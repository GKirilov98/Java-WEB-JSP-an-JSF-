package app.web.beans;

import app.domain.models.view.HeroDeleteViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class HeroDeleteBean {
    private HeroDeleteViewModel viewModel;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDeleteBean() {
    }

    @Inject
    public HeroDeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        this.viewModel = this.modelMapper.map(this.heroService.findById(id), HeroDeleteViewModel.class);
       System.out.println();
    }

    public HeroDeleteViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(HeroDeleteViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void deleteHero() throws Exception {

        if (this.heroService.deleteHero(this.viewModel.getId())){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        } else {
            throw new Exception("Cannot be deleted!");
        }
    }
}
