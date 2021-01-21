package com.gba.parking.repository;

import com.gba.parking.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Parking findByLicensePlateAndStatus(String licensePlate, Boolean status);
}
