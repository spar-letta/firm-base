package com.firm_base.farmer_service.repository;

import com.firm_base.farmer_service.model.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
