package com.firm_base.farmer_service.service;

import com.firm_base.farmer_service.model.*;
import com.firm_base.farmer_service.model.dataType.ActionType;
import com.firm_base.farmer_service.model.request.FarmerContactDto;
import com.firm_base.farmer_service.model.request.FarmerDto;
import com.firm_base.farmer_service.model.vo.Institution;
import com.firm_base.farmer_service.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FarmerServiceImpl implements FarmerService {
    private final ContactRepository contactRepository;

    private final FarmerRepository farmerRepository;
    private final CountyRepository countyRepository;
    private final SubCountyRepository subCountyRepository;
    private final FarmerCategoryRepository farmerCategoryRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public Farmer createFarmer(FarmerDto farmerDto, Authentication loggedInUser) {
        Optional<Farmer> optionalFarmer = farmerRepository.findByNationalIdAndDeletedIsFalse(farmerDto.getNationalId());
        if (!optionalFarmer.isEmpty()) {
            throw new RuntimeException("Farmer already exists");
        }
        Farmer farmer = new Farmer();
        farmer.setNationalId(farmerDto.getNationalId());
        farmer.setFirstName(farmerDto.getFirstName());
        farmer.setLastName(farmerDto.getLastName());
        farmer.setOtherName(farmerDto.getOtherName());
        farmer.setJoinDate(farmerDto.getJoinDate());
        farmer.setLandSize(farmerDto.getLandSize());
        County county = countyRepository.findByPublicId(farmerDto.getCountyId()).orElseThrow(() -> new RuntimeException("County not found"));
        SubCounty subCounty = subCountyRepository.findByPublicId(farmerDto.getSubCountyId()).orElseThrow(() -> new RuntimeException("SubCounty not found"));

        Optional<Institution> optionalInstitution = institutionRepository.findInstitutionByPublicId(farmerDto.getInstitutionId());
        if (!optionalInstitution.isPresent()) {
            throw new RuntimeException("Missing institution");
        }

        farmer.setCounty(county);
        farmer.setSubCounty(subCounty);
        farmer.setInstitution(optionalInstitution.get());

        List<FarmerCategory> farmerCategoryCollect = farmerDto.getFarmerCategoriesIds().stream().map(itemId -> farmerCategoryRepository.findByPublicId(itemId)).collect(Collectors.toList()).stream().filter(Objects::nonNull).collect(Collectors.toList());
        farmer.getFarmerCategories().addAll(farmerCategoryCollect);
        return farmerRepository.save(farmer);
    }

    @Override
    public Page<Farmer> fetchAllFarmers(Pageable pageable) {
        return farmerRepository.findAll(pageable);
    }

    @Override
    public void farmerContact(UUID farmerId, FarmerContactDto farmerContactDto) {
        if (farmerContactDto.getActionType() == null) {
            throw new RuntimeException("Missing action type");
        }
        Farmer farmer = fetchFarmer(farmerId);

        if (farmerContactDto.getActionType() == ActionType.create_contact) {
            Contact contact = new Contact();
            contact.setPhoneNumber(farmerContactDto.getPhoneNumber());
            contact.setEmailAddress(farmerContactDto.getEmailAddress());
            contact.setLocation(farmerContactDto.getLocation());
            contact.setPostalAddress(farmerContactDto.getPostalAddress());
            contact.setPostalCode(farmerContactDto.getPostalCode());
            contact.setFarmer(farmer);
            contactRepository.save(contact);
        }
        if(farmerContactDto.getActionType() == ActionType.update_contact){
            //TODO add update code
        }
    }

    @Override
    public Farmer fetchFarmer(UUID farmerId) {
        Farmer farmer = farmerRepository.findByPublicIdAndDeletedIsFalse(farmerId);
        if (farmer == null)
            throw new RuntimeException("missing farmer");
        return farmer;
    }

}
