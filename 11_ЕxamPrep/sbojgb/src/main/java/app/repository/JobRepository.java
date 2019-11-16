package app.repository;

import app.domain.entities.Job;

public interface JobRepository extends GenericRepository<Job, String> {
    Integer delete(Job map);
}
