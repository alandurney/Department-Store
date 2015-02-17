package com.example.app.model;

import java.util.Scanner;

public class DepartmentStore {

        //MENU + KEYBOARD INPUT//
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        Product p;
        
        int input;//input variable NOTE: try making into a string input//
        
        //MENU IS SET IN DO WHILE LOOP SO IT CONTINUES TO RUN UNLESS MANUALLY ENDED BY USER//
        do{
        System.out.println("Product Information Database");
        System.out.println("1. Create New Product.");
        System.out.println("2. View Existing Products.");
        System.out.println("3. Update Existing Products.");
        System.out.println("4. Delete Existing Products.");
        System.out.println("5. Exit.");
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
                 
                //NOTE: try creating otion for other iputs that are not in the loop//
                }
            }
        }
    }
                                                                

                                                                                                                                                                                                

                                                                                                                                
        //WHILE LOOP ENDS APP IF 5 IS SELECTED//
        while (input != 5);
        System.out.println("Exiting...");
        System.out.println();
    }  
    
//CREATE METHOD//
        private static void createProduct(Scanner keyboard, Model model){
            Product p = readProduct(keyboard);
            if(model.addProduct(p)){
            System.out.println("Product Added Successfully");
            }
            else{
            System.out.println("Error. Product Not Added to Database");  
            }
        }
 //END CREATE METHOD

     //METHOD FOR VIEW PRODUCTS
        private static void viewProducts(Model mdl) {
        List<Product> products = model.getProducts();
                    
        if (products.isEmpty()) {
            System.out.println("There are no products in the database.");
        }
        else{
            System.out.printf("%5s %20s %20s %15s %12s %d %.2d\n", "Id" "productID","productName", "description", "price", "salePrice");
                for(Product pr : products){
                    System.out.printf("%5s %20s %20s %15s %12s %d %.2d\n",//GET SQL CONNECTION FROM GLOBAL LIBRARY//
                        pr.getId();
                        pr.getProductID(),
                        pr.getProductName(),
                        pr.getDescription());
                        pr.getPrice();
                        pr.getSalePrice();
                        }
                    }
        }
 //END VIEW METHOD

private static Product readProduct(Scanner keyb){
    String prodName, description;
    double price, salePrice;
    String line;

    prodName = getString(keyb, "Enter Product:   ");
    description = getString(keyb, "Enter Product Description:    ");
    line = getString(keyb, "Enter Price:    ");
    price = Integer.parseInt(line);
    line = getString(keyb, "Enter Sale Price:    ");
    salePrice = Integer.parseInt(line);
}

 //METHOD FOR DELETE PRODUCTS    
    private static void deleteProduct(Scanner keyboard, Model model) {
        System.out.print("Enter the product ID of the product to delete:    ");
        int productID = Integer.parseInt(keyboard.nextLine());
        Product p;

        p = model.findProductByProductID(productID);
            if (p != null) {
                if(model.removeProduct(p)) {
                    System.out.println("Product Deleted");
                }
                else {
                    System.out.println("Product not Deleted");
                }
            }
            else{
               System.out.println("Product not found");
            }
        }
 //END DELETE METHOD

    
//METHOD FOR EDIT PRODUCTS
    private static void editProduct(Scanner kb, Model m) {
       System.out.println("Enter Product ID to edit:    ");
       int productID = Integer.parseInt(kb.nextLine());
       Product p;
       
       p = m.findProductByProductID(productID);
       if(p != null){
           editProductDetails(kb, p);
           if(m.updateProduct(p)) {
               System.out.println("Product Updated");
           {
            else{
               System.out.println("Programmer not Updated");
                }
           }
           else{
                   System.out.println("Product not Found");
                   }
       }
    }
    //END METHOD FOR EDIT PRODUCT//

    private static void viewProducts(Model model) {
        List<Product> products = model.getProducts();
        for (Product pr : products) {
            System.out.println("Name:    " + pr.getProductName());
} 

    private static void viewProduct (Model model) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static void editProductDetails(Scanner keyb, Product p){
    String productName, description;
    double price;
    String line1;
    double salePrice;
    String line2;

    prodName = getString(keyb, "Enter Product Name [" + p.getProdName() + "]:");
    description = getString(keyb, "Enter Product Description [" + p.getDescription() + "]:");
    line1 = getString(keyb, "Enter Product Price [" + p.getPrice() + "]:");
    line2 = getString(keyb, "Enter Product Sale Price [" + p.getSalePrice() + "]:");

    if(prodName.length() != o){
         p.setProdName(productName);
    }
    if(description.length() != o){
         p.setDescription(description);
    }

    if(line1.length() != o){
        price = Double.parseDouble(line1);
         p.setPrice(price);
    }

    
    if(line2.length() != o){
        salePrice = Double.parseDouble(line2);
         p.setSalePrice(salePrice);
    }
}
}