package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserProfileViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class UserProfileBean  {
    private UserProfileViewModel profileView;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.intiModel();
    }

    private void intiModel() {
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        UserServiceModel userServiceModel = this.userService.getUserById(id);

        if (userServiceModel == null){
            throw new IllegalArgumentException("Profile went wrong!");
        }

        this.profileView = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
    }


    public UserProfileViewModel getProfileView() {
        return profileView;
    }

    public void setProfileView(UserProfileViewModel profileView) {
        this.profileView = profileView;
    }


}
