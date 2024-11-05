package com.firm_base.product_service.docs;

public class Examples {
    public static final String CREATE_PRODUCT_REQUEST = "{\"name\":\"Test\",\"description\":\"Test\",\"price\":100,\"quantity\":1}";
    public static final String PRODUCT_OK_RESPONSE = "{\"publicId\":\"ce07a184-a9d0-44f2-beb0-ceefbbd48b69\",\"dateCreated\":\"2024-10-28 12:09:08\",\"createdBy\":null,\"modifiedBy\":null,\"name\":\"Test\",\"description\":\"Test\",\"price\":100,\"quantity\":1}";
    public static final String PRODUCTS_OK_RESPONSE = "{\"totalElements\":1,\"pageSize\":1,\"totalPages\":1,\"last\":true,\"first\":true,\"pageNumber\":0,\"content\":[{\"publicId\":\"9a8e8e34-5440-4357-9b65-d4fc86f5472b\",\"dateCreated\":\"2024-10-28 12:23:52\",\"createdBy\":null,\"modifiedBy\":null,\"name\":\"Test\",\"description\":\"Test\",\"price\":100,\"quantity\":1}]}";
}
