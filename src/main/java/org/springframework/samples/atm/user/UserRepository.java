package org.springframework.samples.atm.user;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT pin FROM UserEntity WHERE card_num LIKE :cardNum")
    @Transactional(readOnly = true)
    String getUserPin(@Param("cardNum") String cardNum);

    @Query("SELECT id FROM UserEntity WHERE card_num LIKE :cardNum")
    @Transactional(readOnly = true)
    int getUserId(@Param("cardNum") String cardNum);

    @Query("SELECT amount FROM UserEntity WHERE card_num LIKE :cardNum")
    @Transactional(readOnly = true)
    String getUserBalance(@Param("cardNum") String cardNum);

    @Query("UPDATE UserEntity SET amount = :balance WHERE card_num LIKE :cardNum")
    void updateUserBalance(@Param("balance") String balance, @Param("cardNum") String cardNum);

}
