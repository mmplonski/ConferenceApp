package com.marcinplonski.conferenceapp.prelections.controller;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.prelections.service.PrelectionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prelections")
public class PrelectionController {

    private final PrelectionService prelectionService;

    public PrelectionController(PrelectionService prelectionService) {
        this.prelectionService = prelectionService;
    }

    /*
    @GetMapping
    public List<Prelection> getPrelections() {
        return prelectionService.getPrelection();
    }

    @GetMapping("/{id}")
    public Prelection getPrelection(@PathVariable Long id) {
        return prelectionService.getPrelection(id);
    }

    @PostMapping
    public Prelection addPrelection(@RequestBody @Valid Prelection prelection) {
        return prelectionService.addPrelection(prelection);
    }

    @DeleteMapping("/{id}")
    public void deletePrelection(@PathVariable Long id) {
        prelectionService.deletePrelection(id);
    }

    @PatchMapping("/{id}")
    public Prelection patchPrelection(@PathVariable Long id, @RequestBody Prelection prelection) {
        return prelectionService.patchPrelection(id, prelection);
    }
     */
}
