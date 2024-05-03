package com.zerobase.shopreservation.customer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    @Positive
    private long reservationId;

    @NotNull
    @Max(5)
    @Min(0)
    private Integer rate;

    private String contents;
}
