package auth.utils.runner;

import auth.dto.request.CreateUserDTO;
import auth.dto.request.RoleDto;
import auth.entity.Role;
import auth.entity.User;
import auth.repository.PrivilegeRepository;
import auth.repository.RoleRepository;
import auth.service.PrivilegeService;
import auth.service.RoleService;
import auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Transactional
@Component
public class BootStrapUser extends BaseFile implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BootStrapUser.class);
    private final UserService userService;

    public BootStrapUser(RoleService roleService, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PrivilegeService privilegeService, UserService userService) {
        super(roleService, roleRepository, privilegeRepository, privilegeService);
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role rolePrivileges = createRolePrivileges("ROLE_ADMINISTRATOR", Arrays.asList(
                "CREATE_USER",
                "READ_USER",
                "UPDATE_USER",
                "CREATE_INSTITUTION_USER",
                "CREATE_ROLE",
                "READ_ROLES",
                "ASSIGN_PRIVILEGE_TO_ROLE",
                "READ_PROFILE",
                "CREATE_PRIVILEGE",
                "READ_PRIVILEGES",
                "CREATE_INSTITUTION",
                "READ_INSTITUTION",
                "CREATE_VOUCHER",
                "READ_VOUCHER",
                "CREATE_FARMER",
                "READ_FARMERS",
                "READ_FARMER",
                "CREATE_CONTACT",
                "CREATE_FARMER_CATEGORY",
                "READ_FARMER_CATEGORY",
                "READ_COUNTIES",
                "CREATE_PRODUCT",
                "READ_PRODUCT",
                "READ_MESSAGES"
        ), null);
        CreateUserDTO req = new CreateUserDTO();
        req.setFirstName("javenock");
        req.setLastName("javwangila");
        req.setOtherName("Other name");
        req.setUserName("javenockAdmin");
        req.setContactEmail("javen@admin.com");
        req.setPassword("123admin!u");
        User user = userService.createUser(req);
        userService.assignRoleToUser(new RoleDto(rolePrivileges.getName()), user.getPublicId());
    }
}


