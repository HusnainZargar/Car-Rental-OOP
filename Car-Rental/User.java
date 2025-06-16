package carrental.carrental;
public abstract class User {
    protected String username;
    protected String password;
    //..........................getter
    public abstract String getUsername();
    public abstract String getPassword();
    //..........................setter
    public abstract void setUsername(String username);
    public abstract void setPassword(String password);
    
}

