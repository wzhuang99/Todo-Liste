package server.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import server.models.User;
import server.models.UserKompakt;

/**
 * Repository am Rest-API für Benutzer
 * Benutzer werden als Projektion exzerpiert
 */
@RepositoryRestResource(path="users", collectionResourceRel = "users", excerptProjection = UserKompakt.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * Annotation verhindert, dass Benutzer im Repository angezeigt werden
     * @param p Parameter für Pagination
     */
    @RestResource(exported=false)
    Page<User> findAll(Pageable p);

}
