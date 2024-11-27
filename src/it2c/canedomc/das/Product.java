
package it2c.canedomc.das;

import java.util.Scanner;


public class Product {
    
    public void addProducts(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
                     
       String pname;
    while (true) {
        System.out.print("Product Name: ");
        pname = sc.nextLine();
        if (!pname.trim().isEmpty() && pname.matches("^[a-zA-Z0-9 ]+$")) {
            break;
        }
        System.out.println("Invalid product name. Only letters, numbers, and spaces are allowed.");
    }

    double pprice = 0.0;
    while (true) {
        System.out.print("Product Price: ");
        if (sc.hasNextDouble()) {
            pprice = sc.nextDouble();
            if (pprice > 0) {
                break;
            } else {
                System.out.println("Price must be greater than 0.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid numeric price.");
            sc.next(); 
        }
    }
    sc.nextLine(); 

    String pcateg;
    while (true) {
        System.out.print("Product Category: ");
        pcateg = sc.nextLine();
        if (!pcateg.trim().isEmpty() && pcateg.matches("^[a-zA-Z ]+$")) {
            break;
        }
        System.out.println("Invalid category. Only letters and spaces are allowed.");
    }

    String pstats;
    while (true) {
        System.out.print("Status (Available/Unavailable): ");
        pstats = sc.nextLine();
        if (pstats.equalsIgnoreCase("Available")) {
            break;
        }
        System.out.println("Invalid status. Please enter 'Available'.");
    }

    String sql = "INSERT INTO product (p_name, p_price, p_category, p_status) VALUES (?, ?, ?, ?)";
    conf.addRecords(sql, pname, pprice, pcateg, pstats);

    System.out.println("Product added successfully!");
}
    
        public void viewProducts(){
            
        String Cmquery = "SELECT * FROM product";
        String[] hdrs = {"ID", "Product Name", "Price", "Category", "Satus"};
        String[] colmns = {"p_id", "p_name", "p_price", "p_category", "p_status"};
        config conf = new config();
        conf.viewRecords(Cmquery, hdrs, colmns);
  
    }
            
 
        private void updateProducts(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int pid = 0;
        boolean validCustomer = false;

  
    while (!validCustomer) {
        try {
            System.out.print("Enter Existing Product ID: ");
            pid = Integer.parseInt(sc.nextLine());
            String psql = "SELECT COUNT(*) FROM product WHERE p_id = ?";
            if (conf.getSingleValue(psql, pid) > 0) {
                validCustomer = true;
            } else {
                System.out.println("Product does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Product ID.");
        }
    }
            
            String uname;
    while (true) {
        System.out.print("Product Name: ");
        uname = sc.nextLine();
        if (!uname.trim().isEmpty() && uname.matches("^[a-zA-Z0-9 ]+$")) {
            break;
        }
        System.out.println("Invalid product name. Only letters, numbers, and spaces are allowed.");
    }

    double uprice = 0.0;
    while (true) {
        System.out.print("Product Price: ");
        if (sc.hasNextDouble()) {
            uprice = sc.nextDouble();
            if (uprice > 0) {
                break;
            } else {
                System.out.println("Price must be greater than 0.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid numeric price.");
            sc.next(); 
        }
    }
    sc.nextLine(); 

    String ucateg;
    while (true) {
        System.out.print("Product Category: ");
        ucateg = sc.nextLine();
        if (!ucateg.trim().isEmpty() && ucateg.matches("^[a-zA-Z ]+$")) {
            break;
        }
        System.out.println("Invalid category. Only letters and spaces are allowed.");
    }

    String ustats;
    while (true) {
        System.out.print("Status (Available): ");
        ustats = sc.nextLine();
        if (ustats.equalsIgnoreCase("Available")) {
            break;
        }
        System.out.println("Invalid status. Please enter 'Available'.");
    }

            
            String sql = "UPDATE product SET p_name = ?, p_price = ?, p_category = ?, p_status = ? WHERE p_id = ?";
                   
            conf.updateRecords(sql, uname, uprice, ucateg, ustats, pid);           
            
        }
        
        private void deleteProducts(){
               
         Scanner sc = new Scanner(System.in);        
         config conf = new config();
         int pid = 0;
            boolean validInput = false;

             while (!validInput) {
        try {
            System.out.print("Enter Product ID to Delete: ");
            pid = Integer.parseInt(sc.nextLine());

            String sqlCheck = "SELECT COUNT(*) FROM product WHERE p_id = ?";
            if (conf.getSingleValue(sqlCheck, pid) > 0) {
                String sqlReferenceCheck = "SELECT COUNT(*) FROM tbl_order WHERE p_id = ?";
                if (conf.getSingleValue(sqlReferenceCheck, pid) > 0) {
                    System.out.println("Product cannot be deleted because it is referenced in other records (e.g., tbl_orders).");
                } else {
                    validInput = true; 
    
                }
            } else {
                System.out.println("Product ID does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric Product ID.");
        }
    }

   
    String sqlDelete = "DELETE FROM product WHERE p_id = ?";
    conf.deleteRecords(sqlDelete, pid);
    System.out.println("Customer " + pid + " has been successfully deleted.");
}
        
        public void Products (){
        Scanner sc = new Scanner(System.in);
        String response;
        
        do{
            System.out.println("--------------------");
            System.out.println("PRODUCT PANEL!");
            System.out.println("1. ADD PRODUCT");
            System.out.println("2. VIEW PRODUCT");
            System.out.println("3. UPDATE PRODUCT");
            System.out.println("4. DELETE PRODUCT");
            System.out.println("5. EXIT");
            
           
              int act = 0;

            while (act < 1 || act > 5) {
                System.out.print("Enter action: ");
                
                if (sc.hasNextInt()) {
                    act = sc.nextInt();

                    
                    if (act < 1 || act > 5) {
                        System.out.println("Invalid option! Please enter a number 1-5 only.");
                    }
                } else {
                   
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); 
                }
            }
            
            Product prod = new Product();
            switch(act){
                
                
                case 1:
                    
                    
                    prod.addProducts();
                    
                    break;
                    
                case 2:
                   
                    prod.viewProducts();                 
                    
                    break;
                    
                case 3:
                    
                    prod.viewProducts();   
                    prod.updateProducts();
                    break;
            
                case 4: 
                    prod.viewProducts();
                    prod.deleteProducts();
                    prod.viewProducts();
                    break;
            
                case 5:
                    
                    System.out.println("\nReturning to Main System...\n");
                    return;
                    
                    default:
                    System.out.print("Invalid option. Please try again.");
                    break;
            }
            
             System.out.println("Do you want to continue? (yes/no): ");
            response = sc.next();

   
    while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
        System.out.println("Invalid input! Please answer only 'yes' or 'no'.");
        System.out.print("Do you want to continue? (yes/no): ");
        response = sc.next();
    }

} while (response.equalsIgnoreCase("yes"));
        
             
        
     
    }
    
}
