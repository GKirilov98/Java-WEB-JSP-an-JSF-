package app.repository;

import app.domain.entities.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {
    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Job save(Job entity) {
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
    public Job update(Job entity) {
        this.entityManager.getTransaction().begin();
        try {
            Job updatedUser = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedUser;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Job> findAll() {
        this.entityManager.getTransaction().begin();
        List<Job> users = this.entityManager
                .createQuery("SELECT j FROM Job j ", Job.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public Job findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Job user = this.entityManager
                    .createQuery("SELECT j FROM Job j WHERE j.id = :id", Job.class)
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
    public Integer delete(Job job) {

        this.entityManager.getTransaction().begin();
        try {
            int id = this.entityManager.createQuery("DELETE FROM Job WHERE id=:id")
                    .setParameter("id", job.getId())
                    .executeUpdate();
            this.entityManager.getTransaction().commit();

            return id;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
