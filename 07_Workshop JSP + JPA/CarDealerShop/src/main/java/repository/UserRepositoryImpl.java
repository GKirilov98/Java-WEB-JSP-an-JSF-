package repository;

import domain.entity.User;

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
    public boolean save(User entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            throw new Error("Save User Error");
        }
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("" +
                "SELECT u FROM users u", User.class)
                .getResultList();
    }
}
