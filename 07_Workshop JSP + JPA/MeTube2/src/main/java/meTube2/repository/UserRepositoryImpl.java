package meTube2.repository;

import meTube2.domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(User user) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> users = this.entityManager
                .createQuery("SELECT u FROM users u", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return users;
    }

    @Override
    public User findByUsername(String username, String password) {
        this.entityManager.getTransaction().begin();

        User user;
        try {
            user = this.entityManager.createQuery("" +
                    "SELECT u " +
                    "FROM users AS u " +
                    "WHERE u.username = :username AND u.password=:pass", User.class)
                    .setParameter("username", username)
                    .setParameter("pass", password)
                    .getResultList()
                    .get(0);
            return user;
        } catch (Exception e) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }
}
