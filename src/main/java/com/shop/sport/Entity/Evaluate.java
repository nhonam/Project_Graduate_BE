package com.shop.sport.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity // Đánh dấu đây là table trong db
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Evaluate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "comment")
    private String comment;

    @Column(name = "start")
    private int start;

    @ManyToOne
    @JoinColumn(name = "id_user") // // thông qua khóa ngoại id
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product") // // thông qua khóa ngoại id
    private Product product;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order_item")
    private OrderItem orderItem;


}
