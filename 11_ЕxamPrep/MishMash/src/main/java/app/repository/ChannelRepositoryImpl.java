package app.repository;

import app.domain.entities.Channel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ChannelRepositoryImpl implements ChannelRepository {
    private final EntityManager entityManager;

    @Inject
    public ChannelRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Channel save(Channel entity) {
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
    public Channel update(Channel entity) {
        this.entityManager.getTransaction().begin();
        try {
            Channel updateChannels = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updateChannels;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public List<Channel> findAll() {
        this.entityManager.getTransaction().begin();
        List<Channel> channels = this.entityManager
                .createQuery("SELECT j FROM Channel j ", Channel.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return channels;
    }

    @Override
    public Channel findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Channel channel = this.entityManager
                    .createQuery("SELECT j FROM Channel j WHERE j.id = :id", Channel.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return channel;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean deleteChannel(String id) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager
                    .createQuery("DELETE FROM Channel c WHERE c.id = :id")
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
