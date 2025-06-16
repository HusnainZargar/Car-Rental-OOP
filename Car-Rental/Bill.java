package carrental.carrental;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bill {

    Scanner sc = new Scanner(System.in);
    private boolean error = false;
    private CarInventory inventory;
    private int index;
    private int Days;
    private long TotalAmount;
    private String fullName;

    Bill(int index, CarInventory inventory) {
        this.index = index;
        this.inventory = inventory;
    }
    //......................................................Getters
    public boolean getError() {
        return error;
    }
    public int getIndex() {
        return this.index;
    }
    public int getDays() {
        return this.Days;
    }
    public long getTotalAmount() {
        return this.TotalAmount;
    }
    public String getFullName() {
        return this.fullName;
    }
    //......................................................Setters
    public void setError(boolean b) {
        this.error = b;
    }
    public void setTotalAmount(int days) {
        this.TotalAmount = inventory.getCarList()[this.index].getRent() * days;
    }
    public void setDays(int Days) {
        this.Days = Days;
    }
    public void setFullName(String name) {
        this.fullName = name;
    }
    //......................................................Bill Interface
    public int BillInterface() {
        String ch;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                     Bill Interface");
        System.out.println("\t=======================================================");
        if (getError()) {
            System.out.println("\n\t               *** Invalid Input ***");
            setError(false);
        }
        inventory.getCarList()[index].output();
        try {
            System.out.print("\n\t Enter How many Days do you want(1-7): ");
            this.Days = sc.nextInt();
            if (this.Days >= 1 && this.Days <= 7) {
                
                System.out.print("\n\t Enter Your Full Name: ");
                sc.nextLine();
                this.fullName = sc.next();
                this.setTotalAmount(this.Days);
                System.out.println("\n\t          *** Total Amount: Rs. " + this.getTotalAmount() + " ***");
                System.out.print("\n\t Confirm (Y/N): ");
                ch = sc.next();
                switch (ch) {
                    case "Y":
                    case "y":
                        inventory.getCarList()[index].setRequest(true); // Request for admin for approval
                        inventory.getCarList()[index].setisBooked(true); // it is booked, and will be displayed in BookedCars
                        inventory.getCarList()[index].setAvailable(false); // car is booked, it will not be available
                        this.BillGenerate();
                        return 0;
                    case "N":
                    case "n":
                        System.out.println("\n\t           *** Process Terminated ***");
                        System.out.print("\n\t Enter Any Key to Continoue: ");
                        ch = sc.next(); // clear buffer
                        return 0;
                    default:
                        System.out.println("\n\t\t ---Invalid Value!---");
                        System.out.print("\n\t Enter Any Key to Continoue: ");
                        ch = sc.next(); // clear buffer
                        this.BillInterface();
                        break;
                }
            }
            else{
               System.out.println("\n\t\t ---Invalid Value!---");
               System.out.print("\n\t Enter Any Key to Continoue: ");
               ch = sc.next(); // clear buffer
               this.BillInterface(); 
            }
        } catch (InputMismatchException e) {
            setError(true);     // for every invalid input, the error will be true 
            sc.nextLine(); // cleans the buffer when exception is thrown
            this.BillInterface();
        }
        return 0;
    }
    //......................................................Bill Generation
    public int BillGenerate() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                        Bill ");
        System.out.println("\t=======================================================");
        System.out.print("\n\t Rented Car: ");
        inventory.getCarList()[this.index].ShortDisplay();
        System.out.println("\t Full Name: " + this.getFullName());
        System.out.println("\t Number of Days: " + this.getDays());
        System.out.println("\t Total Amount: " + this.getTotalAmount());
        if (inventory.getCarList()[this.index].getRequest()) // if request is true, its pending for approval
        {
            System.out.println("\n\t              ***** Request Pending ***** ");
        }
        System.out.print("\n\t Enter Any Key to Continoue: ");
        String ch = sc.next(); // clear buffer
        return 0;
    }
    //.........................ClearScreen
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
