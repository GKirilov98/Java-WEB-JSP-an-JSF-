package meTube2.service;

import meTube2.domain.entity.Tube;
import meTube2.domain.entity.User;
import meTube2.domain.model.service.TubeServiceModel;
import meTube2.domain.model.service.UserServiceModel;
import meTube2.repository.TubeRepository;
import meTube2.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        User user = this.userRepository
                .findAll()
                .stream()
                .filter(u -> u.getUsername().equals(tube.getUser().getUsername()))
                .findFirst().orElse(null);
        tube.setUser(user);
        return this.tubeRepository.save(tube);
    }

    @Override
    public TubeServiceModel findTubeByYouttubeId(String youtubeId) {
       return this.tubeRepository.findAll()
                .stream()
                .filter(t -> t.getYoutubeId().equals(youtubeId))
                .map(t -> {
                    TubeServiceModel tsm = this.modelMapper.map(t, TubeServiceModel.class);
                    tsm.setUser(this.modelMapper.map(t.getUser(), UserServiceModel.class));
                    return tsm;
                })
                .findFirst()
                .orElse(null);
    }
}
