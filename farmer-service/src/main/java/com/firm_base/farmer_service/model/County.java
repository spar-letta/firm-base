package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(schema = "farmer_v1", name = "counties")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class County extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "county_name")
    @JsonView({BaseView.CountyView.class, BaseView.FarmerView.class})
    private String name;

    @Column(name = "county_code")
    @JsonView({BaseView.CountyView.class, BaseView.FarmerView.class})
    private String code;

    @OneToMany(mappedBy = "county")
    @JsonView({BaseView.CountyView.class})
    private List<SubCounty> subCounty = new ArrayList<>();

    @Transient
    private List<String> subCountyNames = new ArrayList<>();

    public County(String name, String code, List<String> subCountyNames) {
        this.name = name;
        this.code = code;
        this.subCountyNames = subCountyNames;
    }
}
