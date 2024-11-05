package com.farm.base.notification_service.model;

import com.farm.base.notification_service.views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "reports", name = "message_details")
public class MessageDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "national_id")
    @JsonView(BaseView.Message.class)
    private String nationalId;

    @Column(name = "full_name")
    @JsonView(BaseView.Message.class)
    private String fullName;

    @Column(name = "phone_number")
    @JsonView(BaseView.Message.class)
    private String phoneNumber;

    @Column(name = "email_address")
    @JsonView(BaseView.Message.class)
    private String emailAddress;

    @Column(name = "voucher_name")
    @JsonView(BaseView.Message.class)
    private String voucherName;

    @Column(name = "voucher_number")
    @JsonView(BaseView.Message.class)
    private String voucherNumber;

    @Column(name = "expiry_date")
    @JsonView(BaseView.Message.class)
    private LocalDateTime expiryDate;

    @Column(name = "collection_centre")
    @JsonView(BaseView.Message.class)
    private String collectionCentre;

    @Column(name = "description")
    @JsonView(BaseView.Message.class)
    private String pickItemDescription;

    @Column(name = "created_date")
    @JsonView(BaseView.Message.class)
    private LocalDateTime createdDate;

}
