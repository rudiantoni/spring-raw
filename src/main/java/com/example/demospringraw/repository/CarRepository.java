package com.example.demospringraw.repository;

import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.entity.Car;

import java.io.IOException;
import java.util.ArrayList;

public class CarRepository {

    private static int nextCarId = 1;
    private static ArrayList<Car> carsList = new ArrayList<>();

    // OK Inserir Carro com o brandId
    public static Car insertCar(int brandId, String model, String color){
        if (!BrandRepository.brandExists(brandId)) {
            // Cancelar se não existir um objeto Brand no ArrayList com esse ID
            return null;
        }
        Car car = new Car(nextCarId, brandId, model, color);

        carsList.add(car);

        nextCarId++;

        System.out.println("Registered new car: id: " + car.getId() + " brand id: " + car.getBrandId() + " brand description: " + BrandRepository.getBrand(car.getBrandId()).getDescription()  + " model: " + car.getModel() + " color: " + car.getColor());

        return car;
    }

    // OK Inserir Carro com o nome e inserir brand se nao existir o nome
    public static Car insertCar(String brandName, String model, String color) throws Exception {
        int registerBrandId;

        if (!BrandRepository.brandExists(brandName)) {
            // Adicionar um novo objeto Brand caso não exista nenhum com o brandName passado

            //registerBrandId = BrandRepository.insertBrand(brandName).getId();
            Brand newBrand = new Brand(brandName);
            registerBrandId = BrandRepository.insertBrand(newBrand).getId();

        } else {
            registerBrandId = BrandRepository.getBrand(brandName).getId();
        }

        return insertCar(registerBrandId, model, color);
    }

    public static void removeCarById(int id) {

        System.out.println("ENTROU");
        int removeId = -1;
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getId() == id) {
                removeId = i;
                break;
            }
        }

        if (removeId > -1) {
            System.out.println("Removed car: id: " + carsList.get(removeId).getId() +
                    " brand id: " + carsList.get(removeId).getBrandId() +
                    " brand description: " + BrandRepository.getBrand(carsList.get(removeId).getBrandId()).getDescription() +
                    " model: " + carsList.get(removeId).getModel() + " color: " + carsList.get(removeId).getColor());

            carsList.remove(removeId);
        }
    }

    public static String getCars(String delimiter){
        String message = "";
        for (Car car : carsList) {
            message += "id: " + car.getId();
            message += "brandId: " + car.getBrandId();
            message += " model: " +car.getModel();
            message += " color: " +car.getColor();

            message += "brandId: " + car.getBrandId();
            if(car.getId() != nextCarId - 1) {
                message += delimiter;
            }
        }
        return message;
    }

    public static ArrayList<Car> getCarsList() {
        return carsList;
    }

    public static Car getCar(int id) {
        for (Car car : carsList) {

            if(car.getId() == id) {
                return car;
            }
        }

        return null;
    }

    public static Car getCar(String model) {
        for (Car car : carsList) {

            if(model.equals(car.getModel())) {
                return car;
            }
        }

        return null;
    }

    public static void updateCar(int carId, String attribName, String attribValue) {
        Car car = getCar(carId);
        boolean canShowMessage = false;
        if (car == null) return;

        if (attribName.equals("model")) {
            car.setModel(attribValue);
            canShowMessage = true;

        } else if (attribName.equals("color")) {
            car.setColor(attribValue);
            canShowMessage = true;

        } else if (attribName.equals("brand")) {
            BrandRepository.getBrand(car.getBrandId()).setDescription(attribValue);
            canShowMessage = true;

        } else if (attribName.equals("brandId")) {
            if  (BrandRepository.brandExists(Integer.parseInt(attribValue))) {
                car.setBrandId(Integer.parseInt(attribValue));
                canShowMessage = true;
            }
        }

        if (canShowMessage){
            System.out.println("Changed car object attribute at id: " + carId +
                    " attribute name: " + attribName + " attribute value: " + attribValue);
        }
    }

    public static void modifyCar(int id, String brandName, String model, String color, String brandId) {
        Car car = getCar(id);

        if (car == null) return;

        if (!BrandRepository.brandExists(Integer.parseInt(brandId))) return;

        BrandRepository.getBrand(car.getBrandId()).setDescription(brandName);
        car.setModel(model);
        car.setColor(color);
        car.setBrandId(Integer.parseInt(brandId));

        System.out.println("Modified whole car object attribute at id: " + car.getId() +
                " brand id: " + car.getBrandId() +
                " brand description: " + BrandRepository.getBrand(car.getBrandId()).getDescription() +
                " model: " + car.getModel() +
                " color: "+ car.getColor());
    }
}
