
package it2c.canedomc.das;

import java.util.Scanner;


public class Reports {
        
        config conf = new config();
    
       public void generalReport() {
        String query = "SELECT o_id, lname, o_due, o_date FROM tbl_order "
                     + "LEFT JOIN customer ON customer.id = tbl_order.id "
                     + "LEFT JOIN product ON product.p_id = tbl_order.p_id";
        
        String[] headers = {"Order ID", "Customer Last Name", "Due Amount", "Order Date"};
        String[] columns = {"o_id", "lname", "o_due", "o_date"};
        
        System.out.println("=== GENERAL ORDER REPORT ===");
        conf.viewRecords(query, headers, columns);
    }

   
    public void specificReport() {
        Scanner sc = new Scanner(System.in);
        
     
        Customer cust = new Customer();
        System.out.println("=== CUSTOMER LIST ===");
        cust.viewCustomers();
        
       
        System.out.print("Enter Customer ID for specific report: ");
        int customerId = sc.nextInt();

      
        String checkCustomerQuery = "SELECT id FROM customer WHERE id = ?";
        if (conf.getSingleValue(checkCustomerQuery, customerId) == 0) {
            System.out.println("Customer does not exist.");
            return;
        }

       
        String query = "SELECT o_id, fname, p_name, o_due, o_date, o_status FROM tbl_order " 
                + "LEFT JOIN customer ON customer.id = tbl_order.id " 
                + "LEFT JOIN product ON product.p_id = tbl_order.p_id " 
                + "WHERE tbl_order.id = ?";
        
        String[] hdrs = {"Order ID", "Customer's Name", "Product Name", "Due Amount", "Order Date", "Order Status"};
        String[] colmns = {"o_id","fname","p_name","o_due","o_date","o_status"};
        
        System.out.println("=== SPECIFIC REPORT FOR CUSTOMER ID: " + customerId + " ===");
        conf.viewRecords1(query, hdrs, colmns, customerId);
    }

    
    public void Report(){
        
        Scanner sc = new Scanner(System.in);
        String response;
        
     do{    
         System.out.println("-----------------------");
         System.out.println("   ORDER REPORTS    ");
         System.out.println("-----------------------");
         System.out.println("1.GENERALALIZE REPORTS");
         System.out.println("2.SPECIFIC REPORTS");
         System.out.println("3.EXIT REPORTS PANEL");
         
         
              int act = 0;

            while (act < 1 || act > 3) {
                System.out.print("Enter action: ");
                
                if (sc.hasNextInt()) {
                    act = sc.nextInt();

                    
                    if (act < 1 || act > 3) {
                        System.out.println("Invalid option! Please enter a number 1-5 only.");
                    }
                } else {
                   
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); 
                }
            }
         
         Reports rep = new Reports();
         
          switch(act){
                
               
                case 1:
                    
                    rep.generalReport();
                    
                    
                    
                    break;
                    
                case 2:
                   
                    rep.specificReport();
                              
                    
                    break;
                    
                case 3:
                    
                  
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