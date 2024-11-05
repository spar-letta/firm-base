package auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateRoleDTO {

    @NotBlank
    private String name;

    private String description;

    private UUID institutionId;
}
