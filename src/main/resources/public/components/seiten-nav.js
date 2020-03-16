"use strict";

/**
 * Zeigt einen Toolbar mit einem Navigationsfeld zum Blättern zwischen Seiten an.
 *
 * Ein optionaler Titeltext wird links im Toolbar platziert. Elemente, die als
 * inneres HTML-Markup angegeben sind, werden rechts im Toolbar platziert.
 *
 * Verwendung:
 *   <seiten-nav ...>inneres HTML-Markup</seiten-nav>
 *
 * Attribute (optional, wenn nicht anders angegeben):
 *   seite        (erforderlich) das angezeigte Seite-Objekt, muss mit der Seite-Factory
 *                erzeugt worden sein.
 *   laden        (erforderlich) AngularJS-Ausdruck, der bei einem Seitenwechsel
 *                ausgewertet wird. Die Variable "number" enthält die Seitennummer, die
 *                geladen werden soll.
 *   titel        Titeltext, wird links im Toolbar platziert.
 */
app.component("seitenNav", {
    templateUrl: "components/seiten-nav.html",
    bindings: {
        seite: "<",
        laden: "&",
        titel: "@",
    },
    transclude: true,
});
