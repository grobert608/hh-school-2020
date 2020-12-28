package task1;

import task1.actionProcessing.HandleMessageActionDBImpl;
import task1.actionProcessing.HandleMessageActionLoggerImpl;
import task1.handleActions.SendMessageOnVacancyCreation;
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
                .addActionProcessing(new HandleMessageActionLoggerImpl())
                .addActionProcessing(new HandleMessageActionDBImpl())
                .subscribeOnHandleMessageAction(new SendMessageOnVacancyCreation());

        MessageListener.getSubscriber().setListenerFactory(listenerFactory)
                .setName("vacancy_archivation")
                .setVacancyArchivationMessage(VacancyArchivationMessage.class)
                .addActionProcessing(new HandleMessageActionLoggerImpl())
                .subscribeOnHandleMessageAction(new SendMessageOnVacancyCreation());
    }
}
