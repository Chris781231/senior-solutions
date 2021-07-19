package bank.service;

import bank.command.CreateAccountCommand;
import bank.command.UpdateOwnerNameCommand;
import bank.entity.Account;
import bank.entity.AccountDTO;
import bank.entity.AccountWithHistoryDTO;
import bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {

    private final AccountRepository repository;

    private AtomicLong idGenerator = new AtomicLong();

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountDTO> listAccounts() {
        return repository.listAccounts();
    }

    public AccountDTO createAccount(CreateAccountCommand command) {
        Account account = new Account(command.getOwner(), generateId());

        return repository.createAccount(account);
    }

    public String generateId() {
        String generatedId = ((Long) idGenerator.incrementAndGet()).toString();
        return "0".repeat(Math.max(0, 8 - generatedId.length())) +
                generatedId;
    }

    public AccountDTO updateOwnerName(String accountNumber, UpdateOwnerNameCommand command) {
        return repository.updateOwnerName(accountNumber, command.getOwner());
    }

    public void deleteAll() {
        idGenerator = new AtomicLong();
        repository.deleteAll();
    }

    public void deleteAccount(String accountNumber) {
        repository.deleteAccount(accountNumber);
    }

    public AccountWithHistoryDTO findAccount(String accountNumber) {
        return repository.findAccount(accountNumber);
    }
}
