package ubb.ro.templatetry1.data.module;

import dagger.Module;
import dagger.Provides;
import ubb.ro.templatetry1.data.CustomScope;
import ubb.ro.templatetry1.screens.main.MainContract;

/**
 * Created by calin on 13.01.2017.
 */

@Module
public class MainScreenModule {
    private final MainContract.View mView;

    public MainScreenModule(MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainContract.View providesMainScreenContractView() {
        return mView;
    }
}
