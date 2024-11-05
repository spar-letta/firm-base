package com.firm_base.farmer_service.web;

import com.firm_base.farmer_service.FarmerServiceApplicationTests;
import com.firm_base.farmer_service.model.request.ItemDto;
import com.firm_base.farmer_service.model.request.VoucherDto;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class VoucherRestControllerTests extends FarmerServiceApplicationTests {

    @Test
    public void createVoucher(){
        VoucherDto voucherDto = new VoucherDto();
        voucherDto.setName("July Mavuno");
        voucherDto.setExpiryDate(LocalDate.now().plusMonths(1));
        voucherDto.setCollectionCentre("Kiringet Store");
        voucherDto.setNumberOfFarmers(51);
        voucherDto.setFarmerListIds(Collections.singleton(UUID.fromString("d2d45432-3bfa-47c7-8701-fa1711ca6079")));

        ItemDto itemDto = new ItemDto();
        itemDto.setItemId(UUID.fromString("47062309-85e9-457e-a039-d5a1083b930d"));
        itemDto.setQuantity(2);
        itemDto.setDescription("50Kgs bag");

        voucherDto.setVoucherItemListIds(List.of(itemDto));
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(accessToken)
                .body(voucherDto).log().all()
                .post("/vouchers")
                .then().log().all()
                .statusCode(200);
    }
}
