package service;

import domain.model.service.UserServiceModel;
import repository.UserRepository;

import java.util.List;

public interface UserService {

    boolean saveUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAll();

}
