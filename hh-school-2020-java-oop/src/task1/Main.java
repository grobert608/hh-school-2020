package task1;

import task1.handleActions.HandleMessageActionDBImpl;
import task1.handleActions.HandleMessageActionLoggerImpl;
import task1.listeners.MessageListener;
import task1.factory.ListenerFactory;
import task1.messages.VacancyArchivationMessage;
import task1.messages.VacancyCreationMessage;

public class Main {
    public static void main(String[] args) {
        ListenerFactory listenerFactory = new ListenerFactory();

        MessageListener.getSubscriber().setListenerFactory(listenerFactory)
                .setName("vacancy_creation")
                .setVacancyArchivationMessage(VacancyCreationMessage.class)
                .subscribeOnHandleMessageAction(new HandleMessageActionLoggerImpl())
                .subscribeOnHandleMessageAction(new HandleMessageActionDBImpl());

        MessageListener.getSubscriber().setListenerFactory(listenerFactory)
                .setName("vacancy_archivation")
                .setVacancyArchivationMessage(VacancyArchivationMessage.class)
                .subscribeOnHandleMessageAction(new HandleMessageActionLoggerImpl());
    }
}
