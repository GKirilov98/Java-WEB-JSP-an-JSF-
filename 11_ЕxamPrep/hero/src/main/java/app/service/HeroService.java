package app.service;

import app.domain.models.service.HeroServiceModel;

import java.util.List;

public interface HeroService {
    boolean registerHero(HeroServiceModel jobServiceModel);

    List<HeroServiceModel> findAllHeros();
    HeroServiceModel findById(String id);

    boolean deleteHero(String id);
}
