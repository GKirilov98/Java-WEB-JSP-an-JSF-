package app.domain.models.binding;

public class UserLoginBindingModel {
    private String Username;
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
