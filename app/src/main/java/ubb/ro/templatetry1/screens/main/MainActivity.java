package ubb.ro.templatetry1.screens.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ubb.ro.templatetry1.EventService;
import ubb.ro.templatetry1.TemplateApp;
import ubb.ro.templatetry1.data.component.DaggerMainScreenComponent;
import ubb.ro.templatetry1.data.module.MainScreenModule;
import ubb.ro.templatetry1.model.Event;
import ubb.ro.templatetry1.model.Post;
import ubb.ro.templatetry1.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    MainScreenPresenter mMainPresneter;

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject this component
        final TemplateApp app = (TemplateApp) getApplication();
        DaggerMainScreenComponent.builder()
                .netComponent(app.getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);


        listView = (ListView) findViewById(R.id.lv_main);
        list = new ArrayList<>();

        mMainPresneter.loadEvents();
    }


    @Override
    public void showEvents(List<Event> events) {
        for (Event e : events) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void showComplete() {
        Log.d(TAG, "Completed lading events");
    }
}
