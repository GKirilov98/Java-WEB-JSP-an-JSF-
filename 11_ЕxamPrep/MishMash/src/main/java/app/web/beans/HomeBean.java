package app.web.beans;

import app.domain.models.view.HomeChannelViewModel;
import app.service.ChannelService;
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
    private List<HomeChannelViewModel> homeChannelViewModelList;
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        homeChannelViewModelList = this.channelService.findAll()
                .stream()
                .map(chsm -> {
                    HomeChannelViewModel map = this.modelMapper
                            .map(chsm, HomeChannelViewModel.class);
                    map.setFollowersCount(chsm.getFollowersId().size());
                    return map; })
                .collect(Collectors.toList());
    }


    public List<HomeChannelViewModel> getHomeChannelViewModelList() {
        return homeChannelViewModelList;
    }

    public void setHomeChannelViewModelList(List<HomeChannelViewModel> homeChannelViewModelList) {
        this.homeChannelViewModelList = homeChannelViewModelList;
    }
}
