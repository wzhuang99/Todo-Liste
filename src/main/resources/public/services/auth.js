"use strict";

/**
 * Verwaltet An- und Abmeldungen von Benutzern dieser App am Server.
 *
 * Siehe die Methoden login(...), logout(), istAngemeldet() und user().
 */
app.service("AuthService", function($http, $cookies, $log) {

    $log.debug("AuthService()");

    const
        LOGIN = "http://localhost:8181/login",
        LOGOUT = "http://localhost:8181/logout",
        ME = "http://localhost:8181/api/me",
        TOKEN_HEADER = "x-auth-token",
        SESSION_COOKIE = "SESSION";


    /**
     * Entity der angemeldeten Benutzerin, oder undefined, wenn niemand angemeldet ist.
     */
    let user;
    this.user = () => user;


    /**
     * Setzt die Authentifizierungsdaten für einen Benutzer oder löscht sie,
     * wenn kein Argumente angegeben sind.
     */
    let authentifizieren = (user_, token) => {
        user = user_;
        $http.defaults.headers.common[TOKEN_HEADER] = token;
    };


    /**
     * Versucht den angegebenen Benutzer am Server anzumelden und liefert
     * ein Promise auf das Response-Objekt.
     */
    this.login = (username, password) => {
        return $http
            .post(LOGIN, {}, { params: { username: username, password: password } })
            .then(response => {
                $log.debug(`AuthService.login("${username}"): OK`, response.data, response.headers(TOKEN_HEADER));

                authentifizieren(response.data, response.headers(TOKEN_HEADER));
                return Promise.resolve(response);
            })
            .catch(response => {
                $log.error("AuthService.login(): Fehler", response);

                authentifizieren();
                return Promise.reject(response);
            });
    };


    /**
     * Meldet den gerade angemeldeten Benutzer wieder vom Server ab und liefert
     * ein Promise auf das Response-Objekt.
     */
    this.logout = () => {
        return $http
            .post(LOGOUT)
            .then(response => {
                $log.debug("AuthService.logout(): OK");
                return Promise.resolve(response);
            })
            .catch(response => {
                $log.error("AuthService.logout(): Fehler", response);
                return Promise.reject(response);
            })
            .finally(() => {
                authentifizieren();
            });
    };

});
