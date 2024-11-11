package it2c.canedomc.das;

import java.util.Scanner;
        
public class Customer {
    
    public void addCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config(); 
        System.out.println("Customers First name: ");
        String fname = sc.next();
        System.out.println("Customers Last name: ");
        String lname = sc.next();
        System.out.println("Customers Email: ");
        String email = sc.next();
        System.out.println("Customers Contact Number: ");
        String contact = sc.next();
        System.out.println("Customers Address: ");
        String address = sc.next();
        
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
        System.out.println("Enter customer ID: ");
        int id = sc.nextInt();
        
        System.out.println("Enter new Customer First Name:");
        String ufname = sc.next();
        
        System.out.println("Enter new Customer Last Name:");
        String ulname = sc.next();
        
        System.out.println("Enter new Customer Email:");
        String uemail = sc.next();
        
        System.out.println("Enter new Customer Contact Number:");
        String ucontact = sc.next();
        
        System.out.println("Enter new Customer Address:");
        String uaddress = sc.next();
        
        String sql = "UPDATE customer SET fname = ?, lname = ?, email = ?, Contactnum = ?, address = ? WHERE id = ?";
        
        config conf = new config();
        conf.updateRecords(sql, ufname, ulname, uemail, ucontact, uaddress, id);
        
        
    }

    private void deleteCustomers() {      
         Scanner sc = new Scanner(System.in);        
         System.out.print("Enter customer ID: ");
         int id = sc.nextInt();

        String sqlDelete = "DELETE FROM customer WHERE id = ?";
        
        config conf = new config();
        conf.deleteRecords(sqlDelete, id);
    
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
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            
            Customer das = new Customer();
            switch(action){
                
               
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
            
        }while(response.equals("yes"));
        
             
        
     
    }
    
}

