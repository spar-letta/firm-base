package auth.entity;

import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SQLRestriction(value = "deleted = false")
@Table(name = "roles", schema = "core")
public class Role extends AbstractAuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    @JsonView(BaseView.RoleView.class)
    private Long id;

    @Column(name = "name")
    @JsonView({BaseView.RoleView.class, BaseView.UserDetailedView.class})
    private String name;

    @Column(name = "description")
    @JsonView({BaseView.RoleView.class, BaseView.UserDetailedView.class})
    private String description;

    @ManyToOne
    @JoinColumn(name = "role_institution_id_fk")
    @JsonView({BaseView.RoleView.class, BaseView.UserDetailedView.class})
    private Institution institution;

    @JsonView({BaseView.RoleView.class, BaseView.UserDetailedView.class})
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "role_privileges", schema = "core",
            joinColumns = @JoinColumn(name = "role_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id_fk"))
    private List<Privilege> privilegeList = new ArrayList<>();

    public void addPrivilege(Privilege privilege) {
        if(privilegeList == null) {
            privilegeList = new ArrayList<>();
        }
        Optional<Privilege> optionalPrivilege = privilegeList.stream()
                .filter(existingPrivilege -> existingPrivilege.getId().equals(privilege.getId()))
                .findFirst();
        if (!optionalPrivilege.isPresent()) {
            privilegeList.add(privilege);
        }
    }

}
