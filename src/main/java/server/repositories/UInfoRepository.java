package server.repositories;

import at.rennweg.htl.sew.autoconfig.UserInfoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import server.models.User;
import server.models.UserKompakt;

/**
 * Login-Repository für Benutzer
 * muss von UserInfoRepository ableiten
 */
@RepositoryRestResource(excerptProjection = UserKompakt.class)
public interface UInfoRepository extends UserInfoRepository<User> {

}
