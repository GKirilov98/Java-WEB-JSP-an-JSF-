package meTube2.domain.model.servlets;

public class UserLoginServletModel {
    private String username;
    private String password;

    public UserLoginServletModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
