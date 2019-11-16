package app.web.beans;

import app.domain.models.view.HeroHomeViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<HeroHomeViewModel> viewModels;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        viewModels = this.heroService.findAllHeros()
                .stream()
                .map(h -> {
                    HeroHomeViewModel map = this.modelMapper.map(h, HeroHomeViewModel.class);
                    map.setHeroType(h.getHeroType().toLowerCase());
                    return map;
                })
                .collect(Collectors.toList());
        System.out.println();
    }


    public List<HeroHomeViewModel> getViewModels() {
        return viewModels;
    }

    public void setViewModels(List<HeroHomeViewModel> viewModels) {
        this.viewModels = viewModels;
    }

}