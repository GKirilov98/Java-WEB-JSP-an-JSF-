package app.service;

import app.domain.entities.Job;
import app.domain.entities.Sector;
import app.domain.entities.User;
import app.domain.models.service.JobServiceModel;
import app.repository.JobRepository;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerJob(JobServiceModel jobServiceModel) {
        Job job = this.modelMapper.map(jobServiceModel, Job.class);
        job.setSector(Sector.valueOf(jobServiceModel.getSector()));
        User user = this.userRepository.findById(jobServiceModel.getUserId());
        job.setUser(user);
        return this.jobRepository.save(job) != null;
    }

    @Override
    public List<JobServiceModel> findAllJobs() {
        return this.jobRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, JobServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobServiceModel findById(String id) {
        return this.modelMapper.map(this.jobRepository.findById(id), JobServiceModel.class);
    }

    @Override
    public boolean deleteJob(JobServiceModel jobServiceModel) {
        Integer id = this.jobRepository.delete(this.modelMapper.map(jobServiceModel, Job.class));
        return id != null;
    }
}
