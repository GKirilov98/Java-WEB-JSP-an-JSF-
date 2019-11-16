package app.web.beans;

import app.domain.models.service.JobServiceModel;
import app.domain.models.view.JobDeleteViewModel;
import app.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobDeleteBean {
    private JobDeleteViewModel model;
    private JobService jobService;
    private ModelMapper modelMapper;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.model = this.modelMapper.map(this.jobService.findById(id), JobDeleteViewModel.class);
    }

    public JobDeleteViewModel getModel() {
        return model;
    }

    public void setModel(JobDeleteViewModel model) {
        this.model = model;
    }

    public void deleteJob() throws Exception {
        JobServiceModel jobServiceModel = this.modelMapper.map(this.model, JobServiceModel.class);
        if (this.jobService.deleteJob(jobServiceModel)){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        } else {
            throw new Exception("Cannot be deleted!");
        }
    }
}
