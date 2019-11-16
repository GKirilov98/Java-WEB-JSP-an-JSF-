package app.web.beans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.TubeProfileViewModel;
import app.domain.models.view.UserProfileViewModel;
import app.service.TubeService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
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
public class UserProfileBean {
    private UserProfileViewModel viewModel;
    private UserService userService;
    private TubeService tubeService;
    private ModelMapper modelMapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, TubeService tubeService, ModelMapper modelMapper) {
        this.userService = userService;
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String userId = session.getAttribute("id").toString();
        UserServiceModel usm = this.userService.findById(userId);
        this.viewModel = this.modelMapper.map(usm, UserProfileViewModel.class);
        List<TubeProfileViewModel> tubes = this.tubeService.findAllTubes()
                .stream()
                .filter(t -> t.getUploaderId().equals(userId))
                .map(t -> {
                    TubeProfileViewModel map = this.modelMapper.map(t, TubeProfileViewModel.class);
                    map.setId(map.getId());
                    return map;
                })
                .collect(Collectors.toList());
        this.viewModel.setTubes(tubes);
    }


    public UserProfileViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(UserProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void deleteTube(String id) throws IOException {
        this.tubeService.deleteTube(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/profile");
    }
}
