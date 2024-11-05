package auth.web;

import auth.application.doc.Examples;
import auth.dto.request.InstitutionDto;
import auth.entity.Institution;
import auth.service.InstitutionService;
import auth.utils.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institutions")
@Tag(name = "institutions")
@RequiredArgsConstructor
@SecurityRequirement(name = "Jwt")
public class InstitutionController {

    private final InstitutionService institutionService;

    @PreAuthorize("hasAuthority('CREATE_INSTITUTION')")
    @PostMapping
    @Operation(summary = "Create Institution", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.INSTITUTION_OK_RESPONSE)))
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(examples = {@ExampleObject(name = "Create Institution Request", value = Examples.CREATE_INSTITUTION_REQUEST)})))
    @JsonView(BaseView.Institution.class)
    public Institution createInstitution(@RequestBody InstitutionDto institutionDto) {
        return institutionService.createInstitution(institutionDto);
    }

    @PreAuthorize("hasAuthority('READ_INSTITUTION')")
    @GetMapping
    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(example = Examples.INSTITUTIONS_OK_RESPONSE)))})
    @JsonView(BaseView.Institution.class)
    public Page<Institution> getInstitutions(Pageable pageable) {
        return institutionService.fetchInstitution(pageable);
    }
}
