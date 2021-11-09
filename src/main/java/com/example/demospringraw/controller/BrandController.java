package com.example.demospringraw.controller;

import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.dto.UpdateDataDTO;
import com.example.demospringraw.repository.BrandRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class BrandController {

    @RequestMapping(value="/brands", method = GET)
    public ArrayList<Brand> getBrandsList() {
        return BrandRepository.getBrandsList();
    }

    @RequestMapping(value="/brand", method = GET)
    public Brand getBrand(@RequestParam("id") int id) {
        return BrandRepository.getBrand(id);
    }

    @RequestMapping(value="/brand/insert", method = POST)
    public Brand insertBrand(
            @RequestBody() Brand obj
    ) {
        return BrandRepository.insertBrand(obj.getDescription());
    }

    @RequestMapping(value="/brand/remove", method = DELETE)
    public void removeBrand(
            @RequestBody() Brand obj
    ) {
        BrandRepository.removeBrandById(obj.getId());
    }

    @RequestMapping(value="/brand/update", method = PATCH)
    public void updateBrand(
            @RequestBody() UpdateDataDTO obj
    ) {
        BrandRepository.updateBrand(obj.id, obj.attribName, obj.attribValue);
    }

    @RequestMapping(value="/brand/modify/{id}/{description}", method = PUT)
    public void modifyBrand(
            @PathVariable("id") int id,
            @PathVariable("description") String description
    ) {
        BrandRepository.modifyBrand(id, description);
    }
}
