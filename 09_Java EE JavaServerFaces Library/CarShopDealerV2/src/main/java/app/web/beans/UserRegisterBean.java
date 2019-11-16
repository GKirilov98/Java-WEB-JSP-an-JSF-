package app.web.beans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }

    public void register() throws IOException {
        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        if (!model.getPassword().equals(model.getConfirmPassword()) ||
               !this.userService.registerUser(userServiceModel)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/register");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
        }
    }
}
