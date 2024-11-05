package auth.service;

import auth.dto.request.CreateRoleDTO;
import auth.dto.request.RolePrivilegeDto;
import auth.entity.Institution;
import auth.entity.Privilege;
import auth.entity.Role;
import auth.entity.User;
import auth.repository.InstitutionRepository;
import auth.repository.PrivilegeRepository;
import auth.repository.RoleRepository;
import auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PrivilegeRepository privilegeRepository, UserRepository userRepository, InstitutionRepository institutionRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.userRepository = userRepository;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Role createRole(CreateRoleDTO createRoleDTO) throws Exception {
        Optional<Role> optionalRole = roleRepository.findByNameIgnoreCase(createRoleDTO.getName());
        if (optionalRole.isPresent()) {
            throw new Exception("Role already exists");
        }
        Role role = new Role();
        if(createRoleDTO.getInstitutionId() != null){
            Institution institution = institutionRepository.findByPublicIdAndDeletedIsFalse(createRoleDTO.getInstitutionId()).orElseThrow(() -> new Exception("Institution not found"));
            role.setInstitution(institution);
        }

        role.setName(createRoleDTO.getName());
        role.setDescription(createRoleDTO.getDescription());
        return roleRepository.save(role);
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        List<Role> roleList = roleRepository.findAllAndDeletedIsFalse();
        return new PageImpl<>(roleList, pageable, roleList.size());
    }

    @Override
    public Role assignPrivilegeToRole(UUID rolePublicId, RolePrivilegeDto request) throws Exception {
        Role role = validateRole(rolePublicId);
        List<Privilege> allPrivileges = new ArrayList<>();
        request.getPrivilegeUUIDs().forEach(itemId -> {
            Privilege privilege = privilegeRepository.findByPublicId(itemId);
            if(privilege != null) {
                allPrivileges.add(privilege);
            }
        });

        if(!allPrivileges.isEmpty()) {
            allPrivileges.forEach(privilege -> {
                role.addPrivilege(privilege);
            });
        }
        return roleRepository.save(role);
    }

    private Role validateRole(UUID rolePublicId) throws Exception {
        Optional<Role> optionalRole = roleRepository.findByPublicIdAndDeletedIsFalse(rolePublicId);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }else {
            throw new Exception("Role does not exist");
        }
    }
}
