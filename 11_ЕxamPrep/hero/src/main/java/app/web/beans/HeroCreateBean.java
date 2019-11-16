package app.web.beans;

import app.domain.models.binding.HeroCreateBindingModel;
import app.domain.models.service.HeroServiceModel;
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
public class HeroCreateBean {
    private HeroCreateBindingModel model;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new HeroCreateBindingModel();
    }

    public HeroCreateBindingModel getModel() {
        return model;
    }

    public void setModel(HeroCreateBindingModel model) {
        this.model = model;
    }


    public void registerHero() throws IOException {
        HeroServiceModel herServiceModel = this.modelMapper.map(this.model, HeroServiceModel.class);

        if (!this.heroService.registerHero(herServiceModel)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/heroes/create");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
        }
    }
}
