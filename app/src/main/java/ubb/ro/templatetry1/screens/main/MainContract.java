package ubb.ro.templatetry1.screens.main;

import java.util.List;

import ubb.ro.templatetry1.model.Event;
import ubb.ro.templatetry1.model.Post;

/**
 * Created by calin on 13.01.2017.
 */

public interface MainContract {
    interface View {
        void showEvents(List<Event> events);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadEvents();
    }

}
