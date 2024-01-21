
package ASM;


public class Remenber {
    private String Username;
    private String Password;
    private boolean Remenber;
    public Remenber() {
    }

    public Remenber(String Username, String Password, boolean Remenber) {
        this.Username = Username;
        this.Password = Password;
        this.Remenber = Remenber;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isRemenber() {
        return Remenber;
    }

    public void setRemenber(boolean Remenber) {
        this.Remenber = Remenber;
    }

    
    
}
