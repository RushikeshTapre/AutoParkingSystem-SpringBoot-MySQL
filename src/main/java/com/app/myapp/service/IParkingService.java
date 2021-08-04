package com.app.myapp.service;

import com.app.myapp.pojo.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IParkingService {

    List<Car> getCarList();
    Car carEntry(Car c);
    boolean carExit(String plateNumber);
    Car findSlotNumberByRegistrationNumber(String registrationNumber );
    ArrayList<Car> getCarListByColor(String colour);
    Optional<Car> getCar(String id);

}
