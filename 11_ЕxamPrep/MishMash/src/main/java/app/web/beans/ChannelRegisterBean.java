package app.web.beans;

import app.domain.entities.Type;
import app.domain.models.binding.ChannelRegisterBindingModel;
import app.domain.models.service.ChannelServiceModel;
import app.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Arrays;

@Named
@RequestScoped
public class ChannelRegisterBean {
    private ChannelRegisterBindingModel model;
    private String[] channelTypes;
    private ChannelService channelService;
    private ModelMapper modelMapper;

    public ChannelRegisterBean() {
    }

    @Inject
    public ChannelRegisterBean(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.model = new ChannelRegisterBindingModel();
        this.channelTypes = Arrays.stream(Type.values()).map(Enum::name).toArray(String[]::new);
    }

    public String[] getChannelTypes() {
        return channelTypes;
    }

    public void setChannelTypes(String[] channelTypes) {
        this.channelTypes = channelTypes;
    }

    public ChannelRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(ChannelRegisterBindingModel model) {
        this.model = model;
    }

    public void registerChannel() throws IOException {
        if (this.model.getType().equals("")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/channels/register");
            return;
        }

        ChannelServiceModel channelServiceModel = this.modelMapper.map(this.model, ChannelServiceModel.class);

        if (!this.channelService.registerChannel(channelServiceModel)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/channels/register");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
