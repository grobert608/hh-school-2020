package task1;

import task1.handleActions.HandleMessageActionLoggerImpl;
import task1.listeners.VacancyListener;
import task1.utilsForTask.ListenerFactory;
import task1.utilsForTask.VacancyArchivationMessage;

public class Main {
    public static void main(String[] args) {
        ListenerFactory listenerFactory = new ListenerFactory();
        VacancyArchivationMessage vacancyArchivationMessage = new VacancyArchivationMessage();

        VacancyListener.getBuilder().setListenerFactory(listenerFactory)
                .setName("vacancy_creation")
                .setVacancyArchivationMessage(vacancyArchivationMessage)
                .setHandleMessageAction(new HandleMessageActionLoggerImpl())
                .subscribe();
    }
}
