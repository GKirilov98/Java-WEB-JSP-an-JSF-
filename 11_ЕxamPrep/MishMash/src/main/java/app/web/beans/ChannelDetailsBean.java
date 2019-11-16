package app.web.beans;

import app.domain.models.service.ChannelServiceModel;
import app.domain.models.view.ChannelDetailsViewModel;
import app.service.ChannelService;
import jdk.jfr.Name;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ChannelDetailsBean {
    private ChannelDetailsViewModel viewModel;
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public ChannelDetailsBean() {
    }

    @Inject
    public ChannelDetailsBean(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        ChannelServiceModel chsm = this.channelService.getChannelById(id);
        this.viewModel = this.modelMapper.map(chsm, ChannelDetailsViewModel.class);
        this.viewModel.setFollowersCount(chsm.getFollowersId().size());
    }

    public ChannelDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(ChannelDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
