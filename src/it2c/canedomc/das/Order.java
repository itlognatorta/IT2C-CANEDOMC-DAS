
package it2c.canedomc.das;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static jdk.nashorn.tools.ShellFunctions.input;

public class Order {
   
    
    public void Ordering(){
        Scanner sc = new Scanner(System.in);
         String response;

        do{
            System.out.println("-------------------");
            System.out.println("ORDER PANEL!");
            System.out.println("1. ADD ORDER");
            System.out.println("2. VIEW ORDER");
            System.out.println("3. UPDATE ORDER");
            System.out.println("4. DELETE ORDER");
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
            
            Order ord = new Order();
            
            switch(act){
                
               
                case 1:
                    
                    ord.addOrders();
                    ord.viewOrders();
                    
                    break;
                    
                case 2:
                   
                    ord.viewOrders();
                    
                    break;
                    
                case 3:
                    
                    ord.viewOrders();
                    ord.updateOrders();
                               
                    break;
            
                case 4: 
                    
                    ord.viewOrders();
                    ord.deleteOrders();
                    ord.viewOrders();
                                     
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
        private void addOrders(){
            Scanner sc = new Scanner(System.in);
            config conf = new config();
            Customer cust = new Customer();
            Product prod = new Product();
            
            System.out.println("-------------------");
            System.out.println("CUSTOMER TABLE");
            cust.viewCustomers();

    int cid = 0;
    boolean validCustomer = false;

  
    while (!validCustomer) {
        try {
            System.out.print("Enter Selected Customer ID: ");
            cid = Integer.parseInt(sc.nextLine());
            String csql = "SELECT COUNT(*) FROM customer WHERE id = ?";
            if (conf.getSingleValue(csql, cid) > 0) {
                validCustomer = true;
            } else {
                System.out.println("Customer does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Customer ID.");
        }
    }

    System.out.println("-------------------");
    System.out.println("PRODUCT TABLE");
    prod.viewProducts();

    int pid = 0;
    boolean validProduct = false;

 
    while (!validProduct) {
        try {
            System.out.print("Enter Selected Product ID: ");
            pid = Integer.parseInt(sc.nextLine());
            String psql = "SELECT COUNT(*) FROM product WHERE p_id = ?";
            if (conf.getSingleValue(psql, pid) > 0) {
                validProduct = true;
            } else {
                System.out.println("Product does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Product ID.");
        }
    }

    double quantity = 0;
    boolean validQuantity = false;

    
    while (!validQuantity) {
        try {
            System.out.print("Enter quantity: ");
            quantity = Double.parseDouble(sc.nextLine());
            if (quantity > 0) {
                validQuantity = true;
            } else {
                System.out.println("Quantity must be greater than 0. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric quantity.");
        }
    }     
        
            String priceqry = "SELECT p_price FROM product WHERE p_id = ?";
            double price = conf.getSingleValue(priceqry, pid);
            double due = price * quantity;
                    
            System.out.println("-----------------------");
            System.out.println("Total Due: "+due);
            System.out.println("-----------------------");
                                                                      
            
            LocalDate currdate = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = currdate.format(format);
            
            String status = "Pending";
            
                String orderqry = "INSERT INTO tbl_order (id, p_id, o_quantity, o_due, o_date, o_status) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                
                conf.addRecords(orderqry, cid, pid, quantity, due, date, status);

}
        
        
        public void viewOrders() {
            String Cmquery = "SELECT o_id, lname, p_name, o_due, o_date, o_status FROM tbl_order "
                    + "LEFT JOIN customer ON customer.id = tbl_order.id "
                    + "LEFT JOIN product ON product.p_id = tbl_order.p_id";
                  
        
        String[] hdrs = {"Order id","Customer LastName", "Product Name",  "Due", "Date", "Status"};
        String[] colmns = {"o_id","lname", "p_name",  "o_due", "o_date", "o_status"};
        config conf = new config();
        conf.viewRecords(Cmquery, hdrs, colmns);
        
        }
        
        private void updateOrders(){
        Scanner sc = new Scanner(System.in);
        Order or = new Order();
        config conf = new config();
        
        
       int oid = 0;
    boolean validOrder = false;

   
    while (!validOrder) {
        try {
            System.out.print("Enter Order ID: ");
            oid = Integer.parseInt(sc.nextLine());
            
            String osql = "SELECT COUNT(*) FROM tbl_order WHERE o_id = ?";
            if (conf.getSingleValue(osql, oid) > 0) { 
                validOrder = true;
            } else {
                System.out.println("Order ID does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric Order ID.");
        }
    }

   
    String statusQuery = "SELECT o_status FROM tbl_order WHERE o_id = ?";
    String currentStatus = conf.getSingleStringValue(statusQuery, oid);

    System.out.println("-------------------");
    or.viewOrders();

   
    String newStatus = "";
    boolean validStatus = false;

    while (!validStatus) {
        System.out.print("Enter new Status (Cancel, Pending, Done): ");
        newStatus = sc.nextLine().trim();

       
        if (!newStatus.equalsIgnoreCase("Cancel") && 
            !newStatus.equalsIgnoreCase("Pending") && 
            !newStatus.equalsIgnoreCase("Done")) {
            System.out.println("Invalid status. Please input valid Status. Cancel,Pending,Done .");
        } else if (currentStatus.equalsIgnoreCase("Cancel") || currentStatus.equalsIgnoreCase("Done")) {
            System.out.println("Status cannot be updated as the order is already marked as '" + currentStatus + "'.");
            return; 
        } else if (currentStatus.equalsIgnoreCase(newStatus)) {
            System.out.println("The order is already marked as '" + currentStatus + "'. No changes made.");
            return; 
        } else {
            validStatus = true; 
        }
    }

   
    String sql = "UPDATE tbl_order SET o_status = ? WHERE o_id = ?";
    conf.updateRecords(sql, newStatus, oid);

    System.out.println("Status successfully updated to '" + newStatus + "'.");
}
                               
              
    
         
          private void deleteOrders() {      
            Scanner sc = new Scanner(System.in);        
            config conf = new config();

            int oid = 0;
            boolean validInput = false;

            while (!validInput) {
            try {
            System.out.print("Enter Order ID to Delete: ");
            oid = Integer.parseInt(sc.nextLine()); 

           
            String sqlCheck = "SELECT COUNT(*) FROM tbl_order WHERE o_id = ?";
            if (conf.getSingleValue(sqlCheck, oid) > 0) { 
                validInput = true; 
            } else {
                System.out.println("Order ID does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric Order ID.");
        }
    }

   
    String sqlDelete = "DELETE FROM tbl_order WHERE o_id = ?";
    conf.deleteRecords(sqlDelete, oid);
    System.out.println("Order ID " + oid + " has been successfully deleted.");
}
}
