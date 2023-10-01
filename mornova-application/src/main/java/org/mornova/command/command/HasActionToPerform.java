package org.mornova.command.command;

public interface HasActionToPerform {
    enum Action{
        ADD,UPDATE,DELETE
    }
    Action getActionType();
    void setActionType(Action action);
}
