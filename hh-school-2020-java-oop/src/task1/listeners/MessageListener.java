package task1.listeners;

import task1.actionProcessing.ActionProcessing;
import task1.factory.ListenerFactory;
import task1.handleActions.HandleMessageAction;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MessageListener {

    private ListenerFactory listenerFactory;
    private String name;
    private Class messageClass;
    private Set<ActionProcessing> actionProcessings = new HashSet<>();

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

        public Subscriber addActionProcessing(ActionProcessing actionProcessing) {
            MessageListener.this.actionProcessings.add(actionProcessing);
            return this;
        }

        public Subscriber removeActionProcessing(ActionProcessing actionProcessing) {
            MessageListener.this.actionProcessings.remove(actionProcessing);
            return this;
        }

        public Subscriber subscribeOnHandleMessageAction(HandleMessageAction handleMessageAction) {
            Iterator<ActionProcessing> processingIterator = actionProcessings.iterator();
            HandleMessageAction messageAction = handleMessageAction;
            while (processingIterator.hasNext()){
                messageAction = processingIterator.next().doForHandleMessageAction(messageAction);
            }
            MessageListener.this.listenerFactory.subscribe(
                    MessageListener.this.name,
                    MessageListener.this.messageClass,
                    messageAction
            );
            return this;
        }
    }

    public static Subscriber getSubscriber() {
        return new MessageListener().new Subscriber();
    }
}
