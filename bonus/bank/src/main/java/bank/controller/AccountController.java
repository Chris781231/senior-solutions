package bank.controller;

import bank.command.CreateAccountCommand;
import bank.command.UpdateOwnerNameCommand;
import bank.entity.AccountDTO;
import bank.entity.AccountWithHistoryDTO;
import bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountDTO> listAccounts() {
        return service.listAccounts();
    }

    @GetMapping("/{accountNumber}")
    public AccountWithHistoryDTO findAccount(@PathVariable String accountNumber) {
        return service.findAccount(accountNumber);
    }

    @PostMapping
    public AccountDTO createAccount(@RequestBody CreateAccountCommand command) {
        return service.createAccount(command);
    }

    @PutMapping("/{accountNumber}")
    public AccountDTO updateOwnerName(@PathVariable String accountNumber, @RequestBody UpdateOwnerNameCommand command) {
        return service.updateOwnerName(accountNumber, command);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

    @DeleteMapping("/{accountNumber}")
    public void deleteAccount(@PathVariable String accountNumber) {
        service.deleteAccount(accountNumber);
    }
}