package meTube2.service;

import meTube2.domain.entity.User;
import meTube2.domain.model.service.TubeServiceModel;
import meTube2.domain.model.service.UserServiceModel;
import meTube2.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

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
        return this.userRepository.save(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public UserServiceModel findUserByNameAndPassword(String username, String password) {
        return this.userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username) &&
                        user.getPassword().equals(password))
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .findFirst().orElse(null);
    }

    @Override
    public UserServiceModel findUserByUsername(String username, String password) {
        User user = this.userRepository.findByUsername(username, password);

        if (user == null) {
            throw new IllegalArgumentException("Something went wrong in UserServiceImpl class, findUserByUsername method!");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
           user.getTubes().forEach(t -> userServiceModel.getTubesSM().add(
                   this.modelMapper.map(t, TubeServiceModel.class)));
        return userServiceModel;
    }
}
