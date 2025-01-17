package auth.entity;

import auth.dto.datatypes.UserStatus;
import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "core")
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
@ToString
public class User extends AbstractAuditableEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class, BaseView.internalView.class})
    private String firstName;

    @Column(name = "last_name")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class, BaseView.internalView.class})
    private String lastName;

    @Column(name = "other_name")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class})
    private String otherName;

    @Column(name = "user_name", nullable = false, unique = true)
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class, BaseView.internalView.class})
    private String userName;

    @Column(name = "password")
//    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class})
    private String password;

    @Column(name = "contact_email")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class, BaseView.internalView.class})
    private String contactEmail;

    @Column(name = "contact_phone_number")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class})
    private String contactPhoneNumber;

    @Column(name = "verified_email")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class})
    private boolean verifiedEmail;

    @Column(name = "verified_phone_number")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class})
    private boolean verifiedPhoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class})
    private UserStatus status;

    @Column(name = "change_password")
    @JsonView({BaseView.UserDetailedView.class, BaseView.UserProfileView.class})
    private boolean changePassword;

    @JsonView({BaseView.UserDetailedView.class, BaseView.UserCreatedDetailedView.class, BaseView.UserProfileView.class, BaseView.RoleView.class})
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", schema = "core",
            joinColumns = @JoinColumn(name = "user_id_fk", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fk", referencedColumnName = "id"))
    @Setter(AccessLevel.NONE)
    private List<Role> roles = new ArrayList<>();

    @Transient
    @JsonView({BaseView.ProfileView.class, BaseView.Institution.class})
    private String fullName;

    @Transient
    @JsonView({BaseView.UserCreatedDetailedView.class})
    private String clearPassword;

    public String getFullName() {
        if (otherName == null) {
            return String.format("%s %s", firstName, lastName);
        } else {
            return String.format("%s %s %s", firstName, lastName, otherName);
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        this.getRoles().forEach(item -> {
            item.getPrivilegeList().forEach(privileges -> listAuthorities.add(new SimpleGrantedAuthority(privileges.getName())));
        });
        return listAuthorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != UserStatus.Active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatus.Active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != UserStatus.Active;
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.Active;
    }

}
