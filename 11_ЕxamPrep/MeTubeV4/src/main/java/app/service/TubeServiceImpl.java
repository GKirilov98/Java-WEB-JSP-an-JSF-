package app.service;


import app.domain.entities.Tube;
import app.domain.entities.User;
import app.domain.models.service.TubeServiceModel;
import app.repository.TubeRepository;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository jobRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository jobRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);;
        User user = this.userRepository.findById(tubeServiceModel.getUploaderId());
        tube.setUploader(user);
        return this.jobRepository.save(tube) != null;
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        return this.jobRepository.findAll()
                .stream()
                .map(u ->{
                    TubeServiceModel map = this.modelMapper.map(u, TubeServiceModel.class);
                    map.setId(u.getId());
                    map.setYoutubeId(u.getYoutubeId());
                    map.setUploaderId(u.getUploader().getId());

                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TubeServiceModel findById(String id) {
        return this.modelMapper.map(this.jobRepository.findById(id), TubeServiceModel.class);
    }

    @Override
    public boolean deleteTube(String idToDelete) {
        Integer id = this.jobRepository.delete(idToDelete);
        return id != null;
    }

    @Override
    public void update(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        tube.setUploader(this.userRepository.findById(tubeServiceModel.getUploaderId()));
        this.jobRepository.update(tube);
    }
}
