package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "contacts", schema = "farmer_v1")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Column(name = "postal_address")
    @JsonView({BaseView.CountyView.class, BaseView.FarmerView.class})
    private String postalAddress;

    @Column(name = "postal_code")
    @JsonView({BaseView.CountyView.class, BaseView.FarmerView.class})
    private String postalCode;

    @Column(name = "email_address")
    @JsonView({BaseView.CountyView.class, BaseView.VoucherView.class, BaseView.FarmerView.class})
    private String emailAddress;

    @Column(name = "phone_number")
    @JsonView({BaseView.CountyView.class, BaseView.VoucherView.class, BaseView.FarmerView.class})
    private String phoneNumber;

    @Column(name = "location")
    @JsonView({BaseView.CountyView.class, BaseView.FarmerView.class})
    private String location;

    @ManyToOne
    @JoinColumn(name = "physical_address_id")
    private Farmer farmer;

}
