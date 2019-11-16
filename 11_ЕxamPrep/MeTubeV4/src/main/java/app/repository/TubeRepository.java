package app.repository;

import app.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
    Integer delete(String id);
}
