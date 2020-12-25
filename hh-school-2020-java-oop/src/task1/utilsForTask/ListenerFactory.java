package task1.utilsForTask;

import task1.handleActions.HandleMessageAction;

public class ListenerFactory {
    public void subscribe(String name, VacancyArchivationMessage vacancyArchivationMessage, HandleMessageAction handleMessageAction){
        System.out.println("Subscribe!");
    }

    public ListenerFactory() {
        System.out.println("Create ListenerFactory");
    }
}
