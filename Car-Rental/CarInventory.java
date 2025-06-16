package carrental.carrental;
import java.util.Scanner;
public class CarInventory {
    Scanner sc = new Scanner(System.in);
    public int maxSize=10; //max 10 cars
    private int currentSize=7;   
    private Car[] list = new Car[maxSize]; 
    
    public CarInventory() {
        list[0] = new Car("Ferrari", "SF90 Stradale", "Red", 2023, 3990, 986, "Hybrid", 7.8f, 300000, true);
        list[1] = new Car("Lamborghini", "Huracan STO", "Green", 2023, 5204, 640, "Petrol", 5.9f, 500000, true);
        list[2] = new Car("Porsche", "911 Turbo S", "White", 2023, 3800, 640, "Petrol", 8.5f, 350000, true);
        list[3] = new Car("McLaren", "720S", "Orange", 2023, 3994, 710, "Petrol", 8.1f, 284000, true);
        list[4] = new Car("Chevrolet", "Corvette Z06", "Red", 2023, 5500, 670, "Petrol", 6.8f, 450000, true);
        list[5] = new Car("Nissan", "GT-R Nismo", "Black", 2023, 3799, 600, "Petrol", 6.8f, 320000, true);
        list[6] = new Car("Bugatti", "Chiron Super Sport", "Blue", 2023, 7993, 1578, "Petrol", 4.2f, 1200000, true);
    }
      public Car[] getCarList() {
        return list;    
    }
    public void setCurrentSize()
    {
        this.currentSize++;
    }
    public int getCurrentSize()
    {
        return this.currentSize;
    }
    //......................................................Search By Make
    public int searchByMake(String choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getMake().equals(choice)) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
        return 0;
    }
    //......................................................Search By Color
    public int searchByColor(String choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getColor().equals(choice)) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
        return 0;
    }
    //......................................................Search By Engine Capacity
    public int searchByEngineCapacity(int choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getEngineCapacity() <= choice) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
        return 0;
    }
    //......................................................Search By Horse Power
    public int searchByHorsePower(int choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getHorsePower() <= choice) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
        return 0;
    }
    //......................................................Search By Fuel Mileage
    public int searchByFuelMileage(int choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getFuelMileage() >= choice) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
       return 0;
    }
    //......................................................Search By Budget
    public long searchByRent(long choice) {
        boolean found = false;
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (list[i].getRent() <= choice) {
                System.out.println("\t=======================================================");
                list[i].output();
                found = true;
            }
        }
        if (found != true) {
            System.out.println("\n\t     ---------- No Cars Found! ----------");
        }
        return 0;
    }
}





