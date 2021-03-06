package server.models;

import at.rennweg.htl.sew.autoconfig.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Fachklasse für die Tabelle User
 * Erzeugt Benutzer
 */
@Entity
public class User extends Persistent implements UserInfo {

    /**
     * Benutzername
     */
    @NotBlank
    @Column(unique = true, updatable = false)
    private String username;

    /**
     * Passwort
     */
    @NotNull
    @JsonIgnore
    private String password;

    /**
     * Bild/Avatar vom Benutzer
     */
    @Lob
    private String avatar;


    @Override
    public String getUsername() { return username; }


    public void setUsername(String name) { this.username = name; }


    @Override
    public String getPassword() { return password; }


    public void setPassword(String password) { this.password = password; }


    public String getAvatar() { return avatar; }


    public void setAvatar(String avatar) { this.avatar = avatar; }

}
