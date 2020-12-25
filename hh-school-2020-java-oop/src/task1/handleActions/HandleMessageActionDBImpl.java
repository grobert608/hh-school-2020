package task1.handleActions;

import task1.utilsForTask.VacancyCreationMessage;

public class HandleMessageActionDBImpl implements HandleMessageAction {
    @Override
    public void handleMessage(VacancyCreationMessage message) {
        System.out.println("DB Impl");
    }
}
