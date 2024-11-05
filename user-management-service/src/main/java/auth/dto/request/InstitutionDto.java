package auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstitutionDto {
    private String institutionName;

    private String phoneNumber;

    private String emailAddress;

    private String website;
}
