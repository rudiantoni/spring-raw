package com.example.demospringraw.controller;

import com.example.demospringraw.dto.DTOInsertCar;
import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.entity.Car;
import com.example.demospringraw.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CarController {

    @RequestMapping(value="/cars", method = GET)
    public ArrayList<Car> getCarsList() {
        return CarRepository.getCarsList();
    }

    // Usar /cars/{id} com @PathVariable
    @RequestMapping(value="/car", method = GET)
    public Car getCar(@RequestParam("id") int id) {
        return CarRepository.getCar(id);
    }

    @RequestMapping(value="/car/insert", method = POST)
    public Car insertCar(
            @RequestBody() DTOInsertCar obj
    ) throws IOException {
        return CarRepository.insertCar(obj.brand, obj.model, obj.color);
    }

    @RequestMapping(value="/car/insertId", method = POST)
    public Car insertCarId(
            @RequestBody() DTOInsertCar obj
    ) {
        return CarRepository.insertCar(obj.brandId, obj.model, obj.color);
    }

    @RequestMapping(value="/car/remove", method = DELETE)
    public void removeCar(
            @RequestBody() Car obj
    ) {
        CarRepository.removeCarById(obj.getId());
    }

    // PATCH: Atualiza um atributo do objeto
    @RequestMapping(value="/car/update", method = PATCH)
    public void updateCar(
            @RequestBody() DTOUpdateAttrib obj
    ) {
        CarRepository.updateCar(obj.id, obj.attribName, obj.attribValue);
    }

    // PUT: Atualiza o objeto inteiro
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
