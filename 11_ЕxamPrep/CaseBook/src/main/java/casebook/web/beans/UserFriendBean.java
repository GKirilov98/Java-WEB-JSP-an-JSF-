package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserFriendsViewModel;
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
public class UserFriendBean {
    private List<UserFriendsViewModel> models;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendBean() {
    }

    @Inject
    public UserFriendBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        String loggedId = session.getAttribute("id").toString();
        this.models = this.userService.getUserById(loggedId).getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserFriendsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserFriendsViewModel> getModels() {
        return models;
    }

    public void setModels(List<UserFriendsViewModel> models) {
        this.models = models;
    }

    public void removeFriend(String id) throws IOException {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);
        String loggedId = session.getAttribute("id").toString();
        UserServiceModel loggedInUser = this.userService.getUserById(loggedId);
        UserServiceModel friend = this.userService.getUserById(id);
        loggedInUser.setFriends(
                loggedInUser.getFriends().stream()
                .filter(u -> !u.getId().equals(friend.getId()))
                .collect(Collectors.toList())
        );

        friend.setFriends(
                friend.getFriends().stream()
                        .filter(u -> !u.getId().equals(loggedInUser.getId()))
                        .collect(Collectors.toList())
        );

        if (!this.userService.removeFriend(loggedInUser) || !this.userService.removeFriend(friend)) {
            loggedInUser.getFriends().add(friend);
            friend.getFriends().add(loggedInUser);
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/friends");
    }
}
