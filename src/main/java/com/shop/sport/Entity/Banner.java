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
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_product") // // thông qua khóa ngoại id
    private Product product;// đơn hàng của user nào ?

    @JoinColumn(name = "url_banner") // // thông qua khóa ngoại id
   private String url_banner;

    @JoinColumn(name = "public_id") // // thông qua khóa ngoại id
    private String public_id;
}
