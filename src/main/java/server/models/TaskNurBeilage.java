package server.models;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projektion für die Task-Fachklasse
 * Exportiert die Beilage von einer Aufgabe
 */
@Projection(name = "nurbeilage", types = Task.class)
public interface TaskNurBeilage {

    String getBeilage();

}
