package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@Table(schema = "farmer_v1", name = "sub_counties")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCounty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sub_county_name")
    @JsonView({BaseView.SubCountyView.class, BaseView.CountyView.class, BaseView.FarmerView.class})
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "county_id", referencedColumnName = "id")
    private County county;
}
