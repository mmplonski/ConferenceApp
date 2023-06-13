package com.marcinplonski.conferenceapp.prelections.repository;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrelectionRepository extends JpaRepository<Prelection, Long> {
}
