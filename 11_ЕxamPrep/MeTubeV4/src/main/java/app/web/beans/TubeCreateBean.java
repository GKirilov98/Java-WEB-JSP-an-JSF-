package app.web.beans;


import app.domain.models.binding.TubeCreateBindingModel;
import app.domain.models.service.TubeServiceModel;
import app.service.TubeService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class TubeCreateBean {
    private TubeCreateBindingModel model;
    private TubeService jobService;
    private ModelMapper modelMapper;


    public TubeCreateBean() {
    }

    @Inject
    public TubeCreateBean(TubeService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new TubeCreateBindingModel();
    }

    public TubeCreateBindingModel getModel() {
        return model;
    }

    public void setModel(TubeCreateBindingModel model) {
        this.model = model;
    }


    public void createTube() throws IOException {

        TubeServiceModel tubeServiceModel = this.modelMapper.map(this.model, TubeServiceModel.class);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        tubeServiceModel.setId(null);
        tubeServiceModel.setUploaderId(session.getAttribute("id").toString());
        if (session.getAttribute("id") == null || !this.jobService.createTube(tubeServiceModel)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/tube/upload");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
