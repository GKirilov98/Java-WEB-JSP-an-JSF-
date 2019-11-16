package app.web.beans;

import app.domain.models.view.TubeHomeViewModel;
import app.service.TubeService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<TubeHomeViewModel> homeViewModelList;
    private TubeService jobService;
    private UserService userService;
    private ModelMapper modelMapper;


    public HomeBean() {
    }

    @Inject
    public HomeBean(TubeService jobService, UserService userService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.homeViewModelList = this.jobService.findAllTubes()
                .stream()
                .map(j -> {
                    TubeHomeViewModel map = this.modelMapper.map(j, TubeHomeViewModel.class);
                    map.setId(j.getId());
                    String videoID = j.getYoutubeId().split("=")[1];
                    String thumbnailURL = "https://i.ytimg.com/vi/" + videoID + "/default.jpg";
                    map.setYoutubeId(thumbnailURL);
                    map.setTittle(j.getTitle());
                    map.setAuthor(j.getAuthor());

                    return map;
                })
                .collect(Collectors.toList());
        System.out.println();
    }


    public List<TubeHomeViewModel> getHomeViewModelList() {
        return homeViewModelList;
    }

    public void setHomeViewModelList(List<TubeHomeViewModel> homeViewModelList) {
        this.homeViewModelList = homeViewModelList;
    }
}
