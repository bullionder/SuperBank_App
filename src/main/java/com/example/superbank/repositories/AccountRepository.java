package com.example.superbank.repositories;

import com.example.superbank.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE clients SET balance = ?1 WHERE id = ?2", nativeQuery = true)
    void updateBalanceById(BigDecimal balance, Long id);

    @Query(value = "SELECT * from clients where id = ?1", nativeQuery = true)
    List<AccountEntity> findOneTest(Long id);
}
