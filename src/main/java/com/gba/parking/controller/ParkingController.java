package com.gba.parking.controller;

import com.gba.parking.dto.ParkingDTO;
import com.gba.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "Parking Controller")
@RestController
@RequestMapping(value = "/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping
    @ApiOperation(value = "Register Entry of Vehicle")
    public ParkingDTO entryVehicle (@ApiParam(value = "Task") @RequestBody @Validated ParkingDTO dto) {
        return parkingService.entry(dto);
    }

    @PutMapping
    @ApiOperation(value = "Register Exit of Vehicle")
    public ParkingDTO exitVehicle (@ApiParam(value = "Task") @RequestBody @Validated ParkingDTO dto) {
        return parkingService.exit(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete Parking by ID")
    public boolean delete(@PathVariable Long id) throws Exception {
        return parkingService.delete(id);
    }
}
