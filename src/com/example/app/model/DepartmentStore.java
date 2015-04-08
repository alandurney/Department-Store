package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DepartmentStore {

    //MENU + KEYBOARD INPUT//
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model;
        Product p;
        int input=9;//input variable NOTE: try making into a string input//

        //MENU IS SET IN DO WHILE LOOP SO IT CONTINUES TO RUN UNLESS MANUALLY ENDED BY USER//
        do {
            //try loop is for the exception handling of connection errors.
            try {
                model = Model.getInstance();
                System.out.println("Department Store Database");
                System.out.println();
                System.out.println("1. Create New Product.");
                System.out.println("2. View Existing Products.");
                System.out.println("3. Update Existing Products.");
                System.out.println("4. Delete Existing Products.");
                System.out.println();
                System.out.println("5. Create New Store.");
                System.out.println("6. View Existing Stores.");
                System.out.println("7. Update Existing Stores.");
                System.out.println("8. Delete Existing Stores.");
                System.out.println();
                System.out.println("9. Exit.");
                System.out.println();

                //System.out.println("Enter Command:   ");
                //String line = keyboard.nextLine();
                //input = Integer.parseInt(line);//input variable//
                //referingt o the exeption handling declared at the bottom
                input = getInt(keyboard, "Enter Command:    ", 0);

                System.out.println("Command:    " + input);
                System.out.println();

                //SWITCH LOOP USED TO RESPOND TO USER INPUT//
                //note: input is linked to operation by its name//
                switch (input) {
                    case 1: {
                        System.out.println("Creating Product...");
                        createProduct(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 2: {
                        System.out.println("Retrieving Product Information...");
                        viewProducts(model);
                        System.out.println();
                        break;
                    }

                    case 3: {
                        System.out.println("Updating Product Information...");
                        editProduct(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 4: {
                        System.out.println("Deleting Product Information...");
                        deleteProduct(keyboard, model);
                        System.out.println();
                        break;
                    }
                    case 5: {
                        System.out.println("Creating Store...");
                        createShop(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 6: {
                        System.out.println("Retrieving Store Information...");
                        viewShops(model);
                        System.out.println();
                        break;
                    }

                    case 7: {
                        System.out.println("Updating Store Information...");
                        //editShop(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 8: {
                        System.out.println("Deleting Store Information...");
                        //deleteShop(keyboard, model);
                        System.out.println();
                        break;

                        //NOTE: try creating option for other iputs that are not in the loop//
                    }
                }
                }
                //this is for the exception catching connected to the model class
                catch(DataAccessException e){
                    System.out.println();
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            
        } //WHILE LOOP ENDS APP IF 9 IS SELECTED//
        while (input != 9);
        System.out.println("Exiting...");
        System.out.println();
    }

    //CREATE METHOD//
    private static void createProduct(Scanner keyboard, Model model) throws DataAccessException {
        Product p = readProduct(keyboard, model);
        if (model.addProduct(p)) {
            System.out.println("Product Added Successfully");
        } else {
            System.out.println("Error. Product Not Added to Database");
        }
    }
    //END CREATE METHOD

    //METHOD FOR VIEW PRODUCTS
    private static void viewProducts(Model mdl) {
        //list for stored info in the getproducts model in prodtablegateway
        List<Product> products = mdl.getProducts();

        if (products.isEmpty()) {
            System.out.println("There are no products in the database.");
        } else {
            System.out.printf("%9s %20s %20s %7s %9s %20\n", "productID", "productName", "description", "price", "salePrice");
            for (Product pr : products) {
                System.out.printf("%5d %20s %20s %7.2f %9.2s %20\n",
                        pr.getProductID(),
                        pr.getProdName(),
                        pr.getDescription(),
                        pr.getPrice(),
                        pr.getSalePrice()
                );
            }
        }
    }
    //END VIEW METHOD
    
    //NOTE: THIS IS TO TAKE IN THE USER INPUT FOR PRODUCT DETAILS.//
    private static Product readProduct(Scanner keyb, Model model) {
        String prodName, description;
        double price, salePrice;
        int storeID;
        //String line;

        prodName = getString(keyb, "Enter Product Name:   ");
        description = getString(keyb, "Enter Product Description:    ");
        price = getDouble(keyb, "Enter Product Price:      ", 0);
        salePrice = getDouble(keyb, "Enter Product Sale Price:     ", 0);
        //line = getString(keyb, "Enter Price:    ");
        //price = Double.parseDouble(line);
        //line = getString(keyb, "Enter Sale Price:    ");
        //salePrice = Double.parseDouble(line);
        //viewShops(model);
        //line = getString(keyb, "Enter Shop Id:    ");
        //storeID = Integer.parseInt(line);
        storeID = getInt(keyb, "Enter Shop ID:  ", -1);

        return new Product(prodName, description, price, salePrice, storeID);
    }
    //END READ PRODUCTS//

    //METHOD FOR DELETE PRODUCTS    
    private static void deleteProduct(Scanner keyboard, Model model) throws DataAccessException {
        //System.out.print("Enter the product ID of the product to delete:    ");
        //int productID = Integer.parseInt(keyboard.nextLine());
        int productID = getInt(keyboard, "Enter the product ID of the product to delete:    ", -1);
        Product p;

        p = model.findProductByProductID(productID);
        if (p != null) {
            if (model.removeProduct(p)) {
                System.out.println("Product Deleted");
            } else {
                System.out.println("Product not Deleted");
            }
        } else {
            System.out.println("Product not found");
        }
    }
    //END DELETE METHOD

//METHOD FOR EDIT PRODUCTS
    private static void editProduct(Scanner kb, Model m) throws DataAccessException {
        //System.out.println("Enter Product ID to edit:    ");
        //int productID = Integer.parseInt(kb.nextLine());
        int productID = getInt(kb, "Enter Product ID to edit:    ", -1);
        Product p;

        p = m.findProductByProductID(productID);
        if (p != null) {
            editProductDetails(kb, m, p);
            if (m.updateProduct(p)) {
                System.out.println("Product Updated");
            } else {
                System.out.println("Programmer not Updated");
            }
        } else {
            System.out.println("Product not Found");
        }
    }

    private static void editProductDetails(Scanner keyb, Model model, Product p) {
        String prodName, description;
        double price;
        //String line1;
        double salePrice;
        //String line2;
        int storeID;
        //String line3;

        prodName = getString(keyb, "Enter Product Name [" + p.getProdName() + "]:");
        description = getString(keyb, "Enter Product Description [" + p.getDescription() + "]:");
        price = getDouble(keyb, "Enter Product Price [" + p.getPrice() + "]: ", 0);
        salePrice = getDouble(keyb, "Enter Product Sale Price [" + p.getSalePrice() + "]: ", 0);
        //line1 = getString(keyb, "Enter Product Price [" + p.getPrice() + "]:");
        //line2 = getString(keyb, "Enter Product Sale Price [" + p.getSalePrice() + "]:");
        viewShops(model);
        storeID = getInt(keyb, "Enter Store ID [" + p.getStoreID() + "]: ", -1);
        //line3 = getString(keyb, "Enter Store ID [" + p.getStoreID() + "]:");

        if (prodName.length() != 0) {
            p.setProdName(prodName);
        }
        if (description.length() != 0) {
            p.setDescription(description);
        }

        if (price != p.getPrice()) {
            p.setPrice(price);
        }

        if (salePrice != p.getSalePrice()) {
            p.setSalePrice(salePrice);
        }

        if (storeID != p.getStoreID()) {
            p.setStoreID(storeID);
        }
    }
    //END METHOD FOR EDIT PRODUCT//

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    ///////////////////////////////////////STORE////////////////////////////////////
    //CREATE METHOD//
    private static void createShop(Scanner keyboard, Model model) throws DataAccessException {
        Shop s = readShop(keyboard);
        if (model.addShop(s)) {
            System.out.println("Shop Added Successfully");
        } else {
            System.out.println("Error. Shop Not Added to Database");
        }
    }
    //END CREATE METHOD

    //READ METHOD this is for the prompts that appear on create//////
    private static Shop readShop(Scanner keyb) {
        String shopName, manFName, manLName;
        int phoneNo;
        String line;

        shopName = getString(keyb, "Enter Shop Name:   ");
        manFName = getString(keyb, "Enter Manager's First Name:    ");
        manLName = getString(keyb, "Enter Manager's Last Name:    ");
        phoneNo = getInt(keyb, "Enter Phone Number:    ", -1);
        //line = getString(keyb, "Enter Phone Number:    ");
        //phoneNo = Integer.parseInt(line);

        return new Shop(shopName, manFName, manLName, phoneNo);
    }

    //METHOD FOR VIEW PRODUCTS
    private static void viewShops(Model mdl) {
        List<Shop> shops = mdl.getShops();

        if (shops.isEmpty()) {
            System.out.println("There are no stores in the database.");
        } else {
            //NOTE: THE NUMBERS REPRESENT THE AMOUNT OF CHARACTERS FOR THESE INPUTS//
            System.out.printf("%6s %20s %20s %20s %9s\n", "storeID", "shopName", "manFName", "manLName", "phoneNo");
            for (Shop sh : shops) {
                System.out.printf("%6d %20s %20s %20s %9s \n",
                        sh.getStoreID(),
                        sh.getShopName(),
                        sh.getManFName(),
                        sh.getManLName(),
                        sh.getPhoneNo()
                );
            }
        }
    }
    //END VIEW METHOD

    //METHOD FOR DELETE STORES    
    private static void deleteShop(Scanner keyboard, Model model) throws DataAccessException {
        //System.out.print("Enter the store ID of the store to delete:    ");
        //int storeID = Integer.parseInt(keyboard.nextLine());
        int storeID = getInt(keyboard, "Enter the store ID of the store to delete:    ", -1);
        Shop s;

        s = model.findShopByStoreID(storeID);
        if (s != null) {
            if (model.removeShop(s)) {
                System.out.println("Store Deleted");
            } else {
                System.out.println("Store not Deleted");
            }
        } else {
            System.out.println("Store not found");
        }
    }
    //END DELETE METHOD

    //EXCEPTION HANDLING TO PREVENT EXCEPTION ERRORS ON THE INPUT OF DIFFERENT CHARACTERS into Int prompts//
    private static int getInt(Scanner keyb, String prompt, int defaultValue) {
        int input = defaultValue;
        boolean finished = false;
        //this is to make a response to a bad input//
        do {
            try {
                System.out.print(prompt);
                String line = keyb.nextLine();
                if (line.length() > 0) {
                    input = Integer.parseInt(line);
                }
                finished = true;
            } catch (NumberFormatException e) {
                System.out.println("Exception:  " + e.getMessage());
            }
        } while (!finished);

        return input;
    }

    //EXCEPTION HANDLING TO PREVENT EXCEPTION ERRORS ON THE INPUT OF DIFFERENT CHARACTERS into Double price  prompts//
    private static double getDouble(Scanner keyb, String prompt, double defaultValue) {
        double input = defaultValue;
        boolean finished = false;
        //this is to make a response to a bad input//
        do {
            try {
                System.out.print(prompt);
                String line = keyb.nextLine();
                if (line.length() > 0) {
                    input = Double.parseDouble(line);
                }
                finished = true;
            } catch (NumberFormatException e) {
                System.out.println("Exception:  " + e.getMessage());
            }
        } while (!finished);

        return input;
    }
}
