package com.web_project.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;
    @Column
    private String name;
    @Column
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender")
    private BankAccount sender;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_card")
    private CreditCard senderCard;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient")
    private BankAccount recipient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_card")
    private CreditCard recipientCard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
