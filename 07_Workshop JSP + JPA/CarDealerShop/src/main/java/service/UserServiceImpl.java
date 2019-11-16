package service;

import domain.entity.User;
import domain.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        return this.userRepository.save(user);
    }

    @Override
    public List<UserServiceModel> findAll() {
      return   this.userRepository.findAll().stream()
                .map((u) -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }


}
