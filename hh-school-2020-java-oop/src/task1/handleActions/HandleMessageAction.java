package task1.handleActions;

import task1.utilsForTask.VacancyCreationMessage;

@FunctionalInterface
public interface HandleMessageAction {

    public void handleMessage(VacancyCreationMessage message);
}

