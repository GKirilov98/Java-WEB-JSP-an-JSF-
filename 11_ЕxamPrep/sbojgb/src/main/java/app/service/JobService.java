package app.service;

import app.domain.models.service.JobServiceModel;

import java.util.List;

public interface JobService {
    boolean registerJob(JobServiceModel jobServiceModel);

    List<JobServiceModel> findAllJobs();
    JobServiceModel findById(String id);

    boolean deleteJob(JobServiceModel jobServiceModel);
}
