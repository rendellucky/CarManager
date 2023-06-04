package com.example.carmanager.service.impl;

import com.example.carmanager.model.Car;
import com.example.carmanager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements com.example.carmanager.service.CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Override
    public Car createCar(Car car){
        Car _car=carRepository
                .save(new Car(car.getBrand(), car.getModel(), car.getYear(), true));

        return carRepository.save(_car);
    }

    @Override
    public List<Car> getAllCars(String brand){
        List<Car> cars=new ArrayList<Car>();

        if(brand==null)
            carRepository.findAll().forEach(cars::add);
        else
            carRepository.findByBrand(brand).forEach(cars::add);


        return cars;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car updateCar(long id, Car car) {
        Optional<Car> carData=carRepository.findById(id);

        if(carData.isPresent())
        {
            Car _car=carData.get();
            _car.setBrand(car.getBrand());
            _car.setModel(car.getModel());
            _car.setYear(car.getYear());
            _car.setInStock(car.getInStock());
            return carRepository.save(_car);
        }
        else
            return null;

    }

    @Override
    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void deleteAllCars() {
        carRepository.deleteAll();
    }

    @Override
    public List<Car> findByInStock() {
        List<Car> cars=carRepository.findByInStock(true);
        return cars;
    }
}
