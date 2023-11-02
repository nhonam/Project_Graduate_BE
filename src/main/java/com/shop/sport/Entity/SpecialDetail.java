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
public class SpecialDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "special_detail_name")
    private String specialDetailName;

    @OneToMany(mappedBy = "specialDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<SpecialSelected> specialSelecteds;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_special") // // thông qua khóa ngoại id
    private Special special;
}
