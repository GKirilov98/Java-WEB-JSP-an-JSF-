package repository;

import domain.entity.Car;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    private final EntityManager entityManager;

    @Inject
    public CarRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(Car entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            throw new Error("Save Car Error");
        }

    }

    @Override
    public List<Car> findAll() {
        return this.entityManager.createQuery("" +
                "SELECT c FROM cars c", Car.class)
                .getResultList();
    }
}
