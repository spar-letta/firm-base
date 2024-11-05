package com.firm_base.farmer_service.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(schema = "core", name = "institutions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Institution implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_id", updatable = false, insertable = false)
    @JsonView({BaseView.UserView.class, BaseView.FarmerView.class})
    private UUID publicId;

    @Column(name = "institution_name", updatable = false, insertable = false)
    @JsonView({BaseView.UserView.class, BaseView.FarmerView.class})
    private String institutionName;

}
