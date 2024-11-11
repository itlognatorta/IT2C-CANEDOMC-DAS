
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

            System.out.print("Enter action: ");
            int act = sc.nextInt();
            


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
        
        
        
        
    
        
 