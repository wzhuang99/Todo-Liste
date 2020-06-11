package server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import server.models.Task;

/**
 * Repository am Rest-API für Aufgaben
 */
@RepositoryRestResource(path="aufgaben", collectionResourceRel = "aufgaben")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Page<Task> findAll(Pageable p);

    /**
     * nur angemeldete Benutzer können Aufgaben erstellen
     * Benutzer dürfen nur eigene Aufgaben ändern
     * @param t entity
     * @param <T> Subklasse
     */
    @PreAuthorize("isAuthenticated() and #t.verantwortlich.username.equals(principal.username)")
    <T extends Task> T save(@Param("t") T t);

    /**
     * nur angemeldete Benutzer können Aufgaben löschen
     * Benutzer dürfen nur eigene Aufgaben löschen
     * @param t entity
     * @param <T> Subklasse
     */
    @PreAuthorize("isAuthenticated() and #t.verantwortlich.username.equals(principal.username)")
    <T extends Task> T delete(@Param("t") T t);

    /**
     * Query-Methode um Aufgaben anhand der Benutzer zu finden
     * @param s eingegebener Suchbegriff
     * @param p Parameter für Pagination
     */
    Page<Task> findByVerantwortlichUsernameContainsIgnoreCaseOrderByErledigtAscFaelligAsc (@Param("suchbegriff") String s, Pageable p);
}
