package com.zerobase.restaurant.reservation.repository;

import com.zerobase.restaurant.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) > 0 " +
            "FROM Reservation r " +
            "WHERE r.reservationDate = :#{#reservationTime.toLocalDate()} " +
            "AND r.reservationTime = :#{#reservationTime.toLocalTime()}")
    boolean existsReservationTime(@Param("reservationTime")LocalDateTime reservationTime);

    @Query(" SELECT r FROM Reservation r " +
            " WHERE r.store.id = :id " +
            " ORDER BY r.reservationDate " )
    List<Reservation> findReservationsByStoreId(@Param("id") Long id);
}
