package ubb.ro.templatetry1.service;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;
import ubb.ro.templatetry1.model.Event;
import ubb.ro.templatetry1.model.Post;

/**
 * Created by calin on 16.01.2017.
 */

public interface EventService {

    @GET("event")
    Observable<List<Event>> getEvents(@Header("If-Modified-Since") String ifModifiedSince);


    @GET("event")
    Observable<List<Event>> getEvents();

    @POST("event")
    Observable<Event> createEvent(@Body Event event);
}
