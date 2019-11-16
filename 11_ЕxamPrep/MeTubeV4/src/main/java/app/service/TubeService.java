package app.service;


import app.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {
    boolean createTube(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> findAllTubes();
    TubeServiceModel findById(String id);

    boolean deleteTube(String id);

    void update(TubeServiceModel tubeServiceModel);
}
