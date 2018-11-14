package com.example.consultants.week4_daily2.model.data.remote;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {

    @GET("/users/{user}")
    Call<PersonProfile> getUserProfile(@Path("user") String login);

    //    using the rxjava observable
    @GET("/users/{user}")
    Observable<PersonProfile> getRandomUserObs(@Path("user") String login);

}
