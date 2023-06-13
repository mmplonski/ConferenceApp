package com.marcinplonski.conferenceapp.prelections.repository;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface PrelectionRepository extends JpaRepository<Prelection, Long> {
    List<Prelection> getPrelectionsByStartTime(LocalTime localTime);
}
