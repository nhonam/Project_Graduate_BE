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
public class SpecialSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_special_detail") // // thông qua khóa ngoại id
    private SpecialDetail specialDetail;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_product") // // thông qua khóa ngoại id
    private Product product;


}
