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
public class ImportProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_employee")  // thông qua khóa ngoại id
    private User user;  // đơn hàng của user nào ?

    @Column(name = "date_import")
    private Date dateImport;


    @OneToMany(mappedBy = "importProduct", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<ImportProductDetail> importProductDetails;

}
