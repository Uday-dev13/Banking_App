package Allu.Banking_App.Mapper;

import Allu.Banking_App.Dto.AccountDto;
import Allu.Banking_App.Entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
          account.getId(),
          account.getBalance(),
          account.getAccHolderName()
        );
        return accountDto;
    }
}
