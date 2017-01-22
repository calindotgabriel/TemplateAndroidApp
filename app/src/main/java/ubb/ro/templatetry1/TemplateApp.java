package ubb.ro.templatetry1;

import android.app.Application;

import ubb.ro.templatetry1.data.component.DaggerNetComponent;
import ubb.ro.templatetry1.data.component.MainScreenComponent;
import ubb.ro.templatetry1.data.component.NetComponent;
import ubb.ro.templatetry1.data.module.AppModule;
import ubb.ro.templatetry1.data.module.ApiModule;

/**
 * Created by calin on 13.01.2017.
 */
public class TemplateApp extends Application {

    private NetComponent mNetComponent;
    private MainScreenComponent mMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("http://192.168.0.129:3000"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
