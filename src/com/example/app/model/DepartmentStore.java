package com.example.app.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DepartmentStore {

    //note: this is for the ordering part on the stores section. they order by ids.
    //NOTE:NAME_ORDER IS FOR BOTH SHOPS AND PRODUCTS
    private static final int NAME_ORDER = 1;
    private static final int PRICE_ORDER = 1;

    //MENU + KEYBOARD INPUT//
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model;
        Product p;
        int input = 12;//input variable NOTE: try making into a string input//

        //MENU IS SET IN DO WHILE LOOP SO IT CONTINUES TO RUN UNLESS MANUALLY ENDED BY USER//
        do {
            //try loop is for the exception handling of connection errors.
            try {
                model = Model.getInstance();
                System.out.println("The New You");
                System.out.println("Department Store Database");
                System.out.println();
                System.out.println("1. Create New Product.");
                System.out.println("2. View Existing Products.");
                System.out.println("3. Update Existing Products.");
                System.out.println("4. Delete Existing Products.");
                System.out.println("5. View Product Details.");
                //NOTE: THIS OPTION LETS THE USER SEE THE STORE NAMES OF A PRODUCT//
                System.out.println("6. View Products in Stores.");
                System.out.println();
                System.out.println("7. Create New Store.");
                //note: 8. used the order alphabetically here.
                System.out.println("8. View Existing Stores.");
                System.out.println("9. Update Existing Stores.");
                System.out.println("10. Delete Existing Stores.");
                System.out.println("11. View Store Details.");
                System.out.println();
                System.out.println("12. Exit.");
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
                        System.out.println("Creating New Product...");
                        createProduct(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 2: {
                        System.out.println("Retrieving Product Information...");
                        viewProducts(model, PRICE_ORDER);
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
                    //this is for the view single product option. not it uses a model called viewPRODUCT not the same as viewPRODUCTS for option 2//
                    case 5: {
                        System.out.println("Retriving Product...");
                        ViewProduct(keyboard, model);
                        System.out.println();
                        break;
                    }

                    //this is for the view stores under products option. i.e MY EXAMPLE of understanding how the 1:m table works.
                    case 6: {
                        System.out.println("Retriving Information...");
                        StoreProd(keyboard, model);
                        System.out.println();
                        break;
                    }

                    /////////STORES////////////
                    case 7: {
                        System.out.println("Creating Store...");
                        createShop(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 8: {
                        System.out.println("Retrieving Store Information...");
                        viewShops(model, NAME_ORDER);
                        System.out.println();
                        break;
                    }

                    case 9: {
                        System.out.println("Updating Store Information...");
                        editShop(keyboard, model);
                        System.out.println();
                        break;
                    }

                    case 10: {
                        System.out.println("Deleting Store Information...");
                        deleteShop(keyboard, model);
                        System.out.println();
                        break;
                    }
                    //this is for the view single store option.
                    case 11: {
                        System.out.println("Retriving Store...");
                        ViewShop(keyboard, model);
                        System.out.println();
                        break;
                    }
                    //this is for the view single store by id order option.
                    //case 12: {
                    //System.out.println("Retriving Store information...");
                    //ViewShop(keyboard, model);
                    //System.out.println();
                    //break;
                    //}
                }
            } //this is for the exception catching connected to the model class
            catch (DataAccessException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }

        } //WHILE LOOP ENDS APP IF 9 IS SELECTED//
        while (input != 12);
        System.out.println("Goodbye...");
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
    private static void viewProducts(Model mdl, int order) {
        //list for stored info in the getproducts model in prodtablegateway
        List<Product> products = mdl.getProducts();

        if (products.isEmpty()) {
            System.out.println("There are no products in the database.");
        } else {
            if (order == PRICE_ORDER) {
                Collections.sort(products);
            } //this is the code for the order by name imports the collections class//
            //note: the creation of an if else to add a comparator. 
            //the coparator will order the informatoion by price.
            else if (order == PRICE_ORDER) {
                //imported comparator from library
                //calls to method made in productpricecomparator.java
                Comparator<Product> cmptr = new ProductPriceComparator();
                Collections.sort(products, cmptr);
            }

            //this is the method for displaying the products below. Note: this is used so as not to repaeat the same code for multiple methods i.e the product by store id method below.
            displayProducts(products, mdl);
        }
    }

    //this method is called for view prtoduct and view product in storeid.
    //note change tyhe num,bers to change the spacign not the characters//
    private static void displayProducts(List<Product> products, Model mdl) {
        System.out.printf("%-15s %-60s %-110s %-12s %-12s %-20s\n", "Product ID", "productName", "description", "price", "salePrice", "Shop");
        for (Product pr : products) {
            Shop s = mdl.findShopByStoreID(pr.getStoreID());
            System.out.printf("%-15d %-60s %-110s %-12.2f %-12.2s %-20s\n",
                    pr.getProductID(),
                    pr.getProdName(),
                    pr.getDescription(),
                    pr.getPrice(),
                    pr.getSalePrice(),
                    //this is to print out a shop name. If there is nothing there, null. It will print an empty string.
                    (s != null) ? s.getShopName() : "");
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
        //;//viewShops(model);
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

    //METHOD FOR view single PRODUCTS    
    private static void ViewProduct(Scanner keyboard, Model model) throws DataAccessException {
        int productID = getInt(keyboard, "Enter the product ID of the product you want to see:    ", -1);
        Product p;

        p = model.findProductByProductID(productID);
        if (p != null) {
            Shop s = model.findShopByStoreID(p.getStoreID());
            System.out.println("Product:    " + p.getProdName());
            System.out.println("Description:    " + p.getDescription());
            System.out.println("Price:  " + p.getPrice());
            System.out.println("Sale Price: " + p.getSalePrice());
            System.out.println("Store:  " + ((s != null) ? s.getShopName() : ""));

        } else {
            System.out.println("Product not found");
        }
    }
    //END view single product METHOD

    //View store names under a product id information
    private static void StoreProd(Scanner keyboard, Model model) throws DataAccessException {
        int productID = getInt(keyboard, "Enter a Product ID to see if it is in stock:    ", -1);
        Product p;

        p = model.findProductByProductID(productID);
        if (p != null) {
            Shop s = model.findShopByStoreID(p.getStoreID());
            System.out.println("Product:    " + p.getProdName());
            System.out.println("Store:  " + ((s != null) ? s.getShopName() : ""));
            //System.out.println("Price:    " +((p != null)?  p.getPrice() : ""));
            //System.out.println("Sale Price:    " +((p != null)?  p.getSalePrice() : ""));
        } else {
            System.out.println("Product Not In Stock");
        }
    }
    //End View store names under a product id information//

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
        //String line;

        shopName = getString(keyb, "Enter Shop Name:   ");
        manFName = getString(keyb, "Enter Manager's First Name:    ");
        manLName = getString(keyb, "Enter Manager's Last Name:    ");
        phoneNo = getInt(keyb, "Enter Phone Number:    ", 0);
        //line = getString(keyb, "Enter Phone Number:    ");
        //phoneNo = Integer.parseInt(line);

        return new Shop(shopName, manFName, manLName, phoneNo);
    }

    //METHOD FOR VIEW PRODUCTS
    private static void viewShops(Model mdl, int order) {
        List<Shop> shops = mdl.getShops();

        if (shops.isEmpty()) {
            System.out.println("There are no stores in the database.");
        } else {
            Collections.sort(shops);
            //NOTE: THE NUMBERS REPRESENT THE AMOUNT OF CHARACTERS FOR THESE INPUTS//
            System.out.printf("%6s %20s %20s %20s %15s\n", "storeID", "shopName", "manFName", "manLName", "phoneNo");
            for (Shop sh : shops) {
                System.out.printf("%6d %20s %20s %20s %15s \n",
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

    //METHOD FOR EDIT Stores
    private static void editShop(Scanner kb, Model m) throws DataAccessException {
        int storeID = getInt(kb, "Enter Store ID to edit:    ", -1);
        Shop s;

        s = m.findShopByStoreID(storeID);
        if (s != null) {
            editShopDetails(kb, m, s);
            if (m.updateShop(s)) {
                System.out.println("Store Updated");
            } else {
                System.out.println("Store not Updated");
            }
        } else {
            System.out.println("Store not Found");
        }
    }

    private static void editShopDetails(Scanner keyb, Model m, Shop s) {
        String shopName, manFName, manLName;
        int phoneNo;

        shopName = getString(keyb, "Enter Store Name [" + s.getShopName() + "]:");
        manFName = getString(keyb, "Enter Manager's First Name [" + s.getManFName() + "]:");
        manLName = getString(keyb, "Enter Manager's Last Name [" + s.getManLName() + "]:");
        phoneNo = getInt(keyb, "Enter Phone No [" + s.getPhoneNo() + "]: ", 0);

        if (shopName.length() != 0) {
            s.setShopName(shopName);
        }
        if (manFName.length() != 0) {
            s.setManFName(manFName);
        }

        if (manLName.length() != 0) {
            s.setManLName(manLName);
        }

        if (phoneNo != s.getPhoneNo()) {
            s.setPhoneNo(phoneNo);
        }
    }
    //END METHOD FOR EDIT STORE//

    //METHOD FOR DELETE STORES    
    private static void deleteShop(Scanner keyboard, Model model) throws DataAccessException {
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

    //View single Store information
    private static void ViewShop(Scanner keyboard, Model model) throws DataAccessException {
        int storeID = getInt(keyboard, "Enter the Store ID of the product you want to see:    ", -1);
        Shop s;

        s = model.findShopByStoreID(storeID);
        if (s != null) {
            System.out.println("Shop:    " + s.getShopName());
            System.out.println("Manager's First Name:    " + s.getManFName());
            System.out.println("Manager's Last Name:  " + s.getManLName());
            System.out.println("Phone No: " + s.getPhoneNo());

            //products under a store Id///
            List<Product> productList = model.getProductsByStoreID(s.getStoreID());
            if (productList.isEmpty()) {
                System.out.println("There are no products in Stock.");
            } else {
                System.out.println("Products in stock:    ");
                displayProducts(productList, model);
            }

        } else {
            System.out.println("Store not found");
        }
    }
    //view single store information//

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
