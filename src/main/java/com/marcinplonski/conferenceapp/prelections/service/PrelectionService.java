package com.marcinplonski.conferenceapp.prelections.service;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;

import java.time.LocalTime;
import java.util.List;

public interface PrelectionService {

    List<Prelection> getPrelection();

    Prelection getPrelection(Long id);

    Prelection addPrelection(Prelection prelection);

    void deletePrelection(Long id);

    Prelection patchPrelection(Long id, Prelection prelection);

    List<Prelection> getPrelectionsByStartTime(LocalTime localTime);

    boolean isPrelectionExists(Long prelectionId);
}
