package meTube2.domain.model.service;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private List<TubeServiceModel> tubesSM;

    public UserServiceModel() {
        tubesSM = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TubeServiceModel> getTubesSM() {
        return tubesSM;
    }

    public void setTubesSM(List<TubeServiceModel> tubesSM) {
        this.tubesSM = tubesSM;
    }
}
