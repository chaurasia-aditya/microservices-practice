package com.chauri.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loan",
        description = "Schema to hold Loan information"
)
@Data
public class LoanDto {
    @Schema(
            description = "Mobile Number of user",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile number cannot e null or empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Loan Number of user",
            example = "123456789123"
    )
    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|\\d{12})",message = "Loan number must be 12 digits")
    private String loanNumber;

    @Schema(
            description = "Type of loan",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan type cannot be null or empty")
    private String loanType;

    @Schema(
            description = "Total loan amount",
            example = "100000"
    )
    @Positive(message = "Total loan amount should be greater than 0")
    private int totalLoan;

    @Schema(
            description = "Total loan amount paid",
            example = "1000"
    )
    @PositiveOrZero(message = "Loan amount paid should be greater than equal to 0")
    private int amountPaid;

    @Schema(
            description = "Total outstanding amount against a loan",
            example = "99000"
    )
    @PositiveOrZero(message = "Outstanding loan amount should be greater than equal to 0")
    private int outstandingAmount;
}
