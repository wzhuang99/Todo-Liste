app.component("taskEditor", {
    templateUrl: "components/task-editor.html",
    controller: "taskEditorController",
    bindings: {}
});

app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state({
        name: "taskedit",
        params: {task: null},
        component: "taskEditor"
    });

    $urlRouterProvider.otherwise("/");
});


app.controller("taskEditorController", function ($log, $state, $stateParams, Task, RestService, AuthService) {
    $log.debug("taskEditorController")

    this.task = new Task($stateParams.task);

    /**
     * Zurück-Button
     */
    this.back = () => {
        $state.go("tasks");
    };

    /**
     * Sendet PATCH/POST-Abfrage an den Server mit der aktuellen Entity
     * Entity wird anschließend in der Datenbank gespeichert
     */
    this.save = () => {
        this.task.verantwortlich = AuthService.user();
        RestService.speichern(this.task)
            .then(() => {
                $state.go("tasks");
            })
    };

});