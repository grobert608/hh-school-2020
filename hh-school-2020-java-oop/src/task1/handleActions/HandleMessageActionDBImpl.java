package task1.handleActions;

import task1.messages.Message;

public class HandleMessageActionDBImpl implements HandleMessageAction {

    @Override
    public void handleMessage(Message message) {
        System.out.println("DB Impl");
    }
}
