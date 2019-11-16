package repository;

import java.util.List;

public interface GenericRepository<Entity, Key> {
    Entity save(Entity entity);
    List<Entity> findAll();
}
