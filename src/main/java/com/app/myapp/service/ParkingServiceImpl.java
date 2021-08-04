package com.app.myapp.service;

import com.app.myapp.pojo.Car;
import com.app.myapp.repository.ICarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingServiceImpl implements IParkingService{
    @Autowired
    ICarRepository carRepository;

    Logger logger = LoggerFactory.getLogger(ParkingServiceImpl.class);


    public ParkingServiceImpl() {
        System.out.println("in ParkingServiceImpl");
    }


//set log level
    //

    @Override
    public List<Car> getCarList() {
        return (ArrayList<Car>) carRepository.findAll();
    }

    @Override
    public Car carEntry(Car car) {
        try {
            System.out.println("in Service - carEntry ");
            Iterable<Car> iterable=carRepository.findAll();
            List<Car> carList = new ArrayList<>();
            int index=1;
            for(Car car1:iterable){
                if(car1.getSlotNumber().equals(null)){
                    System.out.println(index);
                    break;
                }
                index++;
            }
            car.setSlotNumber(String.valueOf(index));
            return carRepository.save(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean carExit(String id) {
        try {
            carRepository.deleteById(Integer.valueOf(id));
            return true;
        }catch (Exception e){
        return false;
        }
    }

    @Override
    public Car findSlotNumberByRegistrationNumber(String registrationNumber) {
        Iterable<Car> iterable=carRepository.findAll();
        logger.info("in find reg no :reg no",registrationNumber);
        for(Car car :iterable){
            if(car.getPlateNumber().equals(registrationNumber)){
                logger.trace("in find reg no ",car);
                return car;
            }
        }
        return null;
    }
    @Override
    public ArrayList<Car> getCarListByColor(String color) {
        System.out.println("in parkingService:getCarListByColor:"+color);
        ArrayList<Car> carList=new ArrayList<>();
        Iterable<Car> iterable=carRepository.findAll();
        Iterator<Car> iterator=iterable.iterator();
        Car tempCarObject;
        while(iterator.hasNext()){
            tempCarObject=iterator.next();
            if(tempCarObject.getColour().equals(color))
                carList.add(tempCarObject);
        }
        if(carList.isEmpty())
            return null;
        else
            return carList;
    }
    @Override
    public Optional<Car> getCar(String id) {
        return carRepository.findById(Integer.valueOf(id));
    }
}
