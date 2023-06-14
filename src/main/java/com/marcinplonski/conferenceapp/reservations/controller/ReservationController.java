package com.marcinplonski.conferenceapp.reservations.controller;

import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import com.marcinplonski.conferenceapp.reservations.model.ReservationDto;
import com.marcinplonski.conferenceapp.reservations.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @PostMapping("/{prelectionId}")
    public Reservation addReservation(@PathVariable Long prelectionId, @RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto.getLogin(), reservationDto.getEmail(), prelectionId);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    @PatchMapping("/{id}")
    public Reservation patchReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.patchReservation(id, reservation);
    }
}
