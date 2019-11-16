package repository;

import domain.entity.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private  EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("meTube")
                .createEntityManager();
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> tubes = this.entityManager
                .createQuery("SELECT p FROM tubes p")
                .getResultList();
        this.entityManager.getTransaction().commit();
        return tubes;
    }
}
