package ubb.ro.templatetry1.data.component;

import dagger.Component;
import ubb.ro.templatetry1.data.CustomScope;
import ubb.ro.templatetry1.data.module.MainScreenModule;
import ubb.ro.templatetry1.screens.main.MainActivity;

/**
 * Created by calin on 16.01.2017.
 */

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
