package com.farm.base.notification_service.service;

import com.farm.base.notification_service.dto.MessageDto;
import com.farm.base.notification_service.model.MessageDetail;
import com.farm.base.notification_service.repository.MessageDetailRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDetailServiceImpl implements MessageDetailService{

    @PersistenceContext
    private EntityManager entityManager;
    private final MessageDetailRepository messageDetailRepository;

    @Override
    public Page<MessageDetail> fetchAllMessages(Long nationalId, String searchParam, String voucherNumber, LocalDate expiryDate, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageDetail> cq = cb.createQuery(MessageDetail.class);
        Root<MessageDetail> root = cq.from(MessageDetail.class);

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<MessageDetail> countRoot = countQuery.from(MessageDetail.class);

        cq.distinct(true);

        final List<Predicate> andPredicates = new ArrayList<>();

        if (nationalId != null) {
            Predicate newPredicate = cb.equal(root.get("nationalId"), nationalId);
            andPredicates.add(newPredicate);
        }

        if (voucherNumber != null) {
            Predicate newPredicate = cb.equal(root.get("voucherNumber"), voucherNumber);
            andPredicates.add(newPredicate);
        }

        if (expiryDate != null) {
            LocalDateTime startOfDay = expiryDate.atStartOfDay();
            LocalDateTime endOfDay = expiryDate.atStartOfDay().plusHours(24);
            Predicate newPredicate = cb.between(root.get("expiryDate"), startOfDay, endOfDay);
            andPredicates.add(newPredicate);
        }

        if (searchParam != null && searchParam.trim().length() >= 3) {
            final List<Predicate> orPredicates = new ArrayList<>();
            orPredicates.add(cb.like(cb.upper(root.get("fullName")), "%" + searchParam.toUpperCase() + "%"));
            Predicate p = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
            andPredicates.add(p);
        }

        cq.where(andPredicates.toArray(new Predicate[andPredicates.size()])).orderBy(cb.desc(root.get("id")));

        TypedQuery<MessageDetail> query = entityManager.createQuery(cq).setMaxResults(pageable.getPageSize()).setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        List<MessageDetail> queryResultList = query.getResultList();

        return new PageImpl<>(queryResultList, pageable, queryResultList.size());
    }

    @Override
    public void saveMessageDeatils(MessageDto messageDto) {
        messageDto.getFarmerList().forEach(farmer -> {
            MessageDetail messageDetail = new MessageDetail();
            messageDetail.setVoucherName(messageDto.getVoucherName());
            messageDetail.setVoucherNumber(messageDto.getVoucherNumber());
            messageDetail.setExpiryDate(messageDto.getExpiryDate().atStartOfDay());
            messageDetail.setCollectionCentre(messageDto.getCollectionCentre());
            //Farmer details
            messageDetail.setFullName(farmer.getFullName());
            messageDetail.setEmailAddress(farmer.getEmailAddress());
            messageDetail.setPhoneNumber(farmer.getPhoneNumber());
            messageDetail.setNationalId(farmer.getNationalId());
            messageDetail.setCreatedDate(LocalDateTime.now());
            List<String> messageOne = new ArrayList<>();
            messageDto.getPickItemDtoList().forEach(pickItemDto -> {
                StringBuilder stringBuilder = new StringBuilder();
             String pickItemDetails = stringBuilder.append(pickItemDto.getItemName()).append(",").append(pickItemDto.getQuantity()).append(",").append(pickItemDto.getDescription()).toString();
                messageOne.add(pickItemDetails);
            });

            if(!messageOne.isEmpty()){
                messageOne.forEach(myItem -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(myItem).append(":");
                    messageDetail.setPickItemDescription(stringBuilder.toString());
                });
            }
            messageDetailRepository.save(messageDetail);
        });
    }
}
