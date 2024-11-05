package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
