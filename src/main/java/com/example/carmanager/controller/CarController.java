package com.example.carmanager.controller;

import com.example.carmanager.model.Car;
import com.example.carmanager.repository.CarRepository;
import com.example.carmanager.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService=carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) String brand){
        try{
            List<Car> cars=carService.getAllCars(brand);
            
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") long id){
        Optional<Car> carData=carService.getCarById(id);

        if(carData.isPresent()){
            return new ResponseEntity<>(carData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/cars", consumes={"application/json"})
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        try{
            Car _car= carService
                    .createCar(new Car(car.getBrand(), car.getModel(), car.getYear(), true));
            return new ResponseEntity<>(_car, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/cars/{id}", consumes={"application/json"})
    public ResponseEntity<Car> updateCar(@PathVariable("id") long id, @RequestBody Car car){
        Car carData=carService.updateCar(id, car);

        if(carData==null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") long id){
        try{
            carService.deleteCar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<HttpStatus> deleteAllCars(){
        try{
            carService.deleteAllCars();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("cars/inStock")
    public ResponseEntity<List<Car>> findByInStock(){
        try{
            List<Car> cars=carService.findByInStock();

            if(cars.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
