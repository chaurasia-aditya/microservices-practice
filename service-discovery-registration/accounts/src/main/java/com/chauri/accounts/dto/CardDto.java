package com.chauri.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Card",
        description = "Schema to hold Card information"
)
@Data
public class CardDto {
    @Schema(
            description = "Mobile Number of user",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Card Number of user",
            example = "123456789123"
    )
    @NotEmpty(message = "Card Number can not be a null or empty")
    @Pattern(regexp="(^$|\\d{12})",message = "Card number must be 12 digits")
    private String cardNumber;

    @Schema(
            description = "Type of Card",
            example = "Credit Card"
    )
    @NotEmpty(message = "Card type cannot be null or empty")
    private String cardType;

    @Schema(
            description = "Total card limit",
            example = "100000"
    )
    @Positive(message = "Total card limit should be greater than 0")
    private int totalLimit;

    @Schema(
            description = "Total card amount used",
            example = "1000"
    )
    @PositiveOrZero(message = "Card amount used should be greater than equal to 0")
    private int amountUsed;

    @Schema(
            description = "Available card limit against a card",
            example = "99000"
    )
    @PositiveOrZero(message = "Available card limit should be greater than equal to 0")
    private int availableAmount;
}
