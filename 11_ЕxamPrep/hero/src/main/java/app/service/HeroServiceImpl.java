package app.service;

import app.domain.entities.Hero;

import app.domain.entities.HeroClass;
import app.domain.models.service.HeroServiceModel;
import app.repository.HeroRepository;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerHero(HeroServiceModel heroServiceModel) {
        Hero hero = this.modelMapper.map(heroServiceModel, Hero.class);
        hero.setHeroClass(HeroClass.valueOf(heroServiceModel.getHeroType()));
        return this.heroRepository.save(hero) != null;
    }

    @Override
    public List<HeroServiceModel> findAllHeros() {
        return this.heroRepository.findAll()
                .stream()
                .map(u -> {
                    HeroServiceModel map = this.modelMapper.map(u, HeroServiceModel.class);
                    map.setHeroType(u.getHeroClass().name());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HeroServiceModel findById(String id) {
        Hero hero = this.heroRepository.findById(id);
        HeroServiceModel hsm = this.modelMapper.map(hero, HeroServiceModel.class);
        hsm.setHeroType(hero.getHeroClass().name());
        return hsm;
    }

    @Override
    public boolean deleteHero(String toDeleteId) {
        Integer resultId = this.heroRepository.delete(toDeleteId);
        return resultId != null;
    }
}
