package com.zerobase.shopreservation.customer.controller;

import com.zerobase.shopreservation.common.dto.ReservationOutputDto;
import com.zerobase.shopreservation.customer.dto.ReservationInputDto;
import com.zerobase.shopreservation.customer.dto.ReservationTimeTableInputDto;
import com.zerobase.shopreservation.customer.dto.ReservationTimeTableOutputDto;
import com.zerobase.shopreservation.customer.exception.CantReservePastTimeException;
import com.zerobase.shopreservation.customer.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController("customerReservationController")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/customer/reservations/timetable")
    public ResponseEntity<ReservationTimeTableOutputDto> timetable(@Validated @ModelAttribute ReservationTimeTableInputDto dto) {
        ReservationTimeTableOutputDto timeTable = reservationService.getReservationTimeTable(dto);
        return ResponseEntity.ok(timeTable);
    }

    @PostMapping("/customer/reservations")
    public ResponseEntity<?> reserve(@Validated @RequestBody ReservationInputDto reservationInputDto) {
        // 시간이 과거인지 확인
        if (reservationInputDto.getSchedule().isBefore(LocalDateTime.now())) {
            throw new CantReservePastTimeException();
        }
        long reservationId = reservationService.reserve(reservationInputDto);

        return ResponseEntity.ok(Collections.singletonMap("reservationId", reservationId));
    }

    @GetMapping("/customer/reservations")
    public ResponseEntity<?> list() {
        List<ReservationOutputDto> list = reservationService.list();
        return ResponseEntity.ok(list);
    }
}
