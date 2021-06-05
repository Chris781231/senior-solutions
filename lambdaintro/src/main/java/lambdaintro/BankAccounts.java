package lambdaintro;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class BankAccounts {

    private final List<BankAccount> accounts;

    public BankAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public List<BankAccount> listBankAccountsByAccountNumber() {
        return accounts.stream()
                .sorted(Comparator.comparing(BankAccount::getAccountNumber))
                .toList();
    }

    public List<BankAccount> listBankAccountsByBalance() {
        return accounts.stream()
                .sorted(Comparator.comparingDouble(b -> Math.abs(b.getBalance())))
                .toList();
    }

    public List<BankAccount> listBankAccountsByBalanceDesc() {
        return accounts.stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance)
                        .reversed())
                .toList();
    }

    public List<BankAccount> listBankAccountsByNameThanAccountNumber() {
        return accounts.stream()
                .sorted(Comparator.comparing(BankAccount::getNameOfOwner,
                        Comparator.nullsFirst(Collator.getInstance(new Locale("hu", "HU"))))
                        .thenComparing(BankAccount::getAccountNumber))
                .toList();
    }
}
