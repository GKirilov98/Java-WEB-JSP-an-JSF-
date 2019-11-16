package meTube2.repository;

import meTube2.domain.entity.Tube;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Tube entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(entity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> tubes = this.entityManager
                .createQuery("SELECT t FROM tubes t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return tubes;
    }
}
