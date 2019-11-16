package meTube2.service;

import meTube2.domain.entity.User;
import meTube2.domain.model.service.UserServiceModel;

public interface UserService {
    boolean saveUser(UserServiceModel userServiceModel);
    UserServiceModel findUserByNameAndPassword(String username, String password);

    UserServiceModel findUserByUsername(String username, String password);
}
