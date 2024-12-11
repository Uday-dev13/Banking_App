package Allu.Banking_App.Repository;

import Allu.Banking_App.Dto.AccountDto;
import Allu.Banking_App.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<AccountDto> findByAccHolderName(String AccHolderName);
}
