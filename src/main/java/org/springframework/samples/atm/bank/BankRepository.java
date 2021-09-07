package org.springframework.samples.atm.bank;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;

@Repository("bankRepository")
public interface BankRepository extends CrudRepository<BankEntity, Integer> {

    @Query("SELECT bank FROM BankEntity bank")
    @Transactional(readOnly = true)
    List<BankEntity> findAll();

    @Query("SELECT denom_value FROM BankEntity  WHERE denom_type LIKE :denomType%")
    @Transactional(readOnly = true)
    int findDenomValue(@Param("denomType") String denomType);

    @Query("SELECT denom_count FROM BankEntity  WHERE denom_type LIKE :denomType%")
    @Transactional(readOnly = true)
    int findByDenomType(@Param("denomType") String denomType);

    @Query("UPDATE BankEntity SET denom_count = :denomCount WHERE denom_type LIKE :denomType%")
    void updateDenomCount(@Param("denomCount") int denomCount, @Param("denomType") String denomType);
}
