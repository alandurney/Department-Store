package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DepartmentStore {

    //MENU + KEYBOARD INPUT//
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        Product p;

        int input;//input variable NOTE: try making into a string input//

        //MENU IS SET IN DO WHILE LOOP SO IT CONTINUES TO RUN UNLESS MANUALLY ENDED BY USER//
        do {
            System.out.println("Department Store Database");
            System.out.println("1. Create New Product.");
            System.out.println("2. View Existing Products.");
            System.out.println("3. Update Existing Products.");
            System.out.println("4. Delete Existing Products.");
            System.out.println("5. Create New Store.");
            System.out.println("6. View Existing Stores.");
            System.out.println("7. Update Existing Stores.");
            System.out.println("8. Delete Existing Store7s.");
            System.out.println("9. Exit.");
            System.out.println();

            System.out.println("Enter Command:   ");
            String line = keyboard.nextLine();
            input = Integer.parseInt(line);//input variable//

            System.out.println("Command:    " + input);
            System.out.println();

            //SWITCH LOOP USED TO RESPOND TO USER INPUT//
            switch (input) {
                case 1: {
                    System.out.println("Creating Product...");
                    //createProduct(keyboard, model);
                    System.out.println();
                    break;
                }

                case 2: {
                    System.out.println("Retrieving Product Information...");
                    //viewProducts(model);
                    System.out.println();
                    break;
                }

                case 3: {
                    System.out.println("Updating Product Information...");
                    //editProduct(keyboard, model);
                    System.out.println();
                    break;
                }

                case 4: {
                    System.out.println("Deleting Product Information...");
                    //deleteProduct(keyboard, model);
                    System.out.println();
                    break;
                    
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
                    editShop(keyboard, model);
                    System.out.println();
                    break;
                }

                case 8: {
                    System.out.println("Deleting Store Information...");
                    deleteShop(keyboard, model);
                    System.out.println();
                    break;

                    //NOTE: try creating otion for other iputs that are not in the loop//
                }
            }
        } //WHILE LOOP ENDS APP IF 5 IS SELECTED//
        while (input != 9);
        System.out.println("Exiting...");
        System.out.println();
    }

    //CREATE METHOD//
    /*private static void createProduct(Scanner keyboard, Model model) {
        Product p = readProduct(keyboard);
        if (model.addProduct(p)) {
            System.out.println("Product Added Successfully");
        } else {
            System.out.println("Error. Product Not Added to Database");
        }
    }*/
    //END CREATE METHOD


    //METHOD FOR VIEW PRODUCTS
    /*private static void viewProducts(Model mdl) {
        List<Product> products = mdl.getProducts();

        if (products.isEmpty()) {
            System.out.println("There are no products in the database.");
        } else {
            System.out.printf("%9s %20s %20s %7s %9s\n", "productID", "productName", "description", "price", "salePrice");
            for (Product pr : products) {
                System.out.printf("%5d %20s %20s %7.2f %9.2s \n",
                        pr.getProductID(),
                        pr.getProdName(),
                        pr.getDescription(),
                        pr.getPrice(),
                        pr.getSalePrice()
                );
            }
        }
    }*/
    //END VIEW METHOD

    /*private static Product readProduct(Scanner keyb) {
        String prodName, description;
        double price, salePrice;
        String line;

        prodName = getString(keyb, "Enter Product:   ");
        description = getString(keyb, "Enter Product Description:    ");
        line = getString(keyb, "Enter Price:    ");
        price = Double.parseDouble(line);
        line = getString(keyb, "Enter Sale Price:    ");
        salePrice = Double.parseDouble(line);
        
        return new Product(prodName, description, price, salePrice);
    }*/

    //METHOD FOR DELETE PRODUCTS    
    /*private static void deleteProduct(Scanner keyboard, Model model) {
        System.out.print("Enter the product ID of the product to delete:    ");
        int productID = Integer.parseInt(keyboard.nextLine());
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
    }*/
    //END DELETE METHOD

//METHOD FOR EDIT PRODUCTS
    /*private static void editProduct(Scanner kb, Model m) {
        System.out.println("Enter Product ID to edit:    ");
        int productID = Integer.parseInt(kb.nextLine());
        Product p;

        p = m.findProductByProductID(productID);
        if (p != null) {
            editProductDetails(kb, p);
            if (m.updateProduct(p)) {
                System.out.println("Product Updated");
            } else {
                System.out.println("Programmer not Updated");
            }
        } else {
            System.out.println("Product not Found");
        }
    }*/
//END METHOD FOR EDIT PRODUCT//
    
    /*private static void editProductDetails(Scanner keyb, Product p) {
        String prodName, description;
        double price;
        String line1;
        double salePrice;
        String line2;

        prodName = getString(keyb, "Enter Product Name [" + p.getProdName() + "]:");
        description = getString(keyb, "Enter Product Description [" + p.getDescription() + "]:");
        line1 = getString(keyb, "Enter Product Price [" + p.getPrice() + "]:");
        line2 = getString(keyb, "Enter Product Sale Price [" + p.getSalePrice() + "]:");

        if (prodName.length() != 0) {
            p.setProdName(prodName);
        }
        if (description.length() != 0) {
            p.setDescription(description);
        }

        if (line1.length() != 0) {
            price = Double.parseDouble(line1);
            p.setPrice(price);
        }

        if (line2.length() != 0) {
            salePrice = Double.parseDouble(line2);
            p.setSalePrice(salePrice);
        }
    }
    
    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }*/
    
    
    
    
    ///////////////////////////////////////STORE////////////////////////////////////
        
     //CREATE METHOD//
    /*private static void createShop(Scanner keyboard, Model model) {
        Shop s = readShop(keyboard);
        if (model.addShop(s)) {
            System.out.println("Shop Added Successfully");
        } else {
            System.out.println("Error. Shop Not Added to Database");
        }
    }*/
    //END CREATE METHOD
    
    //READ METHOD//////
    private static Shop readShop(Scanner keyb) {
        String address, manFName, manLName;
        int phoneNo;
        String line;

        address = getString(keyb, "Enter address:   ");
        manFName = getString(keyb, "Enter Manager's First Name:    ");
        manLName = getString(keyb, "Enter Manager's Last Name:    ");
        line = getString(keyb, "Enter Phone Number:    ");
        phoneNo = Integer.parseInt(line);
        
        return new Shop(address, manFName, manLName, phoneNo);
    }
    
     //METHOD FOR VIEW PRODUCTS
    private static void viewShops(Model mdl) {
        List<Shop> shops = mdl.getShops();

        if (shops.isEmpty()) {
            System.out.println("There are no stores in the database.");
        } else {
            System.out.printf("%9s %20s %20s %7s %9s\n", "shopID", "address", "manFName", "manLName", "phoneNo");
            for (Shop sh : shops) {
                System.out.printf("%5d %20s %20s %7.2f %9.2s \n",
                        sh.getShopID(),
                        sh.getAddress(),
                        sh.getManFName(),
                        sh.getManLName(),
                        sh.getPhoneNo()
                );
            }
        }
    }
    //END VIEW METHOD
}
