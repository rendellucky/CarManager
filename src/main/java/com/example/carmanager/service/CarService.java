package com.example.carmanager.service;

import com.example.carmanager.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car createCar(Car car);

    List<Car> getAllCars(String brand);

    Optional<Car> getCarById(long id);

    Car updateCar(long id, Car car);

    void deleteCar(long id);

    void deleteAllCars();

    List<Car> findByInStock();
}
