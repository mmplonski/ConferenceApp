package com.marcinplonski.conferenceapp.reservations.repository;

import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserId(Long userId);
    boolean existsByUserIdAndAndPrelectionId(Long userId, Long prelectionId);
}
