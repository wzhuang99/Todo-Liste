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

    this.laden = (number) => {
        RestService.seiteLaden(Task, number, {size: 11, suchbegriff: this.suchbegriff || ""},
            'findByVerantwortlichUsernameContainsIgnoreCaseOrderByErledigtAscFaelligAsc')
            .then(seite => {
                $log.debug("User", this.seite);
                this.seite = seite;
            });
    }

    this.authenticated = () => {
        return AuthService.user();
    }

    this.laden();
});    