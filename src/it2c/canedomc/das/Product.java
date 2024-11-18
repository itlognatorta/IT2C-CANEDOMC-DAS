
package it2c.canedomc.das;

import java.util.Scanner;


public class Product {
    
    public void addProducts(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
                     
        System.out.print("Product Name: ");
        String pname = sc.next();
        System.out.print("Product Price: ");
        double pprice = sc.nextDouble();      
        System.out.print("Product Category: ");
        String pcateg = sc.next();
        System.out.print("Status: ");
        String pstats = sc.next();
        
        String sql = "INSERT INTO product (p_name, p_price, p_category, p_status) VALUES (?, ?, ?, ?)";
        
        conf.addRecords(sql, pname, pprice, pcateg, pstats);
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
        
            System.out.println("Enter Product ID: ");
            int pid = sc.nextInt();
            
            while(conf.getSingleValue("SELECT p_id FROM product WHERE p_id = ?", pid) == 0 ){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Product ID Again: ");
            pid = sc.nextInt();
        }
            
            System.out.print("Enter new Product Name: ");
            String uname = sc.next();
            
            System.out.print("Enter new Product Price: ");
            String uprice = sc.next();
            
            System.out.print("Enter new Product Category; ");
            String ucateg = sc.next(); 
            
            System.out.println("Enter new Product Status: ");
            String ustats = sc.next();
            
            String sql = "UPDATE product SET p_name = ?, p_price = ?, p_category = ?, p_status = ? WHERE p_id = ?";
                   
            conf.updateRecords(sql, uname, uprice, ucateg, ustats, pid);           
            
        }
        
        private void deleteProducts(){
               
         Scanner sc = new Scanner(System.in);        
         config conf = new config();
         System.out.print("Enter Product ID: ");
         int pid = sc.nextInt();
         
        while(conf.getSingleValue("SELECT p_id FROM product WHERE p_id = ?", pid) == 0 ){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Product ID Again: ");
        pid = sc.nextInt();
        
        }
        String sqlDelete = "DELETE FROM product WHERE p_id = ?";
        
       
        conf.deleteRecords(sqlDelete, pid);
    } 
        
        public void Products (){
        Scanner sc = new Scanner(System.in);
        String response;
        
        do{
            System.out.println("--------------------");
            System.out.println("PRODUCT PANEL!");
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            
            Product prod = new Product();
            switch(action){
                
                
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
