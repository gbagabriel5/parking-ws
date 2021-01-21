package com.gba.parking.service;

import com.gba.parking.dto.ParkingDTO;

public interface ParkingService {
    ParkingDTO entry(ParkingDTO dto);
    ParkingDTO exit(ParkingDTO dto);
    boolean delete(Long id) throws Exception;
}
