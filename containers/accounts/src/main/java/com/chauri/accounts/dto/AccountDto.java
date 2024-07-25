package com.chauri.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
@Data
public class AccountDto {

    @Schema(
            description = "Account number of the Bank account",
            example = "1234567890"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of the Bank account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch address of the Bank account",
            example = "123, ABC Street, NY"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
