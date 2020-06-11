app.component("task", {
    templateUrl: "components/task.html",
    controller: "taskController",
    bindings: {
        task: "<",
    }
});

app.controller("taskController", function ($log, AuthService) {

    /**
     * Überprüft den angemeldeten Benutzer
     */
    this.authorised = () => {
        let authenticated  = AuthService.user();
        return authenticated && authenticated.username === (this.task.verantwortlich.username);
    }

});    