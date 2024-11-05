package com.firm_base.product_service.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.product_service.views.BaseView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products", schema = "product_v1")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    @JsonView(BaseView.ProductView.class)
    private String name;

    @Column(name = "description")
    @JsonView(BaseView.ProductView.class)
    private String description;

    @Column(name = "price")
    @JsonView(BaseView.ProductView.class)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "quantity")
    @JsonView(BaseView.ProductView.class)
    private Integer quantity;
}
