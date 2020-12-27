package task1.factory;

import task1.handleActions.HandleMessageAction;

public class ListenerFactory {
    public void subscribe(String name, Class messageClass, HandleMessageAction handleMessageAction) {
        System.out.println("Subscribe for " + name);
    }

    public ListenerFactory() {
        System.out.println("Create ListenerFactory");
    }
}
