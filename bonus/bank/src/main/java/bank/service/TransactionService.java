package bank.service;

import bank.entity.TransactionDTO;
import bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository repository;

    public TransactionService(AccountRepository repository) {
        this.repository = repository;
    }


    public List<TransactionDTO> listTransactions() {
        return repository.listTransactions();
    }
}
