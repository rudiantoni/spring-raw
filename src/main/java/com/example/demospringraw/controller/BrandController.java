package com.example.demospringraw.controller;

import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.repository.BrandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
public class BrandController {

    @RequestMapping(value="/brands", method = GET)
    public ArrayList<Brand> getBrandsList() {
        return BrandRepository.getBrandsList();
    }

    @RequestMapping(value="/brands/{id}", method = GET)
    public Object getBrand(
            @PathVariable("id") int id
    ) {
        try {
            return BrandRepository.searchBrand(id);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping(value="/brands", method = POST)
    public Object insertBrand(
            @RequestBody() Brand brand
    ) {
        // Admite apenas description.
        // Adminite id válido e description.
        // Chamar só a brand, salva no DB
        // Chamar brand com true salva no DB
        // Chamar brand com false NÃO salva no DB
        try {
            return BrandRepository.insertBrand(brand);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }

    }

    @RequestMapping(value="/brands/{id}", method = DELETE)
    public Object removeBrand(
            @PathVariable("id") int id
    ) {
        try {
            BrandRepository.removeBrandById(id);
            return ResponseEntity.status(OK).body("");
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    // PATCH: Atualiza um atributo do objeto
    // Convencionado: retorna o objeto inteiro atualizado.
    // Convencionado: inclui no corpo o nome do atributo e o valor.
    @RequestMapping(value="/brands/{id}", method = PATCH)
    public Object updateBrand(
            @PathVariable("id") int id,
            @RequestBody() DTOUpdateAttrib updateObj
    ) {
        try {
            return BrandRepository.updateBrand(id, updateObj);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT: Atualiza o objeto inteiro
    // Convencionado: retorna O objeto inteiro atualizado.
    // Convencionado: inclui no corpo cada atributo a ser atualizado e seu valor.
    @RequestMapping(value="/brands/{id}", method = PUT)
    public Object modifyBrand(
            @PathVariable("id") int id,
            @RequestBody () Brand brand
    ) {

        try {
            return BrandRepository.modifyBrand(id, brand);
        } catch (Throwable e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }
}
