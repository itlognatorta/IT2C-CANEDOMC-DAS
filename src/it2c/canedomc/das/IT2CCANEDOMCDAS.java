
package it2c.canedomc.das;

import java.util.Scanner;

public class IT2CCANEDOMCDAS {
    
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
        
        conf.addCustomers(sql, fname, lname, email, contact, address);

    }
        
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String response;
        
        do{
            
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            
            switch(action){
                
                case 1:
                    
                    IT2CCANEDOMCDAS das = new IT2CCANEDOMCDAS();
                    das.addCustomers();
                    
                    break;
            }
            
            
            System.out.println("Do you want to continue? (yes/no): ");
            response = sc.nextLine();
        }while(response.equalsIgnoreCase("yes"));
        System.out.println("Thank You, See you again!");
        



        
        
     
    }
    
}
