"use strict";

/**
 * Ist noch niemand angemeldet, dann zeigt diese Komponente Eingabefelder für
 * Benutzernamen und Passwort sowie einen "Anmelden"-Button. Sobald man sich
 * angemeldet hat, zeigt diese Komponente einen "Abmelden"-Button mit dem
 * Benutzernamen.
 *
 * Verwendung:
 *   <auth login-logout="..."></auth>
 *
 * Attribute (optional, wenn nicht anders angegeben):
 *   login-logout AngularJS-Ausdruck (Ausgang), der ausgewertet wird, sobald sich
 *                jemand an- oder abgemeldet hat
 */
app.component("auth", {
    templateUrl: "components/auth.html",
    controller: "AuthController",
    bindings: {
        loginLogout: "&"
    }
});


app.controller("AuthController", function ($log, AuthService) {

    $log.debug("AuthController()");

    // Nur zum Testen
    this.username = "user";
    this.password = "user";


    this.AuthService = AuthService;

    this.login = () => {
        AuthService
            .login(this.username, this.password)
            .then(() => {
                delete this.username;
                this.loginLogout();
            })
            .finally(() => {
                delete this.password;
            });
    };

    this.logout = () => {
        AuthService
            .logout()
            .then(() => {
                this.loginForm.$setUntouched();
                this.loginForm.$setPristine();
                this.loginLogout();
            });
    };
});
