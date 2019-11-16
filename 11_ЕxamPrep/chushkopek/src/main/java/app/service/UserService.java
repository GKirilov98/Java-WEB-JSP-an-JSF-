package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel loginUser(UserServiceModel userServiceModel);

    UserServiceModel getUserById(String id);

    List<UserServiceModel> findAll();

    boolean addFriend(UserServiceModel userServiceModel);

    boolean removeFriend(UserServiceModel userServiceModel);
}
