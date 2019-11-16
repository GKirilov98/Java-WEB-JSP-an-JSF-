package app.web.beans;

import app.domain.models.view.JobDetailsViewModel;
import app.service.JobService;
import jdk.jfr.Registered;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Registered
public class JobDetailsBean {
    private JobDetailsViewModel jobDetailsViewModel;
    private JobService jobService;
    private ModelMapper modelMapper;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.jobDetailsViewModel = this.modelMapper.map(this.jobService.findById(id), JobDetailsViewModel.class);
    }

    public JobDetailsViewModel getJobDetailsViewModel() {
        return jobDetailsViewModel;
    }

    public void setJobDetailsViewModel(JobDetailsViewModel jobDetailsViewModel) {
        this.jobDetailsViewModel = jobDetailsViewModel;
    }
}
