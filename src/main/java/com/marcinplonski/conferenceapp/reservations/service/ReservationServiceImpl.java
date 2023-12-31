package com.marcinplonski.conferenceapp.reservations.service;

import com.marcinplonski.conferenceapp.prelections.model.Prelection;
import com.marcinplonski.conferenceapp.prelections.service.PrelectionService;
import com.marcinplonski.conferenceapp.reservations.EmailNotification;
import com.marcinplonski.conferenceapp.reservations.exception.ReservationException;
import com.marcinplonski.conferenceapp.reservations.model.Reservation;
import com.marcinplonski.conferenceapp.reservations.exception.ReservationError;
import com.marcinplonski.conferenceapp.reservations.repository.ReservationRepository;
import com.marcinplonski.conferenceapp.users.model.User;
import com.marcinplonski.conferenceapp.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final PrelectionService prelectionService;
    private final EmailNotification emailNotification;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, PrelectionService prelectionService, EmailNotification emailNotification) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.prelectionService = prelectionService;
        this.emailNotification = emailNotification;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
    }

    @Override
    public Reservation addReservation(String login, String email, Long prelectionId) {
        validateUserAndPrelection(login, email, prelectionId);
        decreseSeats(prelectionId);
        Reservation reservation = new Reservation(prelectionId, userService.getUserByEmail(email).getId());
        Reservation save = reservationRepository.save(reservation);
        sendNotification(email, login);
        return save;
    }

    private void sendNotification(String email, String login) {
        emailNotification.sendNotification(email, login);
    }

    private void decreseSeats(Long prelectionId) {
        Prelection prelection = prelectionService.getPrelection(prelectionId);
        prelection.setAvailableSeats(prelection.getAvailableSeats()-1);
        prelectionService.patchPrelection(prelection.getId(), prelection);
    }

    private void validateUserAndPrelection(String login, String email, Long prelectionId) {
        isFreePlaces(prelectionId);
        boolean userExists = isUserExistsByLoginAndEmail(login, email);
        if (!userExists) {
            userService.addUser(new User(login, email));
        } else {
            User userByEmail = userService.getUserByLogin(login);
            isUserHaveReservationAlready(prelectionId, userByEmail.getId());
        }
    }

    private boolean isUserExistsByLoginAndEmail(String login, String email) {
        return userService.isUserExistsByLoginAndEmail(login, email);
    }


    private void isUserHaveReservationAlready(Long prelectionId, Long userId) {
        LocalTime startTime = prelectionService.getPrelection(prelectionId).getStartTime();
        List<Prelection> collect = prelectionService.getPrelectionsByStartTime(startTime).stream().filter(prelection -> reservationRepository.existsByUserIdAndAndPrelectionId(userId, prelection.getId())).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            throw new ReservationException(ReservationError.RESERVATION_ALREADY_MAKE);
        }
    }

    private void isFreePlaces(Long prelectionId) {
        Prelection prelections = prelectionService.getPrelection(prelectionId);
        if (prelections.getAvailableSeats() < 1) {
            throw new ReservationException(ReservationError.RESERVATION_CANCEL_NO_FREE_PLACES);
        }
    }


    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
        reservationRepository.delete(reservation);

        Prelection prelection = prelectionService.getPrelection(reservation.getPrelectionId());
        if (prelection.getAvailableSeats() < 5) {
            prelection.setAvailableSeats(prelection.getAvailableSeats()+1);
            prelectionService.patchPrelection(prelection.getId(), prelection);
        }
    }

    @Override
    public Reservation patchReservation(Long id, Reservation reservation) {
        return reservationRepository.findById(id).map(reservationFromDB -> {
            if (!ObjectUtils.isEmpty(reservation.getUserId())) {
                reservationFromDB.setUserId(reservation.getUserId());
            }
            if (!ObjectUtils.isEmpty(reservation.getPrelectionId())) {
                reservationFromDB.setPrelectionId(reservation.getPrelectionId());
            }
            return reservationRepository.save(reservationFromDB);
        }).orElseThrow(() -> new ReservationException(ReservationError.RESERVATION_NOT_FOUND));
    }

    @Override
    public Long countByPrelectionId(Long prelectionId) {
        return reservationRepository.countAllByPrelectionId(prelectionId);
    }

    @Override
    public Long count() {
        return reservationRepository.count();
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findAll();
    }

    @Override
    public String getUserReservations(Long userId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<pre>REZERWACJE UŻYTKOWNIKA: " + userService.getUser(userId).getLogin() + "\n");
        reservationRepository.findAllByUserId(userId).stream().forEach(reservation -> {
            Prelection prelection = prelectionService.getPrelection(reservation.getPrelectionId());
            stringBuilder.append(prelection.getPath() + " " + prelection.getDescription() + " " + prelection.getStartTime() + "-" + prelection.getEndTime() + "\n");
        });
        stringBuilder.append("</pre>");
        return stringBuilder.toString();
    }

}
