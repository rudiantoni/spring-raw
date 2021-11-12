package com.example.demospringraw.dao;

import com.example.demospringraw.entity.Car;

import java.io.IOException;
import java.util.ArrayList;

public class DAOCar {

    private static final String DATABASE_CAR = "./data/bd_car.csv";
    private static final String CSV_DELIMITER = DAOMain.getCSVDelimiter();

    // Arquivo car.csv
    // Coluna 0: id
    // Coluna 1: brandId
    // Coluna 2: model
    // Coluna 3: color
    private static final int CAR_COLUMN_ID = 0;
    private static final int CAR_COLUMN_BRANDID = 1;
    private static final int CAR_COLUMN_MODEL = 2;
    private static final int CAR_COLUMN_COLOR = 3;
    private static final String[] CAR_COLUMNS = {"id", "brandId", "model", "color"};

    public static ArrayList<ArrayList<String>> getSavedCars() throws IOException {
        ArrayList<ArrayList<String>> fileContent = DAOMain.getFileContents(DATABASE_CAR, false);

        return fileContent;
    }

    public static void saveCar(Car car) throws IOException {
        int id = car.getId();
        int brandId = car.getBrandId();
        String model = car.getModel();
        String color = car.getColor();

        DAOMain.insertRow(DATABASE_CAR, id + CSV_DELIMITER + brandId + CSV_DELIMITER + model + CSV_DELIMITER + color + CSV_DELIMITER);
    }

    public static void saveCars(ArrayList<Car> carsList) throws IOException {
        String columns = getCarColumnHeaders();

        String outputStr = columns + "\n";

        for (int i = 0; i < carsList.size(); i++){
            if (carsList.get(i) != null)
                outputStr += getLineStrFromCar(carsList.get(i)) + "\n";
        }

        DAOMain.overwriteFile(DATABASE_CAR, outputStr);
    }

    public static String getLineStrFromCar(Car car) {
        return car.getId() + CSV_DELIMITER + car.getBrandId() + CSV_DELIMITER +
                car.getModel() + CSV_DELIMITER + car.getColor() + CSV_DELIMITER;
    }

    public static ArrayList<Car> getSavedCarsList() throws IOException {

        ArrayList<ArrayList<String>> savedCars = getSavedCars();
        ArrayList<Car> savedCarsList = new ArrayList<>();

        if (savedCars == null) return null;

        for (ArrayList<String> rowData: savedCars) {
            int id = Integer.parseInt(rowData.get(CAR_COLUMN_ID));
            int brandId = Integer.parseInt(rowData.get(CAR_COLUMN_BRANDID));
            String model = rowData.get(CAR_COLUMN_MODEL);
            String color = rowData.get(CAR_COLUMN_COLOR);

            Car newCar = new Car(id, brandId, model, color);
            savedCarsList.add(newCar);
        }

        return savedCarsList;

    }

    public static String getDBCarFileName() {
        return DATABASE_CAR;
    }

    public static String getCarColumnHeaders() {
        String columnsStr = "";
        columnsStr += CAR_COLUMNS[CAR_COLUMN_ID] + CSV_DELIMITER;
        columnsStr += CAR_COLUMNS[CAR_COLUMN_BRANDID] + CSV_DELIMITER;
        columnsStr += CAR_COLUMNS[CAR_COLUMN_MODEL] + CSV_DELIMITER;
        columnsStr += CAR_COLUMNS[CAR_COLUMN_COLOR] + CSV_DELIMITER;

        return columnsStr;
    }


}
