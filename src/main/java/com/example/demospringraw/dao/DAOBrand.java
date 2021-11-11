package com.example.demospringraw.dao;

import com.example.demospringraw.entity.Brand;

import java.io.IOException;
import java.util.ArrayList;

public class DAOBrand {

    private static final String DATABASE_BRAND = "./data/bd_brand.csv";
    private static final String CSV_DELIMITER = DAOMain.getCSVDelimiter();

    // Arquivo brand.csv:
    // Coluna 0: id
    // Coluna 1: description
    private static final int BRAND_COLUMN_ID = 0;
    private static final int BRAND_COLUMN_DESCRIPTION = 1;
    private static final String[] BRAND_COLUMNS = {"id", "description"};

    public static ArrayList<ArrayList<String>> getSavedBrands() throws IOException {

        ArrayList<ArrayList<String>> fileContent = DAOMain.getFileContents(DATABASE_BRAND, false);

        return fileContent;
    }

    public static void saveBrand(Brand brand) throws IOException {
        int id = brand.getId();
        String description = brand.getDescription();

        DAOMain.insertRow(DATABASE_BRAND, id + CSV_DELIMITER + description + CSV_DELIMITER);
    }

    public static void saveBrands(ArrayList<Brand> brandsList) throws IOException {

        String columns = getBrandColumnHeaders();
        String outputStr = columns + "\n";

        for (int i = 0; i < brandsList.size(); i++){
            if (brandsList.get(i) != null)
                outputStr += getLineStrFromBrand(brandsList.get(i)) + "\n";
        }

        DAOMain.overwriteFile(DATABASE_BRAND, outputStr);
    }

    public static String getLineStrFromBrand(Brand brand) {
        return brand.getId() + CSV_DELIMITER + brand.getDescription() + CSV_DELIMITER;
    }

    public static ArrayList<Brand> getSavedBrandsList() throws IOException {

        ArrayList<ArrayList<String>> savedBrands = getSavedBrands();
        ArrayList<Brand> savedBrandsList = new ArrayList<>();

        if (savedBrands == null) return null;

        for (ArrayList<String> rowData: savedBrands) {
            int id = Integer.parseInt(rowData.get(BRAND_COLUMN_ID));
            String description = rowData.get(BRAND_COLUMN_DESCRIPTION);

            Brand newBrand = new Brand(id, description);
            savedBrandsList.add(newBrand);
        }

        return savedBrandsList;

    }

    public static String getDBBrandFileName() {
        return DATABASE_BRAND;
    }

    public static String getBrandColumnHeaders() {
        String columnsStr = "";
        columnsStr += BRAND_COLUMNS[BRAND_COLUMN_ID] + CSV_DELIMITER;
        columnsStr += BRAND_COLUMNS[BRAND_COLUMN_DESCRIPTION] + CSV_DELIMITER;

        return columnsStr;
    }


}
