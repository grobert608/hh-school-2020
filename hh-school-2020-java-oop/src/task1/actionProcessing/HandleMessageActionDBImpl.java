package task1.actionProcessing;

import task1.handleActions.HandleMessageAction;

public class HandleMessageActionDBImpl implements ActionProcessing {
    @Override
    public HandleMessageAction doForHandleMessageAction(HandleMessageAction action) {
        System.out.println("DB");
        // customize
        return action;
    }
}