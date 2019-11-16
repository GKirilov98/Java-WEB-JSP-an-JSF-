package meTube2.repository;

import java.util.List;

public interface GenericRepository<Entity, Id> {
    boolean save (Entity entiti);
    List<Entity> findAll();
}
