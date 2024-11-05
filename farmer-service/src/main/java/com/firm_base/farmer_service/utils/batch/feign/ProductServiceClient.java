package com.firm_base.farmer_service.utils.batch.feign;

import com.firm_base.farmer_service.model.request.VoucherItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/internal/products/{productId}")
    Optional<VoucherItemDto> fetchProduct(@PathVariable("productId") UUID productId);
}
