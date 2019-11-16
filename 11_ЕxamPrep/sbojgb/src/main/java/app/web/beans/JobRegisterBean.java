package app.web.beans;

import app.domain.entities.Sector;
import app.domain.models.binding.JobRegisterBindingModel;
import app.domain.models.service.JobServiceModel;
import app.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@Named
@RequestScoped
public class JobRegisterBean {
    private JobRegisterBindingModel model;
    private JobService jobService;
    private ModelMapper modelMapper;
    private String[] jobSectors;

    public JobRegisterBean() {
    }

    @Inject
    public JobRegisterBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new JobRegisterBindingModel();
        this.jobSectors = Arrays.stream(Sector.values()).map(Enum::name).toArray(String[]::new);
    }

    public JobRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(JobRegisterBindingModel model) {
        this.model = model;
    }

    public String[] getJobSectors() {
        return jobSectors;
    }

    public void setJobSectors(String[] jobSectors) {
        this.jobSectors = jobSectors;
    }

    public void registerJob() throws IOException {

        if (this.model.getSector().equals("")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/jobs/add");
            return;
        }

        JobServiceModel jobServiceModel = this.modelMapper.map(this.model, JobServiceModel.class);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        jobServiceModel.setUserId(session.getAttribute("id").toString());
        if (session.getAttribute("id") == null || !this.jobService.registerJob(jobServiceModel)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/jobs/add");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
