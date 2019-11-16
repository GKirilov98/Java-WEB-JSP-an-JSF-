package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserHomeViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<UserHomeViewModel> models;
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        String id = session.getAttribute("id").toString();

        UserServiceModel loggedInUser = this.userService.getUserById(id);
        this.models = this.userService
                .findAll()
                .stream()
                .filter(u -> !u.getId().equals(id) &&
                        !loggedInUser.getFriends()
                                .stream()
                                .map(UserServiceModel::getId)
                                .collect(Collectors.toList())
                                .contains(u.getId())
                )
                .map(usm -> this.modelMapper.map(usm, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserHomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<UserHomeViewModel> models) {
        this.models = models;
    }

    public void addFriend(String id) throws IOException {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        String loggedId = session.getAttribute("id").toString();
        UserServiceModel loggedInUser = this.userService.getUserById(loggedId);
        UserServiceModel friend = this.userService.getUserById(id);
        loggedInUser.getFriends().add(friend);
        friend.getFriends().add(loggedInUser);

        if (!this.userService.addFriend(loggedInUser) || !this.userService.addFriend(friend)) {
            loggedInUser.getFriends().remove(friend);
            friend.getFriends().remove(loggedInUser);
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
