package app.web.beans;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserLoginBindingModel loginModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.loginModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(UserLoginBindingModel loginModel) {
        this.loginModel = loginModel;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = this.userService.loginUser(
                this.modelMapper.map(this.loginModel, UserServiceModel.class));
        if (userServiceModel == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("username", userServiceModel.getUsername());
            session.setAttribute("id", userServiceModel.getId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
