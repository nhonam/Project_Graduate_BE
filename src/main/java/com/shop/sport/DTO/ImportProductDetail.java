package com.shop.sport.DTO;

import java.util.Date;

public interface ImportProductDetail {

    long getid();
    Date getdate_import();
    float getprice_import();
    long getquantity();
    String getimage_url();
    String getproduct_name();
}
