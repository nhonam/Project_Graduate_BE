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
public class ProductRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product_target") // // thông qua khóa ngoại id
    private Product productTarget;// đơn hàng của user nào ?


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product_current") // // thông qua khóa ngoại id
    private Product productCurent;// đơn hàng của user nào ?
}
