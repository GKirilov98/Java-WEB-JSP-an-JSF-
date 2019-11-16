package app.repository;

import app.domain.entities.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private final EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Hero save(Hero entity) {
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
    public Hero update(Hero entity) {
        this.entityManager.getTransaction().begin();
        try {
            Hero updatedUser = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedUser;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Hero> findAll() {
        this.entityManager.getTransaction().begin();
        List<Hero> heroes = this.entityManager
                .createQuery("SELECT j FROM Hero j ", Hero.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return heroes;
    }

    @Override
    public Hero findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Hero hero = this.entityManager
                    .createQuery("SELECT j FROM Hero j WHERE j.id = :id", Hero.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return hero;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Integer delete(String id) {

        this.entityManager.getTransaction().begin();
        try {
            int resultId = this.entityManager.createQuery("DELETE FROM Hero WHERE id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();

            return resultId;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
