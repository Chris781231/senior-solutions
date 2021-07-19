package bank.repository;

import bank.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private final List<Account> accounts = new ArrayList<>(List.of(
            new Account("Charlie Parker", "00000000", 0, LocalDateTime.now(), new ArrayList<>())
    ));

    private final ModelMapper modelMapper;

    public AccountRepository(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<AccountDTO> listAccounts() {
        Type targetListType = new TypeToken<List<Account>>(){}.getType();
        return modelMapper.map(accounts,targetListType);
    }

    public AccountWithHistoryDTO findAccount(String accountNumber) {
        Account result = findByAccountNumber(accountNumber);
        return modelMapper.map(result, AccountWithHistoryDTO.class);
    }

    public AccountDTO createAccount(Account account) {
        accounts.add(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    public AccountDTO updateOwnerName(String accountNumber, String owner) {
        Account result = findByAccountNumber(accountNumber);
        result.setOwner(owner);
        return modelMapper.map(result, AccountDTO.class);
    }

    public void deleteAll() {
        accounts.clear();
    }

    public void deleteAccount(String accountNumber) {
        Account result = findByAccountNumber(accountNumber);
        accounts.remove(result);
    }

    private Account findByAccountNumber(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equalsIgnoreCase(accountNumber))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find accountNumber: " + accountNumber));
    }

    public List<TransactionDTO> listTransactions() {
        return accounts.stream()
                .flatMap(account -> account.getTransactions().stream())
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }
}
