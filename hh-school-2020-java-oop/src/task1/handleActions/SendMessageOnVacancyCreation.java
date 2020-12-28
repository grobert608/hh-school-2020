package task1.handleActions;

import task1.messages.Message;

public class SendMessageOnVacancyCreation implements HandleMessageAction{

    @Override
    public void handleMessage(Message message) {
        System.out.println("Send Message On Vacancy Creation");
    }
}
