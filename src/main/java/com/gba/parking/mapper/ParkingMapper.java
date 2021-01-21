package com.gba.parking.mapper;

import com.gba.parking.domain.Parking;
import com.gba.parking.dto.ParkingDTO;

public class ParkingMapper {
    public Parking convertToEntity(ParkingDTO dto) {
        Parking entity = new Parking();
        entity.setId(dto.getId());
        entity.setLicensePlate(dto.getLicensePlate());
        entity.setEntryDate(dto.getEntryDate());
        entity.setExitDate(dto.getExitDate());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public ParkingDTO convertToDTO(Parking entity) {
        ParkingDTO dto = new ParkingDTO();
        dto.setId(entity.getId());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setEntryDate(entity.getEntryDate());
        dto.setExitDate(entity.getExitDate());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
