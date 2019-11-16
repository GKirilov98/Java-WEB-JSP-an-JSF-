package sevice;

import domain.entity.Tube;
import domain.model.service.TubeServiceModel;
import domain.model.view.TubeViewModel;
import repository.TubeRepository;
import utl.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(domain.model.service.TubeServiceModel tubeServiceModel) {
        this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public TubeServiceModel findTube(String name) {
        return (TubeServiceModel) this.tubeRepository.findAll()
                .stream()
                .filter(tube -> tube.getName().equals(name))
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .toArray()[0];
    }

    @Override
    public List<TubeServiceModel> findAll() {
       return this.tubeRepository.findAll()
                .stream()
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
               .collect(Collectors.toList());
    }
}
