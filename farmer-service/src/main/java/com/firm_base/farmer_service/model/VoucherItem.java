package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher_items", schema = "farmer_v1")
public class VoucherItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_item_id")
    private Long id;

    @Column(name = "public_id")
    @JsonView({BaseView.ProductItemView.class, BaseView.VoucherView.class})
    private UUID publicId;

    @Column(name = "name")
    @JsonView({BaseView.ProductItemView.class, BaseView.VoucherView.class})
    private String name;

    @Column(name = "quantity")
    @JsonView({BaseView.ProductItemView.class, BaseView.VoucherView.class})
    private Integer quantity;

    @Column(name = "description")
    @JsonView({BaseView.ProductItemView.class, BaseView.VoucherView.class})
    private String description;

    @ManyToOne
    @JoinColumn(name = "voucher_id", referencedColumnName = "voucher_id")
    private Voucher voucher;
}
