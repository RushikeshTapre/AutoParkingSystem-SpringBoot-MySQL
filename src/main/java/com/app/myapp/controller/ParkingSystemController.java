package com.app.myapp.controller;


import com.app.myapp.pojo.Car;
import com.app.myapp.service.IParkingService;
import com.app.myapp.service.ParkingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class ParkingSystemController {

    @Autowired
    private IParkingService parkingService;
    Logger logger = LoggerFactory.getLogger(ParkingSystemController.class);


    //getAllCarList
    @GetMapping
    public ResponseEntity<?> getAllCarDetails() {
        System.out.println("in get all cars ");
        List<Car> list = parkingService.getCarList();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //get by id
    @GetMapping("{id}")
    public ResponseEntity<?> getCarById(@PathVariable String id) {
        try {
            Optional<Car> car=parkingService.getCar(id);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            System.out.println("err in carEntry" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //create
    @PostMapping
    public ResponseEntity<?> carEntry(@RequestBody Car newCar) {
        System.out.println("Controller: carEntry " + newCar);
        try {
            return new ResponseEntity<>(parkingService.carEntry(newCar), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            System.out.println("err in carEntry" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInfo(@PathVariable String id) {
        try {
            boolean isDataDeleted=parkingService.carExit(id);
            System.out.println("in del info " + id);
            if(!isDataDeleted){
                throw new RuntimeException() ;
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println("err in carEntry" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/registration/{registrationNumber}")
    public ResponseEntity<?> getSlotNumber(@PathVariable String registrationNumber) {
        try {
            logger.trace("in controller getslotnumber",registrationNumber);
            Car car = parkingService.findSlotNumberByRegistrationNumber(registrationNumber);
            logger.trace("in controller getslotnum :car",car);
            if (car != null)
                return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (RuntimeException e) {
            System.out.println("err in carEntry" + e);
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @GetMapping(("/search/color/{color}"))
    public ResponseEntity<?> getCarByColor(@PathVariable String color) {
        System.out.println("in get all cars ");
        ArrayList<Car> list = parkingService.getCarListByColor(color);
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
