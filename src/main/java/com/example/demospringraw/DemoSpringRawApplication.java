package com.example.demospringraw;

import com.example.demospringraw.repository.BrandRepository;
import com.example.demospringraw.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringRawApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringRawApplication.class, args);
		String brandName1 = "Toyota";
		String brandName2 = "Honda";
		String brandName3 = "Nissan";
		String brandName4 = "Volkswagen";
		String carModel1 = "Corolla";
		String carModel2 = "Civic";
		String carModel3 = "Sentra";
		String carModel4 = "Gol";

		// Cadastrar marcas
		BrandRepository.insertBrand(brandName1);
		BrandRepository.insertBrand(brandName2);
		BrandRepository.insertBrand(brandName3);
		BrandRepository.insertBrand(brandName4);

		// Cadastrar carros pelo id das marcas que já estão cadastradas
		CarRepository.insertCar(1, carModel1, "red");
		CarRepository.insertCar(2, carModel2, "blue");
		CarRepository.insertCar(3, carModel3, "green");
		CarRepository.insertCar(4, carModel4, "white");

		// Cadastrar carros pelo nome das marcas que já estão cadastradas
		CarRepository.insertCar(brandName1, "Supra", "cyan");
		CarRepository.insertCar(brandName2, "Acura", "pink");
		CarRepository.insertCar(brandName3, "Skyline", "orange");
		CarRepository.insertCar(brandName4, "Jetta", "gray");

		// Cadastrar carros pelo nome de marcas que ainda não estão cadastradas
		CarRepository.insertCar("Ford", "Mustang", "black");
		CarRepository.insertCar("Lamborghini", "Murcielago", "yellow");
		CarRepository.insertCar("Ferrari", "Maranello", "red ferrari");
		CarRepository.insertCar("Fiat", "Uno", "massa corrida");

	}

}
