package controllers;

import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import models.Vehicle;
import models.VehicleManagement;

public class VehicleList extends ArrayList<Vehicle> implements VehicleManagement {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter vehicle's code: ");
        String code = sc.nextLine();

        System.out.println("Enter vehicle's name: ");
        String name = sc.nextLine();

        System.out.println("Enter vehicle's color: ");
        String color = sc.nextLine();

        double price = 0;
        while (true) {
            System.out.println("Enter vehicle's price: ");
            if (sc.hasNextDouble()) {
                price = sc.nextDouble();
                if (price > 0) {
                    sc.nextLine(); 
                    break;
                } else {
                    System.out.println("Price must be greater than 0. Try again.");
                }
            } else {
                System.out.println("Invalid input. Enter a valid price.");
                sc.next(); 
            }
        }

        System.out.println("Enter vehicle's brand: ");
        String brand = sc.nextLine();

        System.out.println("Enter vehicle's type: ");
        String type = sc.nextLine();

        System.out.println("Enter vehicle's productYear: ");
        Integer productYear = sc.nextInt();

        Vehicle vehicle = new Vehicle(code, name, color, price, brand, type, productYear);

        this.add(vehicle);

        System.out.println("Vehicle was added succesfully!");
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter vehicle's code to update: ");
        String searchingcode = sc.nextLine();

