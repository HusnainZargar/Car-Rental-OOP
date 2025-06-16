package carrental.carrental;

import java.util.InputMismatchException;
import java.util.Scanner;

public class admin extends User {

    Scanner sc = new Scanner(System.in);
    private int CustomerArraySize = 0;
    private Customer[] customer = new Customer[6]; //max 5 customers only + 1 admin
    private boolean error = false; // for error handling
    private CarInventory inventory;
    private String choice;
    private boolean timer = true; // timer for sign up, it will display message "Account created"

    admin(String username, String password, CarInventory inventory) {
        this.username = username;
        this.password = password;
        this.inventory = inventory;
    }

    //......................................................Getters
    public Customer[] getCustomerList() {
        return customer;
    }

    public boolean getError() {
        return error;
    }

    public int GetCustomerArraySize() {
        return CustomerArraySize;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public boolean getTimer() {
        return timer;
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

    public void setTimer(boolean t) {
        this.timer = t;
    }

    public void setCustomerArraySize() {
        this.CustomerArraySize++;
    }

    public void setError(boolean b) {
        this.error = b;
    }

    //......................................................Creds List
    private void CredentialsList(String username, String password) {
        for (int i = 0; i < GetCustomerArraySize(); i++) {
            if (getCustomerList()[i].getUsername().equals(username) && getCustomerList()[i].getPassword().equals(password)) {
                getCustomerList()[i].CustomerInterface(); // creds are correct, call customer interface   
            }
        }
    }

    //......................................................login interface
    public int login() {
        int attempt = 3;
        String tempUser = "";
        String tempPass = "";
        do {
            this.clearScreen();
            System.out.println("\t=======================================================");
            System.out.println("\t=======================================================");
            System.out.println("\t               ONLINE CAR RENTAL SYSTEM");
            System.out.println("\t=======================================================");
            System.out.println("\t=======================================================");
            System.out.println("\n\t                    --- Login ---");
            System.out.print("\n\t       Don't Have account? Enter '&' for Sign Up      ");
            if (attempt < 3) {
                if (attempt == 0) {
                    System.out.println("\n\n\n\t               *** Account Locked! *** ");
                    return 0;
                } else {
                    System.out.println("\n\n\t     *** Invalid Credentials! " + attempt + " Attempt Remains! ***");
                }
            }
            System.out.print("\n\n\t       Enter Username: ");
            tempUser = sc.next();
            if (tempUser.equals("&")) {
                this.signUp();
            } else {
                System.out.print("\t       Enter Password: ");
                tempPass = sc.next();
                if (tempUser.equals(this.username) && tempPass.equals(this.password)) {
                    this.adminInterface();
                    return 0;
                } else {
                    this.CredentialsList(tempUser, tempPass);
                }
                --attempt;
            }
        } while (attempt >= 0);
        return 0;
    }

    //......................................................SignUp interface
    public int signUp() {
        String tempUser = "";
        String tempPass = "";
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t=======================================================");
        System.out.println("\t               ONLINE CAR RENTAL SYSTEM");
        System.out.println("\t=======================================================");
        System.out.println("\t=======================================================");
        System.out.println("\n\t                    --- SignUp ---");
        if (GetCustomerArraySize() == 4) {
            System.out.println("\n\n\t            *** Max User Limit Reached *** ");
            System.out.print("\n\t  Enter Any key to go Back: ");
            choice = sc.next();
            this.login();
        }
        System.out.print("\n\n\t       Enter Username: ");
        tempUser = sc.next();
        System.out.print("\t       Enter Password: ");
        tempPass = sc.next();
        if (tempUser.equals(this.username)) { // so customer cannot take the username of admin
            System.out.println("\n\t            *** Username already exists *** ");
            System.out.print("\n\t  Enter Any key to Continue: ");
            choice = sc.next();
            this.signUp();
        }
        for (int i = 0; i < GetCustomerArraySize(); i++) // to check username is unique
        {
            if (getCustomerList()[i].username.equals(tempUser)) {
                System.out.println("\n\t            *** UserName already exists *** ");
                System.out.print("\n\t  Enter Any key to Continoue: ");
                choice = sc.next();
                this.signUp();
            }
        }
        Customer newCustomer = new Customer(tempUser, tempPass, this, inventory);
        customer[GetCustomerArraySize()] = newCustomer;
        customer[GetCustomerArraySize()].setID(); // generates a unique id for it
        setCustomerArraySize();
        setTimer(true);
        newCustomer.CustomerInterface();
        return 0;
    }
    
    //......................................................Admin interface
    public void adminInterface() {
        int choice = 0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t               Welcome to the Admin Dashboard!");
        System.out.println("\t=======================================================");
        if (getError()) // display only when error is true
        {
            System.out.println("\n\t                  ***** Invalid Input *****");
            setError(false); // after displaying the error, convert into false
        }
        System.out.println("\n\t                     1- Car Management");
        System.out.println("\t                     2- Manage Car Booking Requests");
        System.out.println("\t                     3- View all User Accounts");
        System.out.println("\t                     4- Change Username & Password");
        System.out.println("\t                     5- Log out");
        System.out.print("\t   Enter: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            setError(true);     // for every invalid input, the error will be true 
            sc.nextLine(); // cleans the buffer when exception is thrown
            this.adminInterface();
        }
        switch (choice) {
            case 1:
                this.manageCars();
                break;
            case 2:
                this.BookingRequests();
                break;
            case 3:
                this.displayAllUsers();
                break;
            case 4:
                this.changeCredentials();
                break;
            case 5:
                this.login();
                break;
            default:
                System.out.println("\n\t                *** Invalid Option! ***");
                break;
        }
        System.out.print("\n\t Enter any key: ");
        this.choice = sc.next();
        this.adminInterface();
    }
    
    //......................................................Car management
    public void manageCars() {
        int choice = 0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                    Car Management");
        System.out.println("\t=======================================================");
        if (getError()) {
            System.out.println("\n\t                  ***** Invalid Input *****");
            setError(false);
        }
        System.out.println("\n\t                     1- Display All Cars");
        System.out.println("\t                     2- Add New Car");
        System.out.println("\t                     3- Edit Car");
        System.out.println("\t                     4- Remove Car");
        System.out.println("\t                     5- Back to Admin Dashboard");
        System.out.print("\t   Enter: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            setError(true);
            sc.next();
            this.manageCars();
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
                this.manageCars();
                break;
            case 2:
                this.addCar();
            case 3:
                this.editCar();
                break;
            case 4:
                this.removeCar();
                break;
            case 5:
                this.adminInterface();
                break;
            default:
                System.out.println("\n\t                *** Invalid Option! ***");
                break;
        }
        System.out.print("\n\t Enter any key to go back: ");
        this.choice = sc.next();
        this.manageCars();
    }

    //......................................................Add Car
    public void addCar() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                      Add Car");
        System.out.println("\t=======================================================");
        if (inventory.getCurrentSize() < inventory.maxSize) {
            System.out.println("\n\t Enter Car Details: \n\n");
            Car newCar = new Car("", "", "", 0, 0.0f, 0, "", 0.0f, 0, true);
            if (newCar.input() == true) // it means the input() methods returned an error
            {
                setError(true); // it will display the error in the car management interface
                this.manageCars(); // as error is occured, navigate the user to the car management
            } else {
                inventory.getCarList()[inventory.getCurrentSize()] = newCar;
                inventory.setCurrentSize();
                System.out.println("\n\t               *** Car Added Successfully ***");
                System.out.print("\n\t  Enter Any key to go Back: ");
                this.choice = sc.next();
                this.manageCars();
            }
        } else {
            System.out.println("\n\t          *** Maximum Capacity Reached! ***");
            System.out.print("\n\t  Enter Any key to go Back: ");
            this.choice = sc.next();
            this.manageCars();
        }
    }

    //......................................................Edit car
    public void editCar() {
        int index = 0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                  Edit Car Detail");
        System.out.println("\t=======================================================");
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            if (inventory.getCarList()[i] != null) {
                System.out.println("\n\t           --- Car Index: " + (i + 1) + " --- \n");
                inventory.getCarList()[i].output();
            }
        }
        System.out.print("\n\t Enter Car Index to Modify: ");
        try {
            index = sc.nextInt();
        } catch (InputMismatchException e) {
            setError(true);
            this.manageCars();
        }
        sc.nextLine();
        --index;
        if (index >= 0 && index < inventory.getCurrentSize() && inventory.getCarList()[index] != null) {
            if (inventory.getCarList()[index].input() == true) {
                setError(true);
                this.manageCars();
            }
            System.out.println("\n\t               *** Car Modified Successfully ***");
        } else {
            System.out.println("\n\t               *** Invalid Car Index ***");
        }
        System.out.print("\n\t  Enter Any key to go Back: ");
        this.choice = sc.next();
        this.manageCars();
    }

