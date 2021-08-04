package com.app.myapp.repository;

import com.app.myapp.pojo.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICarRepository extends CrudRepository<Car, Integer> {

}
