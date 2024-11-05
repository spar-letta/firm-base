package auth.entity;

import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(schema = "core", name = "institutions")
public class Institution extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "institution_name", nullable = false)
    @JsonView(BaseView.Institution.class)
    private String institutionName;

    @Column(name = "institution_phone_number")
    @JsonView(BaseView.Institution.class)
    private String phoneNumber;

    @Column(name = "email_address")
    @JsonView(BaseView.Institution.class)
    private String emailAddress;

    @Column(name = "institution_website")
    @JsonView(BaseView.Institution.class)
    private String website;

    @Column(name = "institution_registration_number")
    private String registrationNumber;

    @Column(name = "institution_deactivated")
    private boolean deactivated;

    @Column(name = "institution_unique_code")
    @JsonView(BaseView.Institution.class)
    private String uniqueCode;
}
