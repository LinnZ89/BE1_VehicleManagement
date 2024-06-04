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
        int productYear = sc.nextInt();
        sc.nextLine(); // Consume the remaining newline

        Vehicle vehicle = new Vehicle(code, name, color, price, brand, type, productYear);
        this.add(vehicle);

        System.out.println("Vehicle was added successfully!");
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter vehicle's code to remove: ");
        String searchingCode = sc.nextLine();

        Vehicle toRemove = null;
        for (Vehicle vehicle : this) {
            if (vehicle.getId().equals(searchingCode)) {
                toRemove = vehicle;
                break;
            }
        }

        if (toRemove != null) {
            this.remove(toRemove);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter vehicle's code to update: ");
        String searchingCode = sc.nextLine();

        Vehicle toUpdate = null;
        for (Vehicle vehicle : this) {
            if (vehicle.getId().equals(searchingCode)) {
                toUpdate = vehicle;
                break;
            }
        }

        if (toUpdate != null) {
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
            int productYear = sc.nextInt();
            sc.nextLine(); // Consume the remaining newline

            toUpdate.setId(code);
            toUpdate.setName(name);
            toUpdate.setColor(color);
            toUpdate.setPrice(price);
            toUpdate.setBrand(brand);
            toUpdate.setType(type);
            toUpdate.setProductYear(productYear);

            System.out.println("Vehicle updated successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    @Override
    public void savetoFile() {
        try (FileOutputStream fileOutput = new FileOutputStream("data\\Vehicle.txt");
             ObjectOutputStream write = new ObjectOutputStream(fileOutput)) {
            write.writeObject(this);
            System.out.println("Saved Successfully!");
        } catch (IOException exception) {
            System.out.println("Save Failed!");
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
            exception.printStackTrace();
        }
        return res;
    }

    @Override
    public void search_by_code() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle's code: ");
        String searchingCode = sc.nextLine();
        for (Vehicle vehicle : this) {
            if (vehicle.getId().equals(searchingCode)) {
                System.out.println(vehicle.toString());
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    @Override
    public boolean check() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle's code: ");
        String searchingCode = sc.nextLine();
        for (Vehicle vehicle : this) {
            if (vehicle.getId().equals(searchingCode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void search_by_name() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vehicle's name: ");
        String searchingName = sc.nextLine();
        int count = 0;
        for (Vehicle vehicle : this) {
            if (vehicle.getName().equals(searchingName)) {
                System.out.println("___VEHICLE " + count + "___");
                System.out.println(vehicle.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No vehicle found with the given name!");
        }
    }

    @Override
    public void searchmenu() {
        while (true) {
            System.out.println("______Searching Menu______");
            System.out.println("1. SEARCH BY NAME.");
            System.out.println("2. SEARCH BY ID.");
            System.out.println("3. GO BACK TO MAIN MENU.");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    search_by_name();
                    break;
                case 2:
                    search_by_code();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again!");
            }
        }
    }

    @Override
    public void displaymenu() {
        while (true) {
            System.out.println("______Display Menu______");
            System.out.println("1. DISPLAY ALL.");
            System.out.println("2. DISPLAY BY TYPE.");
            System.out.println("3. GO BACK TO MAIN MENU.");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayall();
                    break;
                case 2:
                    displaybytype();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again!");
            }
        }
    }

    @Override
    public void displayall() {
        if (this.isEmpty()) {
            System.out.println("THERE IS NO VEHICLE IN SHOWROOM!");
            return;
        }

        Vehicle[] temp = this.toArray(new Vehicle[0]);

        for (int i = 0; i < temp.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[j].getPrice() > temp[maxIndex].getPrice()) {
                    maxIndex = j;
                }
            }
            Vehicle tempVar = temp[i];
            temp[i] = temp[maxIndex];
            temp[maxIndex] = tempVar;
        }

        int count = 0;
        for (Vehicle vehicle : temp) {
            System.out.println("______VEHICLE #______" + count);
            System.out.println(vehicle.toString());
            count++;
        }
    }

    @Override
    public void displaybytype() {
        if (this.isEmpty()) {
            System.out.println("THERE IS NO VEHICLE IN SHOWROOM!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER VEHICLE'S TYPE: ");
        String displayType = sc.nextLine();

        System.out.println("______KIND OF DISPLAY______");
        System.out.println("1. NORMAL DISPLAY.");
        System.out.println("2. DESCENDING DISPLAY.");
        System.out.println("Please choose!!");
        int choice = sc.nextInt();
        sc.nextLine();

        Vehicle[] temp = this.toArray(new Vehicle[0]);

        if (choice == 2) {
            for (int i = 0; i < temp.length - 1; i++) {
                int maxIndex = i;
                for (int j = i + 1; j < temp.length; j++) {
                    if (temp[j].getPrice() > temp[maxIndex].getPrice()) {
                        maxIndex = j;
                    }
                }
                Vehicle tempVar = temp[i];
                temp[i] = temp[maxIndex];
                temp[maxIndex] = tempVar;
            }
        }

        int count = 0;
        for (Vehicle vehicle : temp) {
            if (vehicle.getType().equals(displayType)) {
                System.out.println("______VEHICLE #______" + count + ": " + vehicle.toString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No vehicles found of the specified type!");
        }
    }

    @Override
    public void displayinfile() {
        VehicleList vehicleList = this.readFile();
        vehicleList.displaymenu();
    }
}
