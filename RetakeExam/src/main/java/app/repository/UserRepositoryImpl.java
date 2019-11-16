package app.repository;

import app.domain.entities.User;

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
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public User update(User entity) {
        this.entityManager.getTransaction().begin();
        try {
            User updated = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updated;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> founded = this.entityManager
                .createQuery("SELECT u FROM User u ", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return founded;
    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            User entity = this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Integer delete(String id) {
        this.entityManager.getTransaction().begin();
        try {
            int resultId = this.entityManager.createQuery("DELETE FROM User WHERE id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();

            return resultId;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        this.entityManager.getTransaction().begin();
        try {
            User user = this.entityManager.createQuery("" +
                    "SELECT u FROM User u " +
                    "WHERE u.username = :username " +
                    "AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
