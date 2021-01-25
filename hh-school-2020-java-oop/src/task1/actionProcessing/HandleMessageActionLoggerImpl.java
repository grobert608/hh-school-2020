package task1.actionProcessing;

import task1.handleActions.HandleMessageAction;

public class HandleMessageActionLoggerImpl implements ActionProcessing {
    @Override
    public HandleMessageAction doForHandleMessageAction(HandleMessageAction action) {
        System.out.println("Logger");
        // customize
        return action;
    }
}
