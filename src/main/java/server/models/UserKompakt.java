package server.models;

import org.springframework.data.rest.core.config.Projection;

/**
 * Projektion für Benutzer
 * liefert Benutzernamen und Avatar
 */
@Projection(name="kompakt", types = User.class)
public interface UserKompakt {
    String getUsername();
    String getAvatar();
}
