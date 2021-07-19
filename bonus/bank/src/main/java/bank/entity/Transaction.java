package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String targetAccountNumber;

    private double changeBalance;

    private TransactionType transactionType;

    private LocalDateTime transactionTime;

    private double newBalance;

    private String sourceAccountNumber;
}
