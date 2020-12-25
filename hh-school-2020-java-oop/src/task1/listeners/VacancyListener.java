package task1.listeners;

import task1.handleActions.HandleMessageAction;
import task1.utilsForTask.ListenerFactory;
import task1.utilsForTask.VacancyArchivationMessage;

public class VacancyListener{

    private ListenerFactory listenerFactory;
    private String name;
    private VacancyArchivationMessage vacancyArchivationMessage;
    private HandleMessageAction handleMessageAction;

    private VacancyListener() {
        System.out.println("Create VacancyListener");

    }

    public class Builder {

        private Builder() {
        }

        public Builder setListenerFactory(ListenerFactory listenerFactory) {
            VacancyListener.this.listenerFactory = listenerFactory;
            return this;

        }

        public Builder setName(String name) {
            VacancyListener.this.name = name;
            return this;
        }

        public Builder setVacancyArchivationMessage(VacancyArchivationMessage vacancyArchivationMessage) {
            VacancyListener.this.vacancyArchivationMessage = vacancyArchivationMessage;
            return this;
        }

        public Builder setHandleMessageAction(HandleMessageAction handleMessageAction) {
            VacancyListener.this.handleMessageAction = handleMessageAction;
            return this;
        }

        public void subscribe() {
            VacancyListener.this.listenerFactory.subscribe(
                    VacancyListener.this.name,
                    VacancyListener.this.vacancyArchivationMessage,
                    VacancyListener.this.handleMessageAction
            );
        }
    }

    public static Builder getBuilder() {
        return new VacancyListener().new Builder();
    }
}
