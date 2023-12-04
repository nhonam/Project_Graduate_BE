package com.shop.sport.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "stock_quantity")
    private int stockQuantity;


    @Column(name = "price")
    private float price;

    @Column(name = "price_capital")
    private float priceCapital;

    @Lob
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ImportProductDetail> importProductDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<OrderItem> orderItems;

//    @OneToMany(mappedBy = "productTarget", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Collection<ProductRelation> productRelationTarget;
//
//    @OneToMany(mappedBy = "productCurent", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Collection<ProductRelation> productRelationCurrent;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Evaluate> evaluates;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "id_category") // // thông qua khóa ngoại id
    private Category category;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "id_supplier") // // thông qua khóa ngoại id
    private Supplier supplier;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "id_activity") // // thông qua khóa ngoại id
    private Activity activity;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "id_environment") // // thông qua khóa ngoại id
    private Environment environment;

    @ManyToOne
    @JoinColumn(name = "id_brand") // // thông qua khóa ngoại id
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_unit") // // thông qua khóa ngoại id
    private Unit unit;







}
