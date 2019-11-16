package app.repository;

import app.domain.entities.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManager entityManager;

    @Inject
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product save(Product entity) {
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
    public Product update(Product entity) {
        this.entityManager.getTransaction().begin();
        try {
            Product updatedProduct = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedProduct;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM Product p ", Product.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Product findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Product product = this.entityManager
                    .createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return product;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean delete(String id) {

        this.entityManager.getTransaction().begin();
        try {
            this.entityManager
                    .createQuery("DELETE FROM Product p WHERE p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            this.entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return false;
        }
    }
}
