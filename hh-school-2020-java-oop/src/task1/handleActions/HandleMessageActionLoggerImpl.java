package task1.handleActions;

import task1.messages.Message;

public class HandleMessageActionLoggerImpl implements HandleMessageAction{

    @Override
    public void handleMessage(Message message) {
        System.out.println("Logger Impl");
    }
}
