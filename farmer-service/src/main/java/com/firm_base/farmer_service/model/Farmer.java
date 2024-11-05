package com.firm_base.farmer_service.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.firm_base.farmer_service.model.vo.Institution;
import com.firm_base.farmer_service.views.BaseView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmers", schema = "farmer_v1")
public class Farmer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farmer_id")
    private Long id;

    @Column(name = "first_name")
    @JsonView({BaseView.FarmerView.class})
    private String firstName;

    @Column(name = "last_name")
    @JsonView({BaseView.FarmerView.class})
    private String lastName;

    @Column(name = "other_name")
    @JsonView({BaseView.FarmerView.class})
    private String otherName;

    @Column(name = "national_id")
    @JsonView({BaseView.FarmerView.class, BaseView.VoucherView.class})
    private String nationalId;

    @Column(name = "farmer_land_size")
    @JsonView(BaseView.FarmerView.class)
    private Integer landSize;

    @Column(name = "join_date")
    @JsonView(BaseView.FarmerView.class)
    private LocalDate joinDate;

    @ManyToOne
    @JoinColumn(name = "county_id", referencedColumnName = "id")
    @JsonView(BaseView.FarmerView.class)
    private County county;

    @ManyToOne
    @JoinColumn(name = "sub_county_id", referencedColumnName = "id")
    @JsonView(BaseView.FarmerView.class)
    private SubCounty subCounty;

    @OneToMany(mappedBy = "farmer")
    @JsonView({BaseView.FarmerView.class, BaseView.VoucherView.class})
    private List<Contact> physicalAddress = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    @JsonView(BaseView.FarmerView.class)
    private Institution institution;

    @ManyToMany
    @JoinTable(name = "categories", schema = "farmer_v1",
            joinColumns = @JoinColumn(name = "farmer_id", referencedColumnName = "farmer_id"),
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"farmer_id", "category_id"}))
    @Setter(AccessLevel.NONE)
    @JsonView(BaseView.FarmerView.class)
    private List<FarmerCategory> farmerCategories = new ArrayList<>();

    @Transient
    @JsonView({BaseView.FarmerView.class, BaseView.VoucherView.class})
    private String fullName;

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ");
        sb.append(lastName);
        if (otherName != null)
            sb.append(" ").append(otherName);
        return sb.toString();
    }
}
