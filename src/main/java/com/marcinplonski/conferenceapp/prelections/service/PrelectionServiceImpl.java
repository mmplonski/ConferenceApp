package com.marcinplonski.conferenceapp.prelections.service;

import com.marcinplonski.conferenceapp.prelections.exception.PrelectionError;
import com.marcinplonski.conferenceapp.prelections.exception.PrelectionException;
import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.prelections.repository.PrelectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrelectionServiceImpl implements PrelectionService {

    private final PrelectionRepository prelectionRepository;

    public PrelectionServiceImpl(PrelectionRepository prelectionRepository) {
        this.prelectionRepository = prelectionRepository;
    }

    @Override
    public List<Prelection> getPrelection() {
        return prelectionRepository.findAll();
    }

    @Override
    public Prelection getPrelection(Long id) {
        return prelectionRepository.findById(id).orElseThrow(() -> new PrelectionException(PrelectionError.PRELECTION_NOT_FOUND));
    }

    @Override
    public Prelection addPrelection(Prelection prelection) {
        return prelectionRepository.save(prelection);
    }

    @Override
    public void deletePrelection(Long id) {
        Prelection prelection = prelectionRepository.findById(id).orElseThrow(() -> new PrelectionException(PrelectionError.PRELECTION_NOT_FOUND));
        prelectionRepository.delete(prelection);
    }

    @Override
    public Prelection patchPrelection(Long id, Prelection prelection) {
        return prelectionRepository.findById(id).map(prelectionFromDB -> {
            prelectionFromDB.setAvailableSeats(prelection.getAvailableSeats());
            return prelectionRepository.save(prelectionFromDB);
        }).orElseThrow(() -> new PrelectionException(PrelectionError.PRELECTION_NOT_FOUND));
    }
}
