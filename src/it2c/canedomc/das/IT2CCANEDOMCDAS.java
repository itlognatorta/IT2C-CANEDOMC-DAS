
package it2c.canedomc.das;

import static java.lang.System.exit;
import java.util.Scanner;

public class IT2CCANEDOMCDAS {
    
  
    public static void main(String[] args) {
        
        
       Scanner sc = new Scanner(System.in);
        String response;
        boolean exit = true; 
        do {

            System.out.println("-------------------------------------");
            System.out.println("|  WELCOME TO DELIVERY APP SYSTEM |");
            System.out.println("-------------------------------------");
            System.out.println("1. CUSTOMER");
            System.out.println("2. PRODUCT");
            System.out.println("3. ORDER");
            System.out.println("4. REPORTS");
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
 


            switch (act) {

                case 1:
                    
                    Customer custom = new Customer();

                    custom.Customers();

                    break;

                case 2:
                    
                   Product prod = new Product();
                   
                   prod.Products();
                   break;   
                  
                case 3:
                    
                    Order ord = new Order();
                    ord.Ordering();
                   
                    break;

                case 4:
                    
                     Reports rep = new Reports();   
                     rep.Report();
                     
                    
                    
                    
                    break;

                case 5:
                    System.out.println("Exiting the program. Type 'yes to coninue: !");
                    response = sc.next();
                    if(response.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
                
            }

        }while(exit);
       
    }

}
        
        
        
        
    
        
 