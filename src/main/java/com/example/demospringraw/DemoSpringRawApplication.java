package com.example.demospringraw;

import com.example.demospringraw.repository.BrandRepository;
import com.example.demospringraw.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringRawApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoSpringRawApplication.class, args);

		// Imprimir array direto
		// System.out.println(Arrays.toString("data"));

		BrandRepository.loadSavedBrands();
		CarRepository.loadSavedCars();

	}

}
