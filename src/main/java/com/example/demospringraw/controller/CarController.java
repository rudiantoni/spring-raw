package com.example.demospringraw.controller;

import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.entity.Car;
import com.example.demospringraw.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
public class CarController {

    @RequestMapping(value="/cars", method = GET)
    public ArrayList<Car> getCarsList() {
        return CarRepository.getCarsList();
    }

    @RequestMapping(value="/cars/{id}", method = GET)
    public Object getCar(
            @PathVariable("id") int id
    ) {
        try {
            return CarRepository.searchCar(id);
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value="/cars", method = POST)
    public Object insertCar(
            @RequestBody() Car car
    ) {
        try {
            return CarRepository.insertCar(car);
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value="/cars/{id}", method = DELETE)
    public Object removeBrand(
            @PathVariable("id") int id
    ) {
        try {
            CarRepository.removeCarById(id);
            return ResponseEntity.status(OK).body("");
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    // PATCH: Atualiza um atributo do objeto
    // Convencionado: retorna o objeto inteiro atualizado.
    // Convencionado: inclui no corpo o nome do atributo e o valor.
    @RequestMapping(value="/cars/{id}", method = PATCH)
    public Object updateCar(
            @PathVariable("id") int id,
            @RequestBody() DTOUpdateAttrib updateObj
    ) {
        try {
            return CarRepository.updateCar(id, updateObj);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }

    }

    // PUT: Atualiza o objeto inteiro
    // Convencionado: retorna O objeto inteiro atualizado.
    // Convencionado: inclui no corpo cada atributo a ser atualizado e seu valor.
    @RequestMapping(value="/cars/{id}", method = PUT)
    public Object modifyCar(
            @PathVariable("id") int id,
            @RequestBody() Car car
    ) {
        try {
            return CarRepository.modifyCar(id, car);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

}
