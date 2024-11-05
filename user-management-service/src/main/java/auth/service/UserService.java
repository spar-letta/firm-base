package auth.service;

import auth.dto.request.*;
import auth.entity.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    User createUser(CreateUserDTO createUserDTO);

    User getUserByPublicId(UUID publicId);

    User updateUser(CreateUserDTO createUserDTO, UUID publicId);

    Page<User> getUsers(Pageable pageable);

    User getUserProfile();

    User updateUserProfile(UserProfileDto userProfileDto);

    User assignRoleToUser(RoleDto request, UUID publicId);

    User getOneUser(String username);

    User createInstitutionAdmin(@Valid CreateUserDTO createUserDTO) throws Exception;
}
