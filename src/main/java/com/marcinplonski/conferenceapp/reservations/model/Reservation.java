package com.marcinplonski.conferenceapp.reservations.model;
import jakarta.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prelectionId;
    private Long userId;

    public Reservation(Long prelectionId, Long userId) {
        this.prelectionId = prelectionId;
        this.userId = userId;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public Long getPrelectionId() {
        return prelectionId;
    }

    public void setPrelectionId(Long prelectionId) {
        this.prelectionId = prelectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


