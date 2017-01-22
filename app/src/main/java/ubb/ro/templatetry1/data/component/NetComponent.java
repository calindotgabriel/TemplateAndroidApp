package ubb.ro.templatetry1.data.component;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import ubb.ro.templatetry1.data.module.AppModule;
import ubb.ro.templatetry1.data.module.NetModule;

/**
 * Created by calin on 13.01.2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
}
