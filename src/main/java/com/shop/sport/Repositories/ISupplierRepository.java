package com.shop.sport.Repositories;

import com.shop.sport.Entity.Environment;
import com.shop.sport.Entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends CrudRepository<Supplier, Long> {

    @Query(value = "CALL is_exsit_supplier(:supplier);", nativeQuery = true)
    Long isExsitSupplier(@Param("supplier") String supplier);

    @Query(value = "CALL check_delete_suplier(:id);", nativeQuery = true)
    int check_delete_suplier(@Param("id") long id);



}
