package task1.handleActions;

import task1.messages.Message;

@FunctionalInterface
public interface HandleMessageAction {

    public void handleMessage(Message message);
}

