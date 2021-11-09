package com.example.demospringraw.repository;

import com.example.demospringraw.entity.Brand;

import java.util.ArrayList;

public class BrandRepository {

    private static int nextBrandId = 1;
    private static final ArrayList<Brand> brandsList = new ArrayList<>();

    // OK Inserir Marca
    public static Brand insertBrand(String description){
        Brand brand = new Brand(nextBrandId, description);

        brandsList.add(brand);

        nextBrandId++;
        System.out.println("Registered new brand: id: " + brand.getId() + " description: " + brand.getDescription());
        return brand;
    }

    public static void removeBrandById(int id) {
        int removeId = -1;
        for (int i = 0; i < brandsList.size(); i++) {
            if (brandsList.get(i).getId() == id) {
                removeId = i;
                break;
            }
        }

        if (removeId > -1) {
            System.out.println("Removed brand: id: " + brandsList.get(removeId).getId() + " brand description: " + brandsList.get(removeId).getDescription());
            brandsList.remove(removeId);
        }
    }

    public static String getBrands(String delimiter){
        StringBuilder message = new StringBuilder();
        for (Brand brand : brandsList) {
            message.append("id: ").append(brand.getId()).append(" description: ").append(brand.getDescription());

            if(brand.getId() != nextBrandId - 1) {
                message.append(delimiter);
            }
        }
        return message.toString();
    }

    public static ArrayList<Brand> getBrandsList(){
        return brandsList;
    }

    public static Brand getBrand(int id) {

        for (Brand brand : brandsList) {

            if(brand.getId() == id) {
                return brand;
            }
        }

        return null;
    }

    public static Brand getBrand(String description) {
        for (Brand brand : brandsList) {

            if(description.equals(brand.getDescription())) {
                return brand;
            }
        }

        return null;
    }

    public static boolean brandExists(int brandId) {
        Brand brand = getBrand(brandId);

        return brand != null;
    }

    public static boolean brandExists(String brandDescription) {
        Brand brand = getBrand(brandDescription);

        return brand != null;
    }

    public static void updateBrand(int brandId, String attribName, String attribValue) {
        Brand brand = getBrand(brandId);

        if (brand == null) return;

        if (attribName.equals("description")) {
            brand.setDescription(attribValue);
            System.out.println("Changed brand object attribute at id: " + brand.getId() +
                    "attribute name: " + attribName + " attribute value: " + brand.getDescription());
        }
    }

    public static void modifyBrand(int id, String description) {
        Brand brand = getBrand(id);

        if (brand == null) return;

        brand.setDescription(description);

        System.out.println("Modified whole brand object attribute at id: " + brand.getId() +
                " description: " + brand.getDescription());
    }

}