    //......................................................Remove Car interface
    public void removeCar() {
        int choice = 0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                     Remove a Car");
        System.out.println("\t=======================================================");
        System.out.println("\t                 1- Temporary Remove");
        System.out.println("\t                 2- Permanent Remove");
        System.out.println("\t                 3- Back to Car Management");
        System.out.print("\n\t  Enter: ");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            setError(true);
            sc.next();
            this.manageCars();
        }
        switch (choice) {
            case 1:
                this.tempRemove();
                break;
            case 2:
                this.permanentRemove();
                break;
            case 3:
                this.manageCars();
                break;
            default:
                System.out.println("\n\t                *** Invalid Option! ***");
                break;
        }
        System.out.print("\n\t Enter any key to go back: ");
        this.choice = sc.next();
        this.manageCars();
    }

    //......................................................Temp Remove
    public void tempRemove() {
        int index=0;
        String reason;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                    Temporary Remove");
        System.out.println("\t=======================================================");
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            if (inventory.getCarList()[i] != null) {
                System.out.print("\n\t      --- Car Index " + (i + 1) + " : ");
                inventory.getCarList()[i].ShortDisplay();
            }
        }
        System.out.print("\n\t Enter Car Index: ");
        try{
        index = sc.nextInt();
        }
        catch (InputMismatchException e) {
            setError(true);
            sc.next();
            this.manageCars();
        }
        --index;
        if (index >= 0 && index < inventory.getCurrentSize() && inventory.getCarList()[index] != null) { 
            System.out.print("\n\t You Selected: ");
            inventory.getCarList()[index].ShortDisplay();
            System.out.print("\n\t Enter Reason For Temporary Removal: ");
            reason = sc.next();
            inventory.getCarList()[index].setReason(reason);
            inventory.getCarList()[index].setAvailable(false);
            System.out.println("\n\t      ---------- Successful! Its now Unavailable ----------");
            System.out.print("\n\n\t Enter any key to go back: ");
            choice = sc.next();
            this.manageCars();
        } else {
            System.out.println("\n\t               *** Invalid Car Index ***");
        }
        System.out.print("\n\t  Enter Any key to continoue: ");
        this.choice = sc.next();
        this.tempRemove();
    }

    //......................................................Permanent Remove
    public void permanentRemove() {
        int index=0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                    Permanent Delete");
        System.out.println("\t=======================================================");
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            if (inventory.getCarList()[i] != null) {
                System.out.println("\n\t      --- Car Index: " + (i + 1) + " ---");
                inventory.getCarList()[i].ShortDisplay();
            }
        }
        System.out.print("\n\t  Enter Car Index: ");
        try{
        index = sc.nextInt();
        }
        catch (InputMismatchException e) {
            setError(true);
            sc.next();
            this.manageCars();
        }
        sc.nextLine();
        --index;
        if (index >= 0 && index < inventory.getCurrentSize() && inventory.getCarList()[index] != null) {
            inventory.getCarList()[index] = null;
            System.out.println("\n\t               *** Car Deleted Successfully ***");
        } else {
            System.out.println("\n\t               *** Invalid Car Index ***");
        }
        System.out.print("\n\t  Enter Any key to go Back: ");
        this.choice = sc.next();
        this.manageCars();
    }

    //......................................................Booking management
    public void BookingRequests() {
        int num = 1;
        boolean hasRequests = false;
        for (int i = 0; i < inventory.getCurrentSize(); i++) {
            this.clearScreen();
            System.out.println("\t=======================================================");
            System.out.println("\t                    Booking Requests");
            System.out.println("\t=======================================================");
            if (inventory.getCarList()[i] != null && inventory.getCarList()[i].getRequest()) {
                hasRequests = true;
                System.out.println("\t                *** Request No. " + num + " ***");
                num++;
                System.out.print("\t Rented Car: ");
                inventory.getCarList()[i].ShortDisplay();
                for (int j = 0; j < GetCustomerArraySize(); j++) {
                    if (getCustomerList()[j].getBill() != null && getCustomerList()[j].getBill().getIndex() == i) // maching the index of user selected car with indexes of cars
                    {
                        System.out.println("\t Customer ID: " + getCustomerList()[j].getID());
                        System.out.println("\t Customer Name: " + getCustomerList()[j].getBill().getFullName());
                        System.out.println("\t Number of Days: " + getCustomerList()[j].getBill().getDays());
                        System.out.println("\t Total Amount: " + getCustomerList()[j].getBill().getTotalAmount());
                        System.out.print("\n\n\t Accept or Reject (Y/N): ");
                        String ch = sc.next();
                        switch (ch) {
                            case "Y":
                            case "y":
                                System.out.print("\n\t Enter Delivery time (In Hours): ");
                                int ch2 = 0;
                                try{
                                ch2 = sc.nextInt();
                                }
                                catch (InputMismatchException e) {
                                 setError(true);
                                 sc.next();  
                                 this.manageCars();
                                }
                                inventory.getCarList()[i].setDelivery(ch2);
                                System.out.println("\n\t               *** Request Accepted ***");
                                inventory.getCarList()[i].setRequest(false); // request is accepted admin will not see it
                                inventory.getCarList()[i].setReturn(true); // car is booked, so it can be returned now
                                break;
                            case "N":
                            case "n":
                                System.out.println("\n\t               *** Request Rejected ***");
                                break;
                            default:
                                 System.out.println("\n\t                *** Invalid Option! ***");
                                 break;
                        }
                        break; // once the request is found, exit the loop
                    }
                }
            }
        }
        if (!hasRequests) {
            System.out.println("\n\t               *** No Booking Requests ***");
        }
        System.out.print("\n\t Enter any key to go back: ");
        this.choice = sc.next();
        this.adminInterface();
    }
    
    //......................................................Change Credentials
    public void changeCredentials() {
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t               Change Username & Password");
        System.out.println("\t=======================================================");
        if (getError()) {
            System.out.println("\n\t            *** Username Already Exists ***");
            setError(false);
        }
        System.out.print("\n\t Enter Current Username: ");
        String currentUser = sc.next();
        System.out.print("\t Enter Current Password: ");
        String currentPass = sc.next();
        if (!currentUser.equals(this.username) || !currentPass.equals(this.password)) {
            System.out.println("\n\t           *** Invalid Current Credentials ***");
            System.out.print("\n\t Enter Any Key to Continue: ");
            this.choice = sc.next();
            this.changeCredentials();
        } else {
            System.out.print("\n\t Enter New Username: ");
            String newUser = sc.next();
            System.out.print("\t Enter New Password: ");
            String newPass = sc.next();
            // Check if new username is unique among customers
            for (int i = 0; i < GetCustomerArraySize(); i++) {
                if (getCustomerList()[i].getUsername().equals(newUser)) {
                    setError(true);
                    this.changeCredentials();
                }
            }
            // Update credentials
            this.setUsername(newUser);
            this.setPassword(newPass);
            System.out.println("\n\t           *** Credentials Updated Successfully ***");
            System.out.print("\n\t Enter Any Key to go back: ");
            this.choice = sc.next();
            this.adminInterface();
        }
    }

    //......................................................Display All User accounts
    public void displayAllUsers() {
        long totalIncome=0;
        this.clearScreen();
        System.out.println("\t=======================================================");
        System.out.println("\t                   All User Details");
        System.out.println("\t=======================================================");
        if (GetCustomerArraySize() != 0) {
            for (int i = 0; i < GetCustomerArraySize(); i++) {
                System.out.println("\n\t User ID: " + getCustomerList()[i].getID());
                System.out.println("\t Username: " + getCustomerList()[i].getUsername());
                System.out.println("\t Password: " + getCustomerList()[i].getPassword());
                if (getCustomerList()[i].getBill() != null && inventory.getCarList()[getCustomerList()[i].getBill().getIndex()].getisBooked()) {
                    Car bookedCar = inventory.getCarList()[getCustomerList()[i].getBill().getIndex()];
                    System.out.println("\t Car Booked: " + bookedCar.getMake() + " " + bookedCar.getModel());
                    System.out.println("\t Price: Rs. " + getCustomerList()[i].getBill().getTotalAmount());
                    System.out.println("\t Days Booked: " + getCustomerList()[i].getBill().getDays());
                    totalIncome += customer[i].getBill().getTotalAmount();
                } else {
                    System.out.println("\t Car Booked: -");
                    System.out.println("\t Price: -");
                    System.out.println("\t No.of Days: -");
                }
                System.out.println("\t-------------------------------------------------------");
            }
            System.out.println("\n\t Total Income from Bookings: Rs. " + totalIncome);
        } else {
            System.out.println("\n\t                  *** No Users ***");    
        }
        System.out.print("\n\t Enter Any key to go Back: ");
        choice = sc.next();
        this.adminInterface();
    }
    //......................................................ClearScreen
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
