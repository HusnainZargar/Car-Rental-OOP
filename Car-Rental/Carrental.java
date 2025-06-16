package carrental.carrental;
public class Carrental { 
    public static void main(String[] args) {
        CarInventory inventory = new CarInventory();
        admin Admin = new admin("admin","admin",inventory); //Default creds for admin 
        Admin.login();     
    }
}
