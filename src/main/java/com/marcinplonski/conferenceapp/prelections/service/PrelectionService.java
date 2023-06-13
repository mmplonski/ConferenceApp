package com.marcinplonski.conferenceapp.prelections.service;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;

import java.time.LocalTime;
import java.util.List;

public interface PrelectionService {

    List<Prelection> getPrelections();

    Prelection getPrelections(Long id);

    Prelection addPrelection(Prelection prelection);

    void deletePrelection(Long id);

    Prelection patchPrelection(Long id, Prelection prelection);

    List<Prelection> getPrelectionsByStartTime(LocalTime localTime);
}
