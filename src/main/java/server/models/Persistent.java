package server.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;


/**
 * Basis-Fachklasse für jede Entity-Klasse
 *
 */
@MappedSuperclass
public class Persistent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private long version;


    /** wird nicht am Rest-API angezeigt */
    public Long getId() {
        return id;
    }


    public long getETag() {
        return version;
    }


    /**
     * Hilfsmethode für die 1-Seite einer 1:n Beziehung, die auch die n-Seite aktualisiert.
     *
     * @param newCollection
     *      neu zugewiesene {@link Collection} für die n-seite
     * @param setEntity
     *      set-{@linkplain BiConsumer Methode} der n-Seite für die Entity der 1-Seite
     * @param getCollection
     *      get-{@linkplain Function Methode} der 1-Seite für die  {@link Collection} von entities der n-Seite
     * @param <O>
     *      Datentyp der 1-seite
     * @param <M>
     *      Datentyp der n-Seite
     * @param <C>
     *      {@link Collection} oder Subklasse
     * @return
     *      neu zugewiesene {@link Collection} der entities von der n-Seite
     */
    protected <O extends Persistent, M extends Persistent, C extends Collection<M>>
            C setOneToMany(C newCollection, BiConsumer<M, O> setEntity, Function<O, C> getCollection) {

        C collection = getCollection.apply((O) this);

        // Soll überhaupt eine andere Collection zugewiesen werden?
        if (newCollection != collection) {
            // Gibt derzeit eine Collection von n-Entities?
            if (collection != null) {
                // entferne alle Entities der n-Seite
                // verhindere ConcurrentModificationException:
                new ArrayList<M>(collection).forEach(e -> setEntity.accept(e, null));
            }

            // Wurde eine neue Collection von n-Entities definiert?
            if (newCollection != null) {
                // füge alle neuen Entities hinzu, verhindere ConcurrentModificationException:
                List<M> copiedCollection = new ArrayList<>(newCollection);
                copiedCollection.forEach(e -> setEntity.accept(e, (O) this));
            }
        }

        return newCollection;
    }


    /**
     * Hilfsmethode für die n-Seite einer 1:n-Beziehung, die auch die 1-Seite aktualisiert.
     *
     * @param <O>
     *      Datentyp der 1-Seite
     * @param <M>
     *      Datentyp der n-Seite
     * @param <C>
     *      {@link Collection} oder Subklasse
     * @param newEntity
     *      neu zuzuweisende Entity der 1-Seite
     * @param getCollection
     *      get-{@linkplain Function Methode} der 1-Seite für die {@link Collection} von Entities der n-Seite
     * @param getEntity
     *      get-{@linkplain Function Methode} der n-Seite für die Entity der 1-Seite
     * @return
     *      neu zugewiesene Entity der 1-Seite
     */
    protected <O extends Persistent, M extends Persistent, C extends Collection<M>>
            O setManyToOne(O newEntity, Function<O, C> getCollection, Function<M, O> getEntity) {

        O entity = getEntity.apply((M) this);

        // Soll überhaupt eine andere Entity zugewiesen werden?
        if (newEntity != entity) {
            C collection;

            // Gibt es derzeit eine 1-Entity, und gibt es dort eine Collection von n-Entities?
            if ((entity != null) && ((collection = getCollection.apply(entity)) != null)) {
                // Diese Entity von der bisherigen 1-Entity entfernen
                collection.remove(this);
            }

            // Wurde eine neue 1-Entity angegeben, und besitzt diese eine Collection von n-Entities?
            if ((newEntity != null) && ((collection = getCollection.apply(newEntity)) != null)) {
                // Diese Entity zur neuen 1-Entity hinzufügen
                collection.add((M) this);
            }
        }

        return newEntity;
    }

}
