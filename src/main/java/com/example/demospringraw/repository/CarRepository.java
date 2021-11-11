package com.example.demospringraw.repository;

import com.example.demospringraw.dao.DAOBrand;
import com.example.demospringraw.dao.DAOCar;
import com.example.demospringraw.dao.DAOMain;
import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.entity.Car;

import java.util.ArrayList;

public class CarRepository {

    private static int nextCarId = 1;
    private static final ArrayList<Car> carsList = new ArrayList<>();

    public static Car insertCar(Car car) throws Exception {
        return insertCar(car, true, false);
    }

    public static Car insertCar(Car car, boolean saveOnDatabase, boolean isLoading) throws Exception {
        if (car == null || car.getBrandId() == Car.CAR_NO_BRANDID || car.getModel() == null || car.getColor() == null) {
            String errMsg = "Invalid car or sent attributes. Attributes brandId, model and color are required. Car insert aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        if (!isLoading) {
            if (!BrandRepository.brandExists(car.getBrandId())) {
                String errMsg = "Invalid brandId: " + car.getBrandId() + ". Car insert aborted.";

                System.out.println(errMsg);
                throw new Exception(errMsg);
            }
        }

        int newCarId = Car.CAR_NO_ID;
        // Objeto sem id passado
        if (car.getId() == Car.CAR_NO_ID) {
            // Objeto novo sem id passado

            newCarId = nextCarId;

        } else if (getCar(car.getId()) != null) {
            // JA existe objeto com o id passado.
            String errMsg = "Car with id " + car.getId() + " already exists. Car insert aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        } else if (getCar(car.getId()) == null) {
            // Não existe objeto com o id passado

            if (car.getId() < nextCarId) {
                // ID passado menor que o próximo

                System.out.println("ID:" + car.getId());
                System.out.println(nextCarId);

                String errMsg = "Invalid car id. Must be at least " + nextCarId + ". Car insert aborted.";

                System.out.println(errMsg);
                throw new Exception(errMsg);

            } else {
                newCarId = car.getId();
            }
        }

        Car newCar = new Car(newCarId, car.getBrandId(), car.getModel(), car.getColor());

        carsList.add(newCar);

        if (saveOnDatabase) {
            DAOCar.saveCar(newCar);
        }

        nextCarId = newCar.getId() + 1;

        System.out.println("Car inserted successfully: id: " + newCar.getId()
                + " brandId: " + newCar.getBrandId() + " model: " + newCar.getModel()
                + " color: " + newCar.getColor() + ".");

        return newCar;
    }

    public static void removeCarById(int id, boolean saveOnDatabase) throws Exception {

        int removeId = -1;
        for (int i = 0; i < carsList.size(); i++) {
            if (carsList.get(i).getId() == id) {
                removeId = i;
                break;
            }
        }

        if (removeId > -1) {
            System.out.println("Car removed successfully: id: " + carsList.get(removeId).getId() +
                    " brand id: " + carsList.get(removeId).getBrandId() + "" +
                    " model: " + carsList.get(removeId).getModel() + " color: " + carsList.get(removeId).getColor() + ".");
            carsList.remove(removeId);

            if(saveOnDatabase) {
                DAOCar.saveCars(carsList);
            }
        } else {
            String errMsg = "Car with id " + id + " not found. Car remove aborted.";
            System.out.println(errMsg);

            throw new Exception(errMsg);
        }
    }

    public static void removeCarById(int id) throws Exception {
        removeCarById(id, true);
    }

    public static ArrayList<Car> getCarsList() {
        return carsList;
    }

    public static Car searchCar(int carId) throws Exception {
        if (getCar(carId) != null) {
            return getCar(carId);
        }
        else{
            String errMsg = "Car with id " + carId + " not found.";
            System.out.println(errMsg);

            throw new Exception(errMsg);
        }
    }

    public static Car getCar(int id) {
        for (Car car : carsList) {

            if(car.getId() == id) {
                return car;
            }
        }

        return null;
    }

    public static Car updateCar(int carId, DTOUpdateAttrib updateObj) throws Exception {
        return updateCar(carId, updateObj, true);
    }

    public static Car updateCar(int carId, DTOUpdateAttrib updateObj, boolean saveOnDatabase) throws Exception {
        Car car = getCar(carId);

        if (car == null) {
            String errMsg ="Car with id " + carId + " not found. Car update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        }

        if (updateObj.attribName == null || updateObj.attribValue == null) {
            String errMsg = "Invalid attribName or attribValue. Both are required. Car update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        if (updateObj.attribName.equals("brandId")) {
            if (BrandRepository.brandExists(Integer.parseInt(updateObj.attribValue))) {
                car.setBrandId(Integer.parseInt(updateObj.attribValue));

            } else {
                String errMsg ="Invalid brandId: " + updateObj.attribValue + ". Car update aborted.";

                System.out.println(errMsg);
                throw new Exception(errMsg);
            }

        } else if (updateObj.attribName.equals("model")) {
            car.setModel(updateObj.attribValue);

        } else if (updateObj.attribName.equals("color")) {
            car.setColor(updateObj.attribValue);

        } else {
            String errMsg = "Car at id " + carId + " invalid attribute " + updateObj.attribName
                    + ". Car update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        }


        System.out.println("Car updated successfully: id: " + car.getId() +
                " attribute name: " + updateObj.attribName + " attribute value: " + updateObj.attribValue + ".");

        if (saveOnDatabase){
            DAOCar.saveCars(carsList);
        }

        return car;

    }

    public static Car modifyCar(int id, Car car) throws Exception {
        return modifyCar(id, car, true);
    }

    public static Car modifyCar(int id, Car car, boolean saveOnDatabase) throws Exception {
        Car modifyCar = getCar(id);

        if (modifyCar == null) {
            String errMsg = "Car with id " + id + " not found. Car modify aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        if (car.getBrandId() == Car.CAR_NO_BRANDID || car.getModel() == null || car.getColor() == null ) {
            String errMsg = "Invalid sent attributes. Attributes brandId, model and color are required. Car modify aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        } else if (!BrandRepository.brandExists(car.getBrandId())) {
            String errMsg ="Invalid brandId: " + car.getBrandId() + ". Car modify aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        } else {

            modifyCar.setBrandId(car.getBrandId());
            modifyCar.setModel(car.getModel());
            modifyCar.setColor(car.getColor());

            System.out.println("Car successfully modified: id: " + modifyCar.getId() +
                    " brandId: " + modifyCar.getBrandId() + " model: " +modifyCar.getModel() +
                    " color: " + modifyCar.getColor() + ".");

            if (saveOnDatabase) {
                DAOCar.saveCars(carsList);
            }

            return modifyCar;
        }

    }


    public static void loadSavedCars() throws Exception {

        ArrayList<Car> savedCarsList = DAOCar.getSavedCarsList();

        if (savedCarsList != null) {
            for (Car car: savedCarsList) {
                insertCar(car, false, true);
            }
        }
    }
}
