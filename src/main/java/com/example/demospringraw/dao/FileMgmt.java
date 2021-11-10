package com.example.demospringraw.dao;

import com.example.demospringraw.entity.Brand;
import com.example.demospringraw.entity.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileMgmt {

    // Arquivo brand.csv:
    // Coluna 0: id
    // Coluna 1: description
    public static final int BRAND_COLUMN_ID = 0;
    public static final int BRAND_COLUMN_DESCRIPTION = 1;
    public static final String[] BRAND_COLUMNS = {"id", "description"};
    // Arquivo car.csv
    // Coluna 0: id
    // Coluna 1: brandId
    // Coluna 2: model
    // Coluna 3: color
    public static final int CAR_COLUMN_ID = 0;
    public static final int CAR_COLUMN_BRANDID = 1;
    public static final int CAR_COLUMN_MODEL = 2;
    public static final int CAR_COLUMN_COLOR = 3;
    public static final String[] CAR_COLUMNS = {"id", "brandId", "model", "color"};

    private static final String DATABASE_BRAND = "./data/bd_brand.csv";
    private static final String DATABASE_CAR = "./data/bd_car.csv";
    private static final String CSV_DELIMITER = ";";

    public static ArrayList<ArrayList<String>> getSavedBrands() throws IOException {

        ArrayList<ArrayList<String>> fileContent = getFileContents(DATABASE_BRAND, false);

        return fileContent;
    }

    public static ArrayList<ArrayList<String>> getSavedCars() throws IOException {
        ArrayList<ArrayList<String>> fileContent = getFileContents(DATABASE_CAR, false);

        return fileContent;
    }

    public static ArrayList<ArrayList<String>> getFileContents(String dataFile, boolean includeColumnHeader) throws IOException {

        ArrayList<ArrayList<String>> fileContent = new ArrayList<>();
        String readingRow = "";

        File csvFile = new File(dataFile);
        if (!csvFile.isFile()) {
            // Se o arquivo CSV não existir, criá-lo e adicionar os cabeçalhos das colunas
            FileWriter fWriter = new FileWriter(dataFile);

            if (dataFile == DATABASE_BRAND) {
                fWriter.append(BRAND_COLUMNS[BRAND_COLUMN_ID] + CSV_DELIMITER);
                fWriter.append(BRAND_COLUMNS[BRAND_COLUMN_DESCRIPTION] + CSV_DELIMITER);
            } else if (dataFile == DATABASE_CAR){
                fWriter.append(CAR_COLUMNS[CAR_COLUMN_ID] + CSV_DELIMITER);
                fWriter.append(CAR_COLUMNS[CAR_COLUMN_BRANDID] + CSV_DELIMITER);
                fWriter.append(CAR_COLUMNS[CAR_COLUMN_MODEL] + CSV_DELIMITER);
                fWriter.append(CAR_COLUMNS[CAR_COLUMN_COLOR] + CSV_DELIMITER);
            }

            fWriter.append("\n");

            fWriter.flush();
            fWriter.close();
            return null;
        }

        BufferedReader fReader = new BufferedReader(new FileReader(dataFile));

        while((readingRow = fReader.readLine()) != null){
            //Adiciona o conteúdo do arquivo CSV ao ArrayList
            ArrayList<String> rowContent = new ArrayList<>();

            String[] splitedRow = readingRow.split(CSV_DELIMITER);

            for (String s: splitedRow) {
                rowContent.add(s);
            }

            fileContent.add(rowContent);
        }
        fReader.close();

        if (!includeColumnHeader) fileContent.remove(0);

        if (fileContent.isEmpty()) return null;

        return fileContent;
    }

    public static void saveBrand(Brand brand) throws IOException {
        int id = brand.getId();
        String description = brand.getDescription();

        insertRow(DATABASE_BRAND, id + CSV_DELIMITER + description + CSV_DELIMITER);
    }

    public static void saveCar(Car car) throws IOException {
        int id = car.getId();
        int brandId = car.getBrandId();
        String model = car.getModel();
        String color = car.getColor();

        insertRow(DATABASE_CAR, id + CSV_DELIMITER + brandId + CSV_DELIMITER + model + CSV_DELIMITER + color + CSV_DELIMITER);
    }

    public static void insertRow(String dataFile, String dataRow) throws IOException {

        File csvFile = new File(dataFile);
        if (!csvFile.isFile()) return;

        ArrayList<ArrayList<String>> existingFileContents = getFileContents(dataFile, true);
        FileWriter fWriter = new FileWriter(dataFile, true);

        fWriter.append(dataRow + "\n");

        fWriter.flush();
        fWriter.close();

    }

    public static void saveBrands(ArrayList<Brand> brandsList) throws IOException {

        String columns = BRAND_COLUMNS[BRAND_COLUMN_ID] + CSV_DELIMITER + BRAND_COLUMNS[BRAND_COLUMN_DESCRIPTION] + CSV_DELIMITER;
        String outputStr = columns + "\n";

        for (int i = 0; i < brandsList.size(); i++){
            if (brandsList.get(i) != null)
                outputStr += getLineStrFromBrand(brandsList.get(i)) + "\n";
        }

        overwriteFile(DATABASE_BRAND, outputStr);
    }

    public static void saveCars(ArrayList<Car> carsList) throws IOException {
        String columns = CAR_COLUMNS[CAR_COLUMN_ID] + CSV_DELIMITER;
        columns += CAR_COLUMNS[CAR_COLUMN_BRANDID] + CSV_DELIMITER;
        columns += CAR_COLUMNS[CAR_COLUMN_MODEL] + CSV_DELIMITER;
        columns += CAR_COLUMNS[CAR_COLUMN_COLOR] + CSV_DELIMITER;

        String outputStr = columns + "\n";

        for (int i = 0; i < carsList.size(); i++){
            if (carsList.get(i) != null)
                outputStr += getLineStrFromCar(carsList.get(i)) + "\n";
        }

        overwriteFile(DATABASE_CAR, outputStr);
    }

    public static void overwriteFile(String fileName, String fileContent) throws IOException {

        File dataFile = new File(fileName);
        if (!dataFile.isFile()) return;

        FileWriter fWriter = new FileWriter(dataFile);
        fWriter.append(fileContent);

        fWriter.flush();
        fWriter.close();

    }

    public static String getLineStrFromRowArray(ArrayList<String> row) {
        String line = "";

        for (int i = 0; i < row.size(); i++) {
            line += row.get(i) + CSV_DELIMITER;
        }

        line += "\n";

        return line;
    }

    public static String getLineStrFromBrand(Brand brand) {
        return brand.getId() + CSV_DELIMITER + brand.getDescription() + CSV_DELIMITER;
    }

    public static String getLineStrFromCar(Car car) {
        return car.getId() + CSV_DELIMITER + car.getBrandId() + CSV_DELIMITER +
                car.getModel() + CSV_DELIMITER + car.getColor() + CSV_DELIMITER;
    }

}
