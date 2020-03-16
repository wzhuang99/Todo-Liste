"use strict";

app.factory("Task", function () {

    function Task(template, modifier) {

        // Schreibgeschützte Properties und ihre Defaultwerte
        let properties = {};

        Object.assign(this, properties, template, modifier);

        this.faellig = new Date(this.faellig);
        this.prio = this.prio || 1;

        // Properties schreibschützen
        Object.keys(properties).forEach(k => Object.defineProperty(this, k, { writable: false }));

        // Liefert eine neue Instanz dieses Objekts mit den angegebenen Änderungen
        this.variante = modifier => new Task(this, modifier);
    }

    // Relativer Pfad im REST-API
    Task.path = "aufgaben";

    return Task;
});
