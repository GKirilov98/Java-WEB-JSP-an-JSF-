package app.web.beans;

import app.domain.models.view.HomeViewModel;
import app.service.JobService;
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
    private List<HomeViewModel> homeViewModelList;
    private JobService jobService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.homeViewModelList = this.jobService.findAllJobs()
                .stream()
                .map(j -> {
                    HomeViewModel map = this.modelMapper.map(j, HomeViewModel.class);
                    map.setSector(j.getSector().toLowerCase());
                    return  map;
                })
                .collect(Collectors.toList());
        System.out.println();
    }


    public List<HomeViewModel> getHomeViewModelList() {
        return homeViewModelList;
    }

    public void setHomeViewModelList(List<HomeViewModel> homeViewModelList) {
        this.homeViewModelList = homeViewModelList;
    }
}
