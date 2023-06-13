package com.marcinplonski.conferenceapp.prelections.service;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.users.model.User;

import java.util.List;

public interface PrelectionService {

    List<Prelection> getPrelection();

    Prelection getPrelection(Long id);

    Prelection addPrelection(Prelection prelection);

    void deletePrelection(Long id);

    Prelection patchPrelection(Long id, Prelection prelection);
}
