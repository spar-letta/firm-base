package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.config.EventConfig;
import com.firm_base.farmer_service.model.Contact;
import com.firm_base.farmer_service.model.Farmer;
import com.firm_base.farmer_service.model.Voucher;
import com.firm_base.farmer_service.model.VoucherItem;
import com.firm_base.farmer_service.model.dataType.CounterType;
import com.firm_base.farmer_service.model.request.*;
import com.firm_base.farmer_service.repository.FarmerRepository;
import com.firm_base.farmer_service.repository.UserRepository;
import com.firm_base.farmer_service.repository.VoucherItemRepository;
import com.firm_base.farmer_service.repository.VoucherRepository;
import com.firm_base.farmer_service.utils.batch.feign.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final VoucherItemRepository voucherItemRepository;
    private final ProductServiceClient productServiceClient;
    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;
    private final CounterService counterService;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public Voucher createVoucher(VoucherDto voucherDto) {
        Voucher voucher = new Voucher();
        voucher.setName(voucherDto.getName());
        voucher.setNumberOfFarmers(voucherDto.getNumberOfFarmers());
        voucher.setExpiryDate(voucherDto.getExpiryDate());

        List<VoucherItem> voucherItemList = new ArrayList<>();
        voucherDto.getVoucherItemListIds().forEach(itemValue -> {
            VoucherItemDto voucherItemDto = productServiceClient.fetchProduct(itemValue.getItemId()).orElse(null);
            if (voucherItemDto != null) {
                voucherItemDto.setQuantity(itemValue.getQuantity());
                voucherItemDto.setDescription(itemValue.getDescription());
                VoucherItem item = createItem(voucherItemDto, voucherDto);
                voucherItemList.add(item);
            }
        });

        if (voucherItemList.size() > 0)
            voucher.getVoucherItemList().addAll(voucherItemList);

        voucher.setCollectionCentre(voucherDto.getCollectionCentre());
        if (!voucherDto.getFarmerListIds().isEmpty()) {
            Set<Farmer> farmerSet = voucherDto.getFarmerListIds().stream().map(farmerId -> farmerRepository.findByPublicIdAndDeletedIsFalse(farmerId)).collect(Collectors.toSet());
            voucher.getFarmerList().addAll(farmerSet);
        }
        voucher.setNumber(String.format("V-%d", counterService.getNextCounter(CounterType.VOUCHER)));
        Voucher savedVoucher = voucherRepository.save(voucher);
        // send message to client/farmer
        MessageDto messageDto = buildMessageDto(savedVoucher);
        log.info("===========messageDto===={}", messageDto);
        rabbitTemplate.convertAndSend(EventConfig.DIRECT_EXCHANGE, EventConfig.ROUTING_KEY_MESSAGE, messageDto);
        return savedVoucher;
    }

    private MessageDto buildMessageDto(Voucher savedVoucher) {
        MessageDto messageDto = new MessageDto();
        messageDto.setVoucherName(savedVoucher.getName());
        messageDto.setVoucherNumber(savedVoucher.getNumber());
        messageDto.setCollectionCentre(savedVoucher.getCollectionCentre());
        messageDto.setExpiryDate(savedVoucher.getExpiryDate());
        if(!savedVoucher.getFarmerList().isEmpty()){
            List<FarmerDetailsDto> farmerList = new ArrayList<>();

            savedVoucher.getFarmerList().forEach(farmer -> {
                FarmerDetailsDto farmerDetailsDto = new FarmerDetailsDto();
                farmerDetailsDto.setFullName(farmer.getFullName());
                farmerDetailsDto.setNationalId(farmer.getNationalId());
                if(farmer.getPhysicalAddress() != null){
                    Contact contact = farmer.getPhysicalAddress().stream().findFirst().get();
                    farmerDetailsDto.setEmailAddress(contact.getEmailAddress());
                    farmerDetailsDto.setPhoneNumber(contact.getPhoneNumber());
                }
                farmerList.add(farmerDetailsDto);
            });
            messageDto.setFarmerList(farmerList);
        }
        if(!savedVoucher.getVoucherItemList().isEmpty()){
            List<PickItemDto> pickItemDtoList = new ArrayList<>();
            savedVoucher.getVoucherItemList().forEach(voucherItem -> {
                PickItemDto pickItemDto = new PickItemDto();
                pickItemDto.setItemName(voucherItem.getName());
                pickItemDto.setQuantity(voucherItem.getQuantity());
                pickItemDto.setDescription(voucherItem.getDescription());
                pickItemDtoList.add(pickItemDto);
            });
            messageDto.setPickItemDtoList(pickItemDtoList);
        }
        return messageDto;
    }

    public VoucherItem createItem(VoucherItemDto voucherItemDto, VoucherDto voucherDto) {
        VoucherItem item = new VoucherItem();
        item.setName(voucherItemDto.getName());
        item.setPublicId(voucherItemDto.getPublicId());
        item.setQuantity(voucherItemDto.getQuantity());
        item.setDescription(voucherItemDto.getDescription());
        return voucherItemRepository.save(item);
    }
}
