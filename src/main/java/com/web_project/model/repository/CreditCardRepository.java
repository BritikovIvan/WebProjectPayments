package com.web_project.model.repository;

import com.web_project.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query("SELECT c FROM CreditCard c JOIN BankAccount b ON (c.bankAccount = b.id) JOIN User u ON (b.user = u.id) " +
            "WHERE u.id = :userId AND c.status = 'A'")
    List<CreditCard> findValidUserCards(@Param("userId") Long userId);

    Optional<CreditCard> findByNumber(String number);
}
