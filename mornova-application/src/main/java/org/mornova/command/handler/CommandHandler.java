package org.mornova.command.handler;

import org.mornova.command.command.Command;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
public interface CommandHandler<T extends Command> {
    /*
    HandlingContext a été conçue pour fournir un mécanisme simple permettant aux
    CommandHandler de définir des actions à exécuter en cas de succès lorsque la command
    aura été exécuté avec succès par  tout les autres handlers. Donc c'est la même instance qui
    doit être partagé par tout les handlers d'une même command.
    * */
    class HandlingContext{
        private final List<Runnable> onSuccessActions = new ArrayList<>();

        public void doOnSuccess(Runnable runnable){
            this.onSuccessActions.add(runnable);
        }

        public Iterable<Runnable> getOnSuccessActions(){
            return this.onSuccessActions;
        }
    }


    void handle(T command, HandlingContext handlingContext);
}
