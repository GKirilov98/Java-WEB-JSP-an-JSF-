package app.repository;


import app.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube entity) {
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
    public Tube update(Tube entity) {
        this.entityManager.getTransaction().begin();
        try {
            Tube update = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return update;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> users = this.entityManager
                .createQuery("SELECT t FROM Tube t ", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public Tube findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Tube user = this.entityManager
                    .createQuery("SELECT t FROM Tube t WHERE t.id = :id", Tube.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Integer delete(String idToDelete) {

        this.entityManager.getTransaction().begin();
        try {
            int id = this.entityManager.createQuery("DELETE FROM Tube WHERE id=:id")
                    .setParameter("id", idToDelete)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();

            return id;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
