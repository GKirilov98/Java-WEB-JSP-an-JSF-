package app.web.beans;

import app.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class ChannelDeleteBean {
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public ChannelDeleteBean() {
    }

    @Inject
    public ChannelDeleteBean(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    public void deleteChannel(String id) throws Exception {
        if (this.channelService.deleteChannel(id)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        } else {
            throw new Exception("Cannot be deleted!");
        }
    }
}
