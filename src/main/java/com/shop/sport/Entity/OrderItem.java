package com.shop.sport.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "price")
    private float price;

    @Column(name = "price_capital")
    private float price_capital;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product") // // thông qua khóa ngoại id
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_order") // // thông qua khóa ngoại id
    private Order1 order;


    @JsonIgnore
    @OneToOne(mappedBy = "orderItem", cascade = CascadeType.ALL)
    private Evaluate evaluate;





}
