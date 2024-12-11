package Allu.Banking_App.Service.Impl;

import Allu.Banking_App.Dto.AccountDto;
import Allu.Banking_App.Entity.Account;
import Allu.Banking_App.Exception.AccountException;
import Allu.Banking_App.Exception.AccountNotFoundException;
import Allu.Banking_App.Exception.DuplicateProductException;
import Allu.Banking_App.Mapper.AccountMapper;
import Allu.Banking_App.Repository.AccountRepository;
import Allu.Banking_App.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;

    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

//        if(accountRepo.findByAccHolderName(accountDto.getAccHolderName()).isPresent()){
//            throw new DuplicateProductException("Account by the name"+accountDto.getAccHolderName()+" is already present!");
//        }
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new AccountNotFoundException("Account with the id"+ id+" does not Found!"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new AccountNotFoundException("Account does not exits!"));

        double total = account.getBalance()  + amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new AccountException("Account does not exits!"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance!");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo
                .findById(id)
                .orElseThrow(()-> new AccountNotFoundException("Account does not exits!"));
        accountRepo.deleteById(id);
    }
}
