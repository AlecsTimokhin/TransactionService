package com.myorg.mainpack.repository;

import com.myorg.mainpack.model.Bill;
import com.myorg.mainpack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE b.id=:id")
    Bill getById(@Param("id") Long id);

    @Query("SELECT b FROM Bill b WHERE b.user.id=:uid ORDER BY b.id DESC")
    List<Bill> findAllYours(@Param("uid") Long uid);

}
