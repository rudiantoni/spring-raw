package com.example.demospringraw.repository;

import com.example.demospringraw.dao.FileMgmt;
import com.example.demospringraw.dto.DTOUpdateAttrib;
import com.example.demospringraw.entity.Brand;

import java.util.ArrayList;

public class BrandRepository {

    private static int nextBrandId = 1;
    private static final ArrayList<Brand> brandsList = new ArrayList<>();

    // OK Inserir Marca
    // Overload
    // insertBrand(brand) salva no BD
    // insertBrand(brand, true) salva no BD
    // insertBrand(brand, false) não salva no BD
    public static Brand insertBrand(Brand brand, boolean saveOnDatabase) throws Exception {
        if (brand.getDescription() == null) {
            String errMsg = "Invalid brand description. Brand insert aborted.";
            System.out.println(errMsg);

            throw new Exception(errMsg);
        }

        /*System.out.println(brand.getDescription());
        System.out.println(brand.getId());*/

        int newBrandId = Brand.BRAND_NO_ID;

        // Objeto sem id passado
        if (brand.getId() == Brand.BRAND_NO_ID) {
            // Objeto novo sem id passado

            newBrandId = nextBrandId;

        } else if (getBrand(brand.getId()) != null) {
            // JA existe objeto com o id passado.
            String errMsg = "Brand with id " + brand.getId() + " already exists. Brand insert aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        } else if (getBrand(brand.getId()) == null) {
            // Não existe objeto com o id passado

            if (brand.getId() < nextBrandId) {
                // ID passado menor que o próximo
                String errMsg = "Invalid brand id. Must be at least " + nextBrandId + ". Brand insert aborted.";

                System.out.println(errMsg);
                throw new Exception(errMsg);

            } else {
                newBrandId = brand.getId();
            }
        }

        Brand newBrand = new Brand(newBrandId, brand.getDescription());

        brandsList.add(newBrand);

        if (saveOnDatabase) {
            FileMgmt.saveBrand(newBrand);
        }

        nextBrandId = newBrand.getId() + 1;

        System.out.println("Brand inserted successfully: id: " + newBrand.getId() + " description: " + newBrand.getDescription() + ".");

        return newBrand;
    }

    public static Brand insertBrand(Brand brand) throws Exception {
        return insertBrand(brand, true);
    }

    public static void removeBrandById(int id, boolean saveOnDatabase) throws Exception {
        int removeId = -1;
        for (int i = 0; i < brandsList.size(); i++) {
            if (brandsList.get(i).getId() == id) {
                removeId = i;
                break;
            }
        }

        if (removeId > -1) {
            System.out.println("Brand removed successfully: id: " + brandsList.get(removeId).getId() + " brand description: " + brandsList.get(removeId).getDescription() + ".");
            brandsList.remove(removeId);

            if(saveOnDatabase) {
                FileMgmt.saveBrands(brandsList);
            }
        } else {
            String errMsg = "Brand with id " + id + " not found. Brand remove aborted.";
            System.out.println(errMsg);

            throw new Exception(errMsg);
        }

    }

    public static void removeBrandById(int id) throws Exception {
        removeBrandById(id, true);
    }

    public static ArrayList<Brand> getBrandsList(){
        return brandsList;
    }

    public static Brand searchBrand(int brandId) throws Exception {
        if (getBrand(brandId) != null) {
            return getBrand(brandId);
        }
        else{
            String errMsg = "Brand with id " + brandId + " not found.";
            System.out.println(errMsg);

            throw new Exception(errMsg);
        }
    }

    public static Brand getBrand(int id) {

        for (Brand brand : brandsList) {

            if(brand.getId() == id) {
                return brand;
            }
        }

        return null;
    }

    public static boolean brandExists(int brandId) {
        Brand brand = getBrand(brandId);

        return brand != null;
    }

    public static Brand updateBrand(int brandId, DTOUpdateAttrib updateObj) throws Exception {
        return updateBrand(brandId, updateObj, true);
    }

    public static Brand updateBrand(int brandId, DTOUpdateAttrib updateObj, boolean saveOnDatabase) throws Exception {
        Brand brand = getBrand(brandId);

        if (brand == null) {
            String errMsg ="Brand with id " + brandId + " not found. Brand update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        }

        if (updateObj.attribName == null || updateObj.attribValue == null) {
            String errMsg = "Invalid attribName or attribValue. Both are required. Brand update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        }

        if (updateObj.attribName.equals("description")) {
            brand.setDescription(updateObj.attribValue);
            System.out.println("Brand updated successfully: id: " + brand.getId() +
                    " attribute name: " + updateObj.attribName + " attribute value: " + updateObj.attribValue);
            if (saveOnDatabase) {
                FileMgmt.saveBrands(brandsList);
            }

            return brand;
        } else {
            String errMsg = "Brand at id " + brandId + " invalid attribute " + updateObj.attribName
                    + ". Brand update aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        }
    }

    public static Brand modifyBrand(int id, Brand brand) throws Exception {
        return modifyBrand(id, brand, true);
    }

    public static Brand modifyBrand(int id, Brand brand, boolean saveOnDatabase) throws Exception {
        Brand modifyBrand = getBrand(id);

        if (modifyBrand == null) {
            String errMsg = "Brand with id " + id + " not found. Brand modify aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        if (brand.getDescription() == null) {
            String errMsg = "Invalid sent attribute. Attribute description is required. Brand modify aborted.";

            System.out.println(errMsg);
            throw new Exception(errMsg);

        } else {

            modifyBrand.setDescription(brand.getDescription());

            System.out.println("Brand successfully modified: id: " + modifyBrand.getId() +
                    " description: " + modifyBrand.getDescription() + ".");

            if (saveOnDatabase) {
                FileMgmt.saveBrands(brandsList);
            }

            return modifyBrand;
        }

    }

    public static void loadSavedBrands() throws Exception {

        ArrayList<ArrayList<String>> savedBrands = FileMgmt.getSavedBrands();

        if (savedBrands == null) return;

        for (ArrayList<String> rowData: savedBrands) {
            int id = Integer.parseInt(rowData.get(FileMgmt.BRAND_COLUMN_ID));
            String description = rowData.get(FileMgmt.BRAND_COLUMN_DESCRIPTION);

            Brand brand = new Brand(id, description);
            insertBrand(brand, false);
        }
    }
}
