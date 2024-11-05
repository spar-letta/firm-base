package auth.utils.helpers;

import auth.entity.Institution;
import auth.entity.Role;
import auth.repository.PrivilegeRepository;
import auth.repository.RoleRepository;
import auth.service.PrivilegeService;
import auth.service.RoleService;
import auth.utils.runner.BaseFile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RolePrivilegesHelper extends BaseFile{

    public RolePrivilegesHelper(RoleService roleService, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PrivilegeService privilegeService) {
        super(roleService, roleRepository, privilegeRepository, privilegeService);
    }

    public Role createRoleWithPrivileges(String roleName, Institution institution) throws Exception {
        Role rolePrivileges = createRolePrivileges(roleName, Arrays.asList("CREATE_MEMBER", "READ_MEMBER", "UPDATE_MEMBER",
                "DELETE_MEMBER","READ_AUDIT_RECORDS", "READ_DEPARTMENTS"), institution);
        return rolePrivileges;

    }
}
