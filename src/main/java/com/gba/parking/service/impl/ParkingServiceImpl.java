package com.gba.parking.service.impl;

import com.gba.parking.domain.Parking;
import com.gba.parking.dto.ParkingDTO;
import com.gba.parking.exception.Exception;
import com.gba.parking.exception.InvalidEntityException;
import com.gba.parking.exception.NotFoundException;
import com.gba.parking.mapper.ParkingMapper;
import com.gba.parking.repository.ParkingRepository;
import com.gba.parking.service.ParkingService;
import com.gba.parking.util.DateUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository repository;

    private final ParkingMapper parkingMapper = new ParkingMapper();

    @Override
    public ParkingDTO entry(ParkingDTO dto) {
        Parking parking = parkingMapper.convertToEntity(dto);
        try {
            int hour = new DateUtils().getHour(new Date());
            if(hour >= 8 && hour <= 18) {
                parking.setEntryDate(new Date());
                parking.setStatus(true);
                parking = repository.save(parking);
            } else {
                throw new NotFoundException("Fora do horario de funcionamento!");
            }
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityException(ex.getMessage());
        }
        dto = parkingMapper.convertToDTO(parking);
        return dto;
    }

    public ParkingDTO exit(ParkingDTO dto) {
        Parking parking = repository.findByLicensePlateAndStatus(dto.getLicensePlate(), true);
        if(parking != null) {
            try {
                parking.setExitDate(new Date());
                parking.setPrice(calculatesPrice(
                        parking.getEntryDate(),
                        parking.getExitDate()
                ));
                parking.setStatus(false);
                parking = repository.save(parking);
            } catch (ConstraintViolationException ex) {
                throw new InvalidEntityException(ex.getMessage());
            }
        } else {
            throw new NotFoundException("Placa não encontrada!");
        }
        dto = parkingMapper.convertToDTO(parking);
        return dto;
    }

    public double calculatesPrice(Date entryDate, Date exitDate) {
        double price=0.00;

        int entryHour = new DateUtils().getHour(entryDate);
        int entryMinutes = new DateUtils().getMinutes(entryDate);
        int exitHour = new DateUtils().getHour(exitDate);

        String hourAndMinutes = entryHour+"."+entryMinutes;
        int hours = exitHour - entryHour;

        if(hours > 0) {
            if (entryHour > 8 && exitHour <= 12)
                price += hours * 2;
            else if (Double.parseDouble(hourAndMinutes) > 12.1 && exitHour <= 18)
                price += hours * 3;
            else if (entryHour > 8 && exitHour <= 18)
                price += hours * 2.50;
        } else {
            price = 2;
        }
        return price;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            repository.deleteById(id);
            return true;
        } catch (ConstraintViolationException e){
            throw new InvalidEntityException(e.getMessage());
        }
    }
}