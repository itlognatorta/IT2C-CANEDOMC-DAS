package it2c.canedomc.das;

import java.util.Scanner;
        
public class Customer {
    
    public void addCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config(); 
        System.out.print("Customers First name: ");
        String fname = sc.next();
        System.out.print("Customers Last name: ");
        String lname = sc.next();
        String email;
    while (true) {
        System.out.print("Customers Email (e.g., example@domain.com): ");
         email = sc.next();
        if (email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) { 
            break;
        } else {
            System.out.print("Invalid email format! Please try again.");
        }
    }       
    String contact;
   while (true) {
    System.out.print("Customer's Contact Number (11 digits only): ");
    contact = sc.next();
    if (contact.matches("\\d{11}")) { 
        break;
    } else {
        System.out.println("Invalid input! Please enter a valid 11-digit number.");
    }
   }
       String address;
    while (true) {
    System.out.print("Customer's Address: ");
    address = sc.nextLine();
   
    if (!address.trim().isEmpty() && address.matches("^[a-zA-Z0-9 ]+$")) {
        break;
    }
    System.out.println("Only letters, numbers, and spaces are allowed.");
}
        
        String sql = "INSERT INTO customer (fname, lname, email, contactnum, address) VALUES (?, ?, ?, ?, ?)";
        
        conf.addRecords(sql, fname, lname, email, contact, address);

    }
    
    public void viewCustomers() {
        String Cmquery = "SELECT * FROM customer";
        String[] hdrs = {"ID", "Firstname", "Lastname", "Email", "Contact Number", "Address"};
        String[] colmns = {"id", "fname", "lname", "email", "Contactnum", "address"};
        config conf = new config();
        conf.viewRecords(Cmquery, hdrs, colmns);
    }
    
    private void updateCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id = 0;
        boolean validCustomer = false;

  
    while (!validCustomer) {
        try {
            System.out.print("Enter Existing Customer ID: ");
            id = Integer.parseInt(sc.nextLine());
            String csql = "SELECT COUNT(*) FROM customer WHERE id = ?";
            if (conf.getSingleValue(csql, id) > 0) {
                validCustomer = true;
            } else {
                System.out.println("Customer does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Customer ID.");
        }
    }
        
        System.out.println("Enter new Customer First Name:");
        String ufname = sc.next();
        
        System.out.println("Enter new Customer Last Name:");
        String ulname = sc.next();
        
         String uemail;
    while (true) {
        System.out.print("Customers Email (e.g., example@domain.com): ");
         uemail = sc.next();
        if (uemail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) { 
            break;
        } else {
            System.out.print("Invalid email format! Please try again.");
        }
    }
        
         String ucontact;
    while (true) {
        System.out.print("Customers Contact Number (digits only): ");
        ucontact = sc.next();
        if (ucontact.matches("\\d+")) { 
            break;
        } else {
            System.out.print("Invalid input! Please enter digits only.");
        }
    }
        
        System.out.println("Enter new Customer Address:");
        String uaddress = sc.next();
        
        String sql = "UPDATE customer SET fname = ?, lname = ?, email = ?, Contactnum = ?, address = ? WHERE id = ?";
       
        conf.updateRecords(sql, ufname, ulname, uemail, ucontact, uaddress, id);
        
    
    }

    private void deleteCustomers() {      
         Scanner sc = new Scanner(System.in);        
         config conf = new config();
            int id = 0;
            boolean validInput = false;

             while (!validInput) {
        try {
            System.out.print("Enter Customer ID to Delete: ");
            id = Integer.parseInt(sc.nextLine());

            String sqlCheck = "SELECT COUNT(*) FROM customer WHERE id = ?";
            if (conf.getSingleValue(sqlCheck, id) > 0) {
                String sqlReferenceCheck = "SELECT COUNT(*) FROM tbl_order WHERE id = ?";
                if (conf.getSingleValue(sqlReferenceCheck, id) > 0) {
                    System.out.println("Customer cannot be deleted because it is referenced in other records (e.g., tbl_orders).");
                } else {
                    validInput = true;
                }
            } else {
                System.out.println("Customer ID does not exist. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric Customer ID.");
        }
    }

   
    String sqlDelete = "DELETE FROM customer WHERE id = ?";
    conf.deleteRecords(sqlDelete, id);
    System.out.println("Customer " + id + " has been successfully deleted.");
}
        
                
            public void Customers(){
                
            Scanner sc = new Scanner(System.in);
            String response;

        do{
            System.out.println("----------------------");
            System.out.println("CUSTOMER PANEL!");
            System.out.println("1. ADD CUSTOMER");
            System.out.println("2. VIEW CUSTOMER");
            System.out.println("3. UPDATE CUSTOMER");
            System.out.println("4. DELETE CUSTOMER");
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
            
            Customer das = new Customer();
            switch(act){
                
               
                case 1:
                    
                    
                    das.addCustomers();
                    
                    break;
                    
                case 2:
                   
                    das.viewCustomers();                 
                    
                    break;
                    
                case 3:
                    
                    das.viewCustomers();   
                    das.updateCustomers();
                    break;
            
                case 4: 
                    das.viewCustomers();
                    das.deleteCustomers();
                    das.viewCustomers();
                    break;
                    
                case 5:    
                     System.out.println("\nReturning to Main System...\n");
                    return;
                    
                    default:
                    System.out.println("Invalid option. Please try again. ");              
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

