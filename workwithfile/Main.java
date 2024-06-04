package workwithfile;

import java.util.Scanner;

import controllers.VehicleList;
import models.VehicleManagement;

public class Main {
    public static void main(String[] args) {
        VehicleManagement vehicle_list = new VehicleList();

        while (true) {
            System.out.println("______Menu______");
            System.out.println("1. ADD NEW VEHICLE.");
            System.out.println("2. CHECKING EXITS VEHICLE.");
            System.out.println("3. UPDATING VEHICLE.");
            System.out.println("4. DELETING VEHICLE.");
            System.out.println("5. SEARCHING VEHICLE.");
            System.out.println("6. DISPLAY VEHICLE LIST.");
            System.out.println("7. SAVE VEHICLE TO FILE.");
            System.out.println("8. PRINT LIST VEHICLES IN FILE.");
            System.out.println("9. QUIT.");
            System.out.println("Please choose!!");

            Scanner sc = new Scanner(System.in);
            Integer choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    vehicle_list.add();
                case 2:
                    boolean res = vehicle_list.check();
                    if (res) {
                        System.out.println("EXIST VEHICLE!");
                    } else {
                        System.out.println("NO VEHICLE FOUND!");
                    }
                case 3:
                    vehicle_list.update();
                case 4:
                    vehicle_list.remove();
                case 5:
                    vehicle_list.searchmenu();
                case 6:
                    vehicle_list.displaymenu();
                case 7:
                    vehicle_list.savetoFile();
                case 8:
                    vehicle_list.displayinfile();
                case 9:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again!");
            }
            
        }
    }
}