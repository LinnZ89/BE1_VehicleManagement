package models;

import controllers.VehicleList;

public interface VehicleManagement {
    void add();

    void remove();
    
    void update();

    void searchmenu();

    void search_by_code();

    void search_by_name();

    boolean check();

    void savetoFile();

    void displaymenu();

    void displayall();

    void displaybytype();

    void displayinfile();

    VehicleList readFile();
}