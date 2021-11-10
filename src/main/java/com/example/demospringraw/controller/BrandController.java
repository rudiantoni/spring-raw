package com.example.demospringraw.controller;

import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.repository.BrandRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class BrandController {

    @RequestMapping(value="/brands", method = GET)
    public ArrayList<Brand> getBrandsList() {
        return BrandRepository.getBrandsList();
    }

    @RequestMapping(value="/brands/{id}", method = GET)
    public Brand getBrand(
            @PathVariable("id") int id
    ) {
        return BrandRepository.searchBrand(id);
    }

    @RequestMapping(value="/brands", method = POST)
    public Brand insertBrand(
            @RequestBody() Brand brand
    ) throws IOException {
        return BrandRepository.insertBrand(brand);

        // Admite apenas description.
        // Adminite id válido e description.
        // Chamar só a brand, salva no DB
        // Chamar brand com true salva no DB
        // Chamar brand com false NÃO salva no DB
    }

    @RequestMapping(value="/brands/{id}", method = DELETE)
    public void removeBrand(
            @PathVariable("id") int id
    ) throws IOException {
        BrandRepository.removeBrandById(id);
    }

    // PATCH: Atualiza um atributo do objeto
    // Convencionado: retorna o objeto inteiro atualizado.
    // Convencionado: inclui no corpo o nome do atributo e o valor.
    @RequestMapping(value="/brands/{id}", method = PATCH)
    public Brand updateBrand(
            @PathVariable("id") int id,
            @RequestBody() DTOUpdateAttrib updateObj
    ) throws IOException {
        //BrandRepository.updateBrand(id, obj.attribName, obj.attribValue); // Passa objeto inteiro
        return BrandRepository.updateBrand(id, updateObj);
    }

    // PUT: Atualiza o objeto inteiro
    // Convencionado: retorna O objeto inteiro atualizado.
    // Convencionado: inclui no corpo cada atributo a ser atualizado e seu valor.
    @RequestMapping(value="/brands/{id}", method = PUT)
    public Brand modifyBrand(
            @PathVariable("id") int id,
            @RequestBody () Brand brand
    ) throws IOException {
        //BrandRepository.modifyBrand(id, brand.getDescription());
        return BrandRepository.modifyBrand(id, brand);
    }
}
