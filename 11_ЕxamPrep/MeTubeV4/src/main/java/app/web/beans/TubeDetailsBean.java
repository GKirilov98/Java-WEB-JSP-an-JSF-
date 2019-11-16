package app.web.beans;

import app.domain.models.service.TubeServiceModel;
import app.domain.models.view.TubeDetailsViewModel;
import app.service.TubeService;
import jdk.jfr.Registered;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Registered
public class TubeDetailsBean {
    private TubeDetailsViewModel tubeDetailsViewModel;
    private TubeService tubeService;
    private ModelMapper modelMapper;

    public TubeDetailsBean() {
    }

    @Inject
    public TubeDetailsBean(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        TubeServiceModel tubeServiceModel = this.tubeService.findById(id);
        tubeServiceModel.setViews(tubeServiceModel.getViews() + 1);
        this.tubeService.update(tubeServiceModel);
        String videoID = tubeServiceModel.getYoutubeId().replace("watch?v=" , "embed/");
        tubeServiceModel.setYoutubeId(videoID );
        this.tubeDetailsViewModel = this.modelMapper.map(tubeServiceModel,  TubeDetailsViewModel.class);
    }

    public TubeDetailsViewModel getTubeDetailsViewModel() {
        return tubeDetailsViewModel;
    }

    public void setTubeDetailsViewModel(TubeDetailsViewModel tubeDetailsViewModel) {
        this.tubeDetailsViewModel = tubeDetailsViewModel;
    }
}
