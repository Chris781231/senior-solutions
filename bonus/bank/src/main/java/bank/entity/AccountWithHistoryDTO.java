package bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountWithHistoryDTO {

    private String owner;

    private String accountNumber;

    private double balance;

    private LocalDateTime createTime;

    public void changeBalance(double amount) {
        balance += amount;
    }

    private List<Transaction> transactions;
}
