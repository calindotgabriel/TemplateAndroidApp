package ubb.ro.templatetry1.screens.main;

import android.util.Log;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ubb.ro.templatetry1.service.EventService;
import ubb.ro.templatetry1.model.Event;

/**
 * Created by calin on 16.01.2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    Retrofit retrofit;
    MainContract.View mView;

    @Inject
    public MainPresenter(Retrofit retrofit, MainContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void loadEvents() {

        final EventService eventService = retrofit.create(EventService.class);
        final Observable<List<Event>> events = eventService.getEvents();

        events.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Event>>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            final int code = ((HttpException) e).code();
                            if (code == 304) {
                                Log.d(TAG, "Events were not modified");
                            }
                        } else {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(List<Event> events) {
                        mView.showEvents(events);
                    }
                });
    }

}
