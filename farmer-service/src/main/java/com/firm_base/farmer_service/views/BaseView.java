package com.firm_base.farmer_service.views;

public interface BaseView {
    interface FarmerView{}
    interface UserView extends FarmerView{}
    interface CountyView{}
    interface SubCountyView{}
    interface ContactView {}
    interface CategoryView {}
    interface VoucherView{}
    interface ProductItemView{}
}
