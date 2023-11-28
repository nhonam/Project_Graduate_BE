package com.shop.sport.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImportProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "id_User")  // thông qua khóa ngoại id
//    private User user;  // đơn hàng của user nào ?

//    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Collection<Order1> orders;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_import_product") // // thông qua khóa ngoại id
    private ImportProduct importProduct;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product") // // thông qua khóa ngoại id
    private Product product;


//    @OneToMany(mappedBy = "importProduct", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Collection<ImportProduct> importProducts;

    @Column(name = "quantity")
    private Date quantity;

    @Column(name = "price_import")
    private float priceImport;

}
