package com.web_project.model.repository;

import com.web_project.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.sender.id = :userId OR p.recipient.id = :userId")
    List<Payment> findUserPayments(@Param("userId") Long userId);
}
