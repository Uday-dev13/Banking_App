package Allu.Banking_App.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private double balance;
    private String accHolderName;
}
