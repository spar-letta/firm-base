package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.model.dataType.FarmerCategoryEnum;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmer_categories", schema = "farmer_v1")
public class FarmerCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    @JsonView({BaseView.CategoryView.class, BaseView.FarmerView.class})
    private FarmerCategoryEnum category;

    @Column(name = "land_size")
    @JsonView({BaseView.CategoryView.class, BaseView.FarmerView.class})
    private Integer landSize;
}
