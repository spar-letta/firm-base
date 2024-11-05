package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vouchers", schema = "farmer_v1")
public class Voucher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long id;

    @Column(name = "voucher_name")
    @JsonView({BaseView.VoucherView.class})
    private String name;

    @Column(name = "voucher_number")
    @JsonView({BaseView.VoucherView.class})
    private String number; // VC1230

    @Column(name = "number_of_farmers")
    @JsonView({BaseView.VoucherView.class})
    private Integer numberOfFarmers; // how many farmers

    @Column(name = "expiry_date")
    @JsonView({BaseView.VoucherView.class})
    private LocalDate expiryDate;

    @OneToMany(mappedBy = "voucher")
    @JsonView({BaseView.VoucherView.class})
    private List<VoucherItem> voucherItemList = new ArrayList<>();

    @Column(name = "collection_centre")
    @JsonView({BaseView.VoucherView.class})
    private String collectionCentre;

    @ManyToMany
    @JoinTable(name = "farmers_vouchers", schema = "farmer_v1",
            joinColumns = {@JoinColumn(name = "fk_voucher_id", referencedColumnName = "voucher_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_farmer_id", referencedColumnName = "farmer_id")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"fk_voucher_id", "fk_farmer_id"}))
    @JsonView({BaseView.VoucherView.class})
    @Setter(AccessLevel.NONE)
    private Set<Farmer> farmerList = new HashSet<>();
}
