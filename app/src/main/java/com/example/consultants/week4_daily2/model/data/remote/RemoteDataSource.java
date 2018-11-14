package com.example.consultants.week4_daily2.model.data.remote;

import android.util.Log;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;
import com.example.consultants.week4_daily2.utils.NetworkHelper;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    public static final String TAG = RemoteDataSource.class.getSimpleName() + "_TAG";
    
    //TODO the base url from the NetworkHelper(utils)
    //see the Remote Service that clarifies the input for profile, 'NathanBanavong'

    //instance of retrofit created -> calls base class
    private Retrofit createInstance(){
        Log.d(TAG, "createInstance: ");
        
        return new Retrofit.Builder()
                .baseUrl(NetworkHelper.BASE_GIT_URL)
//                use for converting the response using Gson
                .addConverterFactory(GsonConverterFactory.create())
                //using rxjava adapter
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    private  RemoteService getRemoteService() {
        Log.d(TAG, "getRemoteService: " + Thread.currentThread().getName());
        return createInstance().create(RemoteService.class);
    }

    //TODO need for a ProfileResponse? -> get the info? similar to user to randomresponse?
    public Call<PersonProfile> getUserProfile(String login) {
        Log.d(TAG, "getUserProfile: " + login);
        Retrofit retrofit = createInstance();
        RemoteService service = retrofit.create(RemoteService.class);
        return getRemoteService().getUserProfile(login);
    }

    //using rxjava
    public Observable<PersonProfile> getProfileUserObs(String login) {
        Log.d(TAG, "getProfileUserObs: " + login);
        return getRemoteService().getRandomUserObs(login);
    }


}
