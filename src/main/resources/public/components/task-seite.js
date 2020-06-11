app.component("taskSeite", {
    templateUrl: "components/task-seite.html",
    controller: "taskSeiteController",
    bindings: {}
});

app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state({
        name: "tasks",
        url: "/",
        component: "taskSeite"
    });

    $urlRouterProvider.otherwise("/");
});


app.controller("taskSeiteController", function ($log, RestService, Task, AuthService) {
    $log.debug("taskSeiteController");

    /**
     * Holt alle Entities vom Server
     * @param number Anzahl der Seite
     */
    this.laden = (number) => {
        RestService.seiteLaden(Task, number, {size: 11, suchbegriff: this.suchbegriff || ""},
            'findByVerantwortlichUsernameContainsIgnoreCaseOrderByErledigtAscFaelligAsc')
            .then(seite => {
                $log.debug("test", seite);
                this.seite = seite;
            });
    }

    /**
     * Prüft ob der aktuelle Benutzer angemeldet ist
     */
    this.authenticated = () => {
        return AuthService.user();
    }

    this.laden();
});    