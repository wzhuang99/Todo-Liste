package server.models;

import org.springframework.data.rest.core.config.Projection;
import server.models.Task;


@Projection(name = "nurbeilage", types = Task.class)
public interface TaskNurBeilage {

    String getBeilage();

}
