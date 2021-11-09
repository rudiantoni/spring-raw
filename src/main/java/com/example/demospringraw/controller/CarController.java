package com.example.demospringraw.controller;

import com.example.demospringraw.dto.AddCarDTO;
import com.example.demospringraw.dto.InsertCarDTO;
import com.example.demospringraw.dto.UpdateDataDTO;
import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.entity.Car;
import com.example.demospringraw.repository.BrandRepository;
import com.example.demospringraw.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CarController {

    @RequestMapping(value="/cars", method = GET)
    public ArrayList<Car> getCarsList() {
        return CarRepository.getCarsList();
    }

    @RequestMapping(value="/car", method = GET)
    public Car getCar(@RequestParam("id") int id) {
        return CarRepository.getCar(id);
    }

    @RequestMapping(value="/car/insert", method = POST)
    public Car insertCar(
            @RequestBody() InsertCarDTO obj
    ) {
        return CarRepository.insertCar(obj.brand, obj.model, obj.color);
    }

    @RequestMapping(value="/car/insertId", method = POST)
    public Car insertCar(
            @RequestBody() AddCarDTO obj
    ) {
        return CarRepository.insertCar(obj.brand, obj.model, obj.color);
    }

    @RequestMapping(value="/car/remove", method = DELETE)
    public void removeCar(
            @RequestBody() Car obj
    ) {
        CarRepository.removeCarById(obj.getId());
    }

    @RequestMapping(value="/car/update", method = PATCH)
    public void updateCar(
            @RequestBody() UpdateDataDTO obj
    ) {
        CarRepository.updateCar(obj.id, obj.attribName, obj.attribValue);
    }

    @RequestMapping(value="/car/modify/{id}/{brandId}/{brandName}/{model}/{color}", method = PUT)
    public void modifyCar(
            @PathVariable("id") int id,
            @PathVariable("brandId") String brandId,
            @PathVariable("brandName") String brandName,
            @PathVariable("model") String model,
            @PathVariable("color") String color
    ) {
        CarRepository.modifyCar(id, brandName, model, color, brandId);
    }

}
