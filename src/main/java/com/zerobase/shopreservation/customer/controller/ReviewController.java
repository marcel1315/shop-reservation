package com.zerobase.shopreservation.customer.controller;

import com.zerobase.shopreservation.common.dto.ReviewOutputPageDto;
import com.zerobase.shopreservation.common.dto.ReviewsOfShopDto;
import com.zerobase.shopreservation.customer.dto.ReviewDto;
import com.zerobase.shopreservation.customer.dto.UpdateReviewDto;
import com.zerobase.shopreservation.customer.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController("customerReviewController")
@RequiredArgsConstructor
@Tag(name = "7 - Customer Review", description = "고객 리뷰 관련")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "예약에 대한 리뷰를 남김"
    )
    @PostMapping("/customer/review")
    public ResponseEntity<?> review(@Validated @RequestBody ReviewDto reviewDto) {
        LocalDateTime now = LocalDateTime.now();
        reviewService.review(reviewDto, now);
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "예약에 대한 리뷰를 남김"
    )
    @PostMapping("/customer/review/{reviewId}")
    public ResponseEntity<?> review(@Validated @RequestBody UpdateReviewDto updateReviewDto, @PathVariable long reviewId) {
        reviewService.updateReview(reviewId, updateReviewDto);
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "예약에 대한 리뷰를 제거",
            description = "자신이 남긴 특정 예약에 대한 리뷰를 제거"
    )
    @DeleteMapping("/customer/review/{reviewId}")
    public ResponseEntity<?> review(
            @PathVariable
            @Parameter(
                    schema = @Schema(defaultValue = "1")
            )
            long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "특정 상점에 대한 모든 리뷰를 봄",
            description = "Pagination이 적용되어 있음"
    )
    @GetMapping("/customer/reviews")
    public ResponseEntity<?> listReviews(@ModelAttribute ReviewsOfShopDto dto) {
        ReviewOutputPageDto page = reviewService.listReviews(dto);
        return ResponseEntity.ok(page);
    }
}
