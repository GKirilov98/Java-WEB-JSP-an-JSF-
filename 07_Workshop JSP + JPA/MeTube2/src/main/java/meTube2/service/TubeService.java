package meTube2.service;

import meTube2.domain.model.service.TubeServiceModel;

public interface TubeService {
    boolean saveTube(TubeServiceModel tubeServiceModel);

    TubeServiceModel findTubeByYouttubeId(String youtubeId);
}
