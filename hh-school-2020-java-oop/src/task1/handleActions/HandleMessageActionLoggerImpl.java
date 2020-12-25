package task1.handleActions;

import task1.utilsForTask.VacancyCreationMessage;

public class HandleMessageActionLoggerImpl implements HandleMessageAction{
    @Override
    public void handleMessage(VacancyCreationMessage message) {
        System.out.println("Logger Impl");
    }
}