        VehicleList vehicleList = this;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == searchingcode) {

            }
        }

    }
    
    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter vehicle's code to update: ");
        String searchingcode = sc.nextLine();

        VehicleList vehicleList = this;
        for (Vehicle Ivehicle : vehicleList) {
            if (Ivehicle.getId() == searchingcode) {

                System.out.println("Enter new vehicle's code: ");
                String code = sc.nextLine();

                System.out.println("Enter new vehicle's name: ");
                String name = sc.nextLine();

                System.out.println("Enter new vehicle's color: ");
                String color = sc.nextLine();

                double price = 0;
                while (true) {
                System.out.println("Enter new vehicle's price: ");
                if (sc.hasNextDouble()) {
                    price = sc.nextDouble();
                    if (price > 0) {
                        sc.nextLine(); 
                        break;
                    } else {
                        System.out.println("Price must be greater than 0. Try again.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a valid price.");
                    sc.next(); 
                    }
                }

            System.out.println("Enter new vehicle's brand: ");
            String brand = sc.nextLine();

            System.out.println("Enter new vehicle's type: ");
            String type = sc.nextLine();

            System.out.println("Enter new vehicle's productYear: ");
            Integer productYear = sc.nextInt();

            Vehicle vehicle = new Vehicle(code, name, color, price, brand, type, productYear);
                }
            }
        }

    @Override
    public void savetoFile() {

        try (FileOutputStream fileOutput = new FileOutputStream("data\\Vehicle.txt");
            ObjectOutputStream write = new ObjectOutputStream(fileOutput)) {
                write.writeObject(this);
                System.out.println("Saved Successfully!");
            } catch (IOException exception) {
                System.out.println("Saved Failed!");
            }

    }

    @Override
    public VehicleList readFile() {

        VehicleList res = new VehicleList();
        try (FileInputStream fileInput = new FileInputStream("data\\Vehicle.txt");
            ObjectInputStream read = new ObjectInputStream(fileInput)) {

                res = (VehicleList) read.readObject();
                System.out.println("Read Successfully!");

            } catch (EOFException eofException) {
                System.out.println("Read Failed!");

            } catch (IOException | ClassNotFoundException exception) {

            }
            return res;
    }

    @Override
    public void search_by_code() {
        Scanner sc = new Scanner(System.in);
        System.err.println("Enter vehicle's code: ");
        String searchingcode = sc.nextLine();
        VehicleList vehicleList = this;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == searchingcode) {
                vehicle.toString();
            }
        }
    }

    @Override
    public boolean check() {
        Scanner sc = new Scanner(System.in);
        System.err.println("Enter vehicle's code: ");
        String searchingcode = sc.nextLine();
        VehicleList vehicleList = this;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getId() == searchingcode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void search_by_name() {
        VehicleList vehicleList = this;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle's name: ");
        String searchingname = sc.nextLine();
        Integer count = 0;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getName() == searchingname) {
                System.out.println("___VEHICLE " + count + "___");
                vehicle.toString();
            }
        }
    }

    @Override
    public void searchmenu() {
        VehicleList vehicleList = this;
        while (true) {
            System.out.println("______Searching Menu______");
            System.out.println("1. SEARCH BY NAME.");
            System.out.println("2. SEARCH BY ID.");
            System.out.println("3. GO BACK TO MAIN MENU.");

            Scanner sc = new Scanner(System.in);
            Integer choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    vehicleList.search_by_name();
                    break;
                case 2:
                    vehicleList.search_by_code();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again!");
            }
        }
    }

    @Override
    public void displaymenu() {
        VehicleList vehicleList = this;
        while (true) {
            System.out.println("______Display Menu______");
            System.out.println("1. DISPLAY ALL.");
            System.out.println("2. DISPLAY BY TYPE.");
            System.out.println("3. GO BACK TO MAIN MENU.");

            Scanner sc = new Scanner(System.in);
            Integer choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    vehicleList.displayall();
                    break;
                case 2:
                    vehicleList.displaybytype();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again!");
            }
        }
    }

    @Override
    public void displayall() {
        VehicleList vehicleList = this;
        int count = 0;
        if (vehicleList.isEmpty() == true) {
            System.out.println("THERE IS NO VEHICLE IN SHOWROOM!");
            return;
        } else {
            int n = vehicleList.size();
            Vehicle[] temp = new Vehicle[n];
      
            for (int i = 0; i < n; i++) {
                temp[i] = vehicleList.get(i);
            }
      
            for (int i = 0; i < n - 1; i++) {
                int maxIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (temp[j].getPrice() > temp[maxIndex].getPrice()) {
                        maxIndex = j;
                    }
                }
            
                    Vehicle tempVar = temp[i];
                    temp[i] = temp[maxIndex];
                    temp[maxIndex] = tempVar;
                }
      
            for (int i = 0; i < n; i++) {
                vehicleList.set(i, temp[i]);
            }

            for (Vehicle vehicle : vehicleList) {
                System.out.println("______VEHICLE #______" + count);
                vehicle.toString();
                count += 1;
            }
        }
    }

    @Override
    public void displaybytype() {
        VehicleList vehicleList = this;
      
        if (vehicleList.isEmpty()) {
          System.out.println("THERE IS NO VEHICLE IN SHOWROOM!");
          return;
        } else {
            Scanner sc = new Scanner(System.in);
            Integer count = 0;

            System.out.println("ENTER VEHICLE'S TYPE: ");
            String displaytype = sc.nextLine();
      
            System.out.println("______KIND OF DISPLAY______");
            System.out.println("1. NORMAL DISPLAY.");
            System.out.println("2. DESCENDING DISPLAY.");
            System.out.println("Please choose!!");
            Integer choice = sc.nextInt();
      
          switch (choice) {
            case 1:
              for (Vehicle vehicle : vehicleList) {
                if (vehicle.getType().equals(displaytype)) {
                  System.out.println("______VEHICLE #______" + count + ": " + vehicle.toString());
                  count++;
                }
              }
              break;
      
            case 2:
                int n = vehicleList.size();
                Vehicle[] temp = new Vehicle[n];
      
                for (int i = 0; i < n; i++) {
                    temp[i] = vehicleList.get(i);
                }
      
                for (int i = 0; i < n - 1; i++) {
                    int maxIndex = i;
                    for (int j = i + 1; j < n; j++) {
                    if (temp[j].getPrice() > temp[maxIndex].getPrice()) {
                        maxIndex = j;
                    }
                    }
            
                    Vehicle tempVar = temp[i];
                    temp[i] = temp[maxIndex];
                    temp[maxIndex] = tempVar;
                }
      
                for (int i = 0; i < n; i++) {
                    vehicleList.set(i, temp[i]);
                }
      
                for (Vehicle vehicle : vehicleList) {
                    if (vehicle.getType().equals(displaytype)) {
                    System.out.println("______VEHICLE #______" + count + ": " + vehicle.toString());
                    count++;
                    }
                }
                break;
      
            default:
                break;
            }
            }
        }

    @Override
    public void displayinfile() {
        VehicleList vehicleList = this.readFile();
        vehicleList.displaymenu();
    }
}