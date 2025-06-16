package carrental.carrental;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Customer extends User {

    Scanner sc = new Scanner(System.in);
    private boolean error = false;
    private String choice;
    private int ID; // unique ID to track booking, logins,etc
    private static final HashSet<Integer> usedIDs = new HashSet<>();
    private static final Random random = new Random();
    private admin Admin;
    private int index = 0; // make getter and setter
    private CarInventory inventory;
    private Bill bill; // public because admin is accessing

    Customer(String username, String password, admin Admin, CarInventory inventory) {
        this.username = username;
        this.password = password;
        this.Admin = Admin;
        this.inventory = inventory;
    }
    //......................................................Getters
    public Bill getBill() {
        return bill;
    }
    public int getID() {
        return ID;
    }
    public int getIndex() {
        return index;
    }
    public boolean getError() {
        return error;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    //......................................................Setters
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    public void setError(boolean b) {
        this.error = b;
    }
    public void setID() {
        int newID;
        do {
            newID = 1000 + random.nextInt(9000);
        } while (usedIDs.contains(newID));
        usedIDs.add(newID);
        this.ID = newID;
    }
    //......................................................Customer Interface
    public void CustomerInterface() {
        int choice = 0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t             Welcome to the Customer Dashboard");
        System.out.println("\t=======================================================");
        if (getError()) {
            System.out.println("\n\t               *** Invalid Input ***");
            setError(false);
        }
        if (Admin.getTimer()) {
            System.out.println("\n\t           *** Account Created Successfully ***");
            Admin.setTimer(false);
        }
        System.out.println("\n\t                  1- List all Cars");
        System.out.println("\t                  2- Search for a Car");
        System.out.println("\t                  3- Book a Car");
        System.out.println("\t                  4- View Requests for Booked Cars");
        System.out.println("\t                  5- Return a Car");
        System.out.println("\t                  6- Log out");
        System.out.print("\t   Enter: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            setError(true);     // for every invalid input, the error will be true 
            sc.nextLine(); // cleans the buffer when exception is thrown
            this.CustomerInterface();
        }
        switch (choice) {
            case 1:
                this.clearScreen();
                System.out.println("\t=======================================================");
                System.out.println("\t                  All Available Cars");
                System.out.println("\t=======================================================");
                for (int i = 0; i < inventory.getCurrentSize(); i++) {
                    if (inventory.getCarList()[i] != null) {
                        inventory.getCarList()[i].output();
                    }
                }
                System.out.print("\n\t  Enter Any key to go Back: ");
                this.choice = sc.next();
                this.CustomerInterface();
                break;
            case 2:
                this.searchCar();
                break;
            case 3:
                this.bookACar();
                break;
            case 4:
                this.bookedCars();
                break;
            case 5:
                this.returnCar();
                break;
            case 6:
                Admin.login();
                break;
            default:
                System.out.println("\n\t                *** Invalid Option! ***");
                break;
        }
        System.out.print("\n\t Enter any key to Continoue: ");
        this.choice = sc.next();
        this.CustomerInterface();
    }
    //......................................................Search Car
    public void searchCar() {
        long Lchoice;
        String Schoice;
        int Ichoice = 0;

        clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                   Search For a Car");
        System.out.println("\t=======================================================");
        if (getError()) {
            System.out.println("\n\t               *** Invalid Input ***");
            setError(false);
        }
        System.out.println("\n\t                   1-  By Your Budget");
        System.out.println("\t                   2-  By Car Make");
        System.out.println("\t                   3-  By Car Color");
        System.out.println("\t                   4-  By Engine Capacity");
        System.out.println("\t                   5-  By HorsePower");
        System.out.println("\t                   6-  By Fuel Mileage");
        System.out.println("\t                   7-  For Exit");
        System.out.print("\n\t Enter: ");
        try {
            Ichoice = sc.nextInt();
            sc.nextLine();
            switch (Ichoice) {
                case 1:
                    System.out.print("\n\t Enter Your Maximum Budget: ");
                    Lchoice = sc.nextInt();
                    sc.nextLine();
                    inventory.searchByRent(Lchoice);
                    break;
                case 2:
                    System.out.print("\n\t Enter the Car Make: ");
                    Schoice = sc.next();
                    sc.nextLine();
                    inventory.searchByMake(Schoice);
                    break;
                case 3:           
                    System.out.print("\n\t Enter the Car Color: ");
                    Schoice = sc.nextLine();
                    sc.nextLine();
                    inventory.searchByColor(Schoice);
                    break;
                case 4:
                    System.out.print("\n\t Enter the Maximum Engine Capacity: ");
                    Ichoice = sc.nextInt();
                    sc.nextLine();
                    inventory.searchByEngineCapacity(Ichoice);
                    break;
                case 5:
                    System.out.print("\n\t Enter the Maximum HorsePower: ");
                    Ichoice = sc.nextInt();
                    sc.nextLine();
                    inventory.searchByHorsePower(Ichoice);
                    break;
                case 6:
                    System.out.print("\n\t Enter the Minimum Fuel Mileage: ");
                    Ichoice = sc.nextInt();
                    sc.nextLine();
                    inventory.searchByFuelMileage(Ichoice);
                    break;
                case 7:
                    this.CustomerInterface();
                    break;
                default:
                    System.out.println("\n\t\t ---Invalid Value!---");
                    break;
            }
        } catch (InputMismatchException e) {
            setError(true);     // for every invalid input, the error will be true 
            sc.nextLine(); // cleans the buffer when exception is thrown
            this.searchCar();
        }
        System.out.print("\n\t Enter Any Key to go Back: ");
        this.choice = sc.nextLine(); // clear buffer
        this.CustomerInterface();
    }
    //......................................................GettersBook a Car
    public void bookACar() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                     Book a Car");
        System.out.println("\t=======================================================");
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            if (inventory.getCarList()[i] != null) {
                System.out.println("\n\t      --- Car Index: " + (i + 1) + " ---");
                inventory.getCarList()[i].output();
            }
        }
        if (getError()) {
            System.out.println("\n\t               *** Invalid Input ***");
            setError(false);
        }

        System.out.print("\n\t Enter Car Index to Book (00 to Quit):");
        try {
            this.index = sc.nextInt();

            if (this.index == 00) {
                this.CustomerInterface();
            }
            --this.index;
            if (this.index < 0 || this.index >= inventory.getCurrentSize()) {
                setError(true);
                this.bookACar();
            } else {
                if (!inventory.getCarList()[this.index].getAvailabe()) {
                    System.out.println("\n\t               *** Car is not Available! ***");
                    System.out.print("\n\t Enter Any Key to Continoue: ");
                    this.choice = sc.next();
                    this.bookACar();
                } else {
                    System.out.println("\n\t You Selected " + inventory.getCarList()[this.index].getMake() + " " + inventory.getCarList()[this.index].getModel() + "! ");
                    System.out.print("\t Do You want to Book (Y/N): ");
                    String ch;
                    ch = sc.next();
                    switch (ch) {
                        case "Y":
                        case "y":
                            if (this.bill != null && inventory.getCarList()[this.bill.getIndex()].getisBooked()) {
                                System.out.println("\n\t      *** You can Only Book one Car at a time ***");
                                System.out.print("\n\t Enter Any Key to Go Back: ");
                                this.choice = sc.next();
                                this.CustomerInterface();
                            }
                            this.bill = new Bill(this.index, inventory);
                            this.bill.BillInterface();
                            this.CustomerInterface();
                            break;
                        case "N":
                        case "n":
                            System.out.println("\n\t               *** Process Terminated! ***");
                            System.out.print("\n\t Enter Any Key to Go Back: ");
                            this.choice = sc.nextLine();
                            this.CustomerInterface();
                            break;
                        default:
                            System.out.println("\n\t\t ---Invalid Value!---");                         
                            break;
                    }
                }

            }
        } catch (InputMismatchException e) {
            setError(true);     // for every invalid input, the error will be true 
            sc.next(); // cleans the buffer when exception is thrown
            this.bookACar();
        }
        System.out.print("\n\t Enter Any Key to Continoue: ");
        this.choice = sc.nextLine(); // clear buffer
        this.CustomerInterface();
    }
    //......................................................Booked Cars
    public void bookedCars() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                       Bookings");
        System.out.println("\t=======================================================");
        try {
            if (bill != null && inventory.getCarList()[bill.getIndex()].getisBooked()) {
                System.out.print("\t Rented Car: ");
                inventory.getCarList()[bill.getIndex()].ShortDisplay();
                System.out.println("\t Customer ID: " + this.getID());
                System.out.println("\t Customer Name: " + this.bill.getFullName());
                System.out.println("\t Number of Days: " + this.bill.getDays());
                System.out.println("\t Total Amount: " + this.bill.getTotalAmount());
                System.out.print("\t Status: ");
                if (inventory.getCarList()[bill.getIndex()].getisBooked() && !inventory.getCarList()[bill.getIndex()].getReturn()) {
                    System.out.print("*** Request Pending *** ");
                } // when car is booked (approved by admin && car is not returned then it will show request accepted
                else {
                    System.out.println("*** Request Accepted *** ");
                    System.out.println("\t *** Car will be Delivered in " + inventory.getCarList()[bill.getIndex()].getDelivery() + " hours ***");
                    System.out.print("\n\t Enter Any Key to go Back: ");
                    this.choice = sc.next();
                    this.CustomerInterface();
                }
            } else {
                System.out.println("\t                *** No Booked cars *** ");
            }
        } // trying to see booking and returns before booking a car causes a nullPointerExecepion because 'bill.getIndex()'
        // is null, so indirectly the car is not booked, thats why i printed the message no booked cars
        // taking the advantage of the error :)
        catch (NullPointerException e) {
            System.out.println("\t                *** No Booked cars *** ");
        }
        System.out.print("\n\t Enter Any Key to go Back: ");
        this.choice = sc.next();
        this.CustomerInterface();
    }
    //......................................................Return Car
    public void returnCar() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                     Return a Car");
        System.out.println("\t=======================================================");
        try {
            if (inventory.getCarList()[this.bill.getIndex()].getReturn()) {
                System.out.print("\t Rented Car: ");
                inventory.getCarList()[this.bill.getIndex()].ShortDisplay();
                System.out.println("\t Customer ID: " + this.getID());
                System.out.println("\t Customer Name: " + this.bill.getFullName());
                System.out.println("\t Number of Days: " + this.bill.getDays());
                System.out.println("\t Total Amount: " + this.bill.getTotalAmount());
                System.out.print("\n\t Do you Want to Return (Y/N): ");
                String ch = "";
                ch = sc.next();
                switch (ch) {
                    case "Y":
                    case "y":
                        inventory.getCarList()[this.bill.getIndex()].setisBooked(false);
                        inventory.getCarList()[this.bill.getIndex()].setAvailable(true);
                        inventory.getCarList()[this.bill.getIndex()].setReturn(false);
                        System.out.println("\n\t           *** Returned Successfully ***");
                        break;
                    case "N":
                    case "n":
                        System.out.println("\n\t           *** Process Terminated ***");
                        break;
                    default:
                        System.out.println("\n\t           *** Invalid Selection! ***");
                        break;
                }
            } else {
                System.out.println("\t              *** No Cars to Return ***");
            }
        } catch (NullPointerException e) {
            System.out.println("\t              *** No Cars to Return ***");
        }
        System.out.print("\n\t Enter Any Key to go Back: ");
        this.choice = sc.next();
        this.CustomerInterface();
    }
    //.........................ClearScreen
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
