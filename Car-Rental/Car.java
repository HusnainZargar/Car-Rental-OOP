package carrental.carrental;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Car {
    Scanner sc = new Scanner(System.in);
    private String make;
    private String model;
    private String color;
    private int yearOfManufacture;
    private float engineCapacity;
    private int horsePower;
    private String fuelType;
    private float fuelMileage;
    private long rent;
    private boolean isBooked=false;
    private boolean available=true;
    private boolean Return=false;
    private boolean Request=false; 
    private String reason = "Booked!"; 
    private int delivery;
    
    public Car(String make, String model, String color, int yearOfManufacture, float engineCapacity,
               int horsePower, String fuelType, float fuelMileage, int rent, boolean available) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.engineCapacity = engineCapacity;
        this.horsePower = horsePower;
        this.fuelType = fuelType;
        this.fuelMileage = fuelMileage;
        this.rent = rent;
        this.available = available;   
    }
    //......................................................Getters
     public boolean getisBooked()
    {
        return isBooked;
    }
    public boolean getReturn()
    {
        return Return;
    }
    public int getDelivery()
    {
        return delivery;
    }
    public String getMake()
    {
        return make;
    }
    public String getModel()
    {
        return model;
    }
    public String getColor()
    {
        return color;
    }
    public int getYearOfMan()
    {
        return yearOfManufacture;
    }
    public float getEngineCapacity()
    {
        return engineCapacity;
    }
    public int getHorsePower()
    {
        return horsePower;
    }
    public String getFuelType()
    {
        return fuelType;
    }
    public float getFuelMileage()
    {
        return fuelMileage;
    }
    public long getRent()
    {
        return rent;
    }
    public boolean getAvailabe()
    {
        return this.available;
    }
    public boolean getRequest()
    {
        return this.Request;
    }
    //......................................................Setters
    public void setisBooked(boolean b)
    {
        this.isBooked=b;
    }
    public void setRequest(boolean Request)
    {
       this.Request=Request; 
    }
        public void setReason(String reason)
    {
        this.reason=reason;
    }
    public void setAvailable(boolean available)
    {
       this.available=available; 
    }
    public void setDelivery(int d)
    {
        this.delivery=d;
    }
    public void setReturn(boolean b)
    {
        this.Return=b;
    }
    public void ShortDisplay()
    {
        System.out.println(" "+this.make+" "+this.model);
    }
    //......................................................Input
    public boolean input() {
       try{
        System.out.print("\t Enter Make: ");
        make = sc.next();
        System.out.print("\t Enter Model: ");
        model = sc.next();
        System.out.print("\t Enter Color: ");
        color = sc.next();
        System.out.print("\t Enter Year of Manufacture: ");
        yearOfManufacture = sc.nextInt();
        System.out.print("\t Enter Engine Capacity (L): ");
        engineCapacity = sc.nextFloat();
        System.out.print("\t Enter Horse Power: ");
        horsePower = sc.nextInt();
        System.out.print("\t Enter Fuel Type: ");
        fuelType = sc.next();
        System.out.print("\t Enter Fuel Mileage (km/l): ");
        fuelMileage = sc.nextFloat();
        System.out.print("\t Enter Rent Rs: ");
        rent = sc.nextLong();
       }
       catch(InputMismatchException e)
       {
           return true;
       }
       return false;
    }
    //......................................................Output
    public void output() {
        System.out.println("\t Make: " + make);
        System.out.println("\t Model: " + model);
        System.out.println("\t Color: " + color);
        System.out.println("\t Year of Manufacture: " + yearOfManufacture);
        System.out.println("\t Engine Capacity: " + engineCapacity + " L");
        System.out.println("\t Horse Power: " + horsePower);
        System.out.println("\t Fuel Type: " + fuelType);
        System.out.println("\t Fuel Mileage: " + fuelMileage + " km/L");
        System.out.print("\t Status: ");
        if (available == true) {
        System.out.println(" Available ");
        System.out.println("\t  ------ Per Day Rent: Rs. " + rent);
        System.out.println("\t=======================================================");
        } else {
        System.out.println(" ********** Not Available ********** ");
        System.out.println("\t Reason: " + reason);
        System.out.println("\t=======================================================");
        }
    }
}

