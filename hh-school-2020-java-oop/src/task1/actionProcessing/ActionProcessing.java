package task1.actionProcessing;

import task1.handleActions.HandleMessageAction;

public interface ActionProcessing {

    public HandleMessageAction doForHandleMessageAction(HandleMessageAction action);
}
