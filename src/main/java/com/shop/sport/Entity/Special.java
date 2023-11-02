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
public class Special {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_special")
    private String nameSpecial;

    @OneToMany(mappedBy = "special", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<SpecialDetail> specialDetails;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_category") // // thông qua khóa ngoại id
    private Category category;



}
