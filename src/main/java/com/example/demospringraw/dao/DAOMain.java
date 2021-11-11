package com.example.demospringraw.dao;

import java.io.*;
import java.util.ArrayList;

public class DAOMain {


    private static final String CSV_DELIMITER = ";";

    public static ArrayList<ArrayList<String>> getFileContents(String dataFile, boolean includeColumnHeader) throws IOException {

        ArrayList<ArrayList<String>> fileContent = new ArrayList<>();
        String readingRow = "";

        File csvFile = new File(dataFile);
        if (!csvFile.isFile()) {
            // Se o arquivo CSV não existir, criá-lo e adicionar os cabeçalhos das colunas
            FileWriter fWriter = new FileWriter(dataFile);

            if (dataFile.equals(DAOBrand.getDBBrandFileName())) {
                fWriter.append(DAOBrand.getBrandColumnHeaders());
            } else if (dataFile == DAOCar.getDBCarFileName()){
                fWriter.append(DAOCar.getCarColumnHeaders());
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

    public static void insertRow(String dataFile, String dataRow) throws IOException {

        File csvFile = new File(dataFile);
        if (!csvFile.isFile()) return;

        ArrayList<ArrayList<String>> existingFileContents = getFileContents(dataFile, true);
        FileWriter fWriter = new FileWriter(dataFile, true);

        fWriter.append(dataRow + "\n");

        fWriter.flush();
        fWriter.close();

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

    public static String getCSVDelimiter(){
        return CSV_DELIMITER;
    }
}
