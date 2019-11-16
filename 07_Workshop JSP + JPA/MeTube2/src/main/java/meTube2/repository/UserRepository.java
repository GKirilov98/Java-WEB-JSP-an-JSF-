package meTube2.repository;


import meTube2.domain.entity.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username, String password);
}
