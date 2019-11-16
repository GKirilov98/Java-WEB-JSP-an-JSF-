package sevice;

import domain.model.service.TubeServiceModel;
import domain.model.view.TubeViewModel;
import web.servlet.AllTubesServlet;

import java.util.List;

public interface TubeService {
    void saveTube(domain.model.service.TubeServiceModel tubeServiceModel);

    TubeServiceModel findTube(String name);

    List<TubeServiceModel> findAll();
}
