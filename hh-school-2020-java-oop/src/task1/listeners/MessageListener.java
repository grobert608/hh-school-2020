package task1.listeners;

import task1.factory.ListenerFactory;
import task1.handleActions.HandleMessageAction;

public class MessageListener {

    private ListenerFactory listenerFactory;
    private String name;
    private Class messageClass;

    private MessageListener() {
        System.out.println("Create VacancyListener");

    }

    public class Subscriber {

        private Subscriber() {
        }

        public Subscriber setListenerFactory(ListenerFactory listenerFactory) {
            MessageListener.this.listenerFactory = listenerFactory;
            return this;

        }

        public Subscriber setName(String name) {
            MessageListener.this.name = name;
            return this;
        }

        public Subscriber setVacancyArchivationMessage(Class messageClass) {
            MessageListener.this.messageClass = messageClass;
            return this;
        }

        public Subscriber subscribeOnHandleMessageAction(HandleMessageAction handleMessageAction) {
            MessageListener.this.listenerFactory.subscribe(
                    MessageListener.this.name,
                    MessageListener.this.messageClass,
                    handleMessageAction
            );
            return this;
        }
    }

    public static Subscriber getSubscriber() {
        return new MessageListener().new Subscriber();
    }
}
