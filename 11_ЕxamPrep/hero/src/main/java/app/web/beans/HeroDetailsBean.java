package app.web.beans;

import app.domain.models.service.HeroServiceModel;
import app.domain.models.view.HeroDetailsViewModel;
import app.service.HeroService;
import jdk.jfr.Registered;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Registered
public class HeroDetailsBean {
    private HeroDetailsViewModel viewModel;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDetailsBean() {
    }

    @Inject
    public HeroDetailsBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.viewModel = this.modelMapper.map(this.heroService.findById(id), HeroDetailsViewModel.class);
    }

    public HeroDetailsViewModel getHeroDetailsViewModel() {
        return viewModel;
    }

    public void setHeroDetailsViewModel(HeroDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
