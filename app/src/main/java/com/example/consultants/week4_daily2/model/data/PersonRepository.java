package com.example.consultants.week4_daily2.model.data;

import android.util.Log;

import com.example.consultants.week4_daily2.CacheManager;
import com.example.consultants.week4_daily2.model.data.remote.RemoteDataSource;
import com.example.consultants.week4_daily2.model.data.remote.RemoteObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonRepository {
    public static final String TAG = PersonRepository.class.getSimpleName() + "_TAG";

    RemoteDataSource remoteDataSource;

    public PersonRepository(RemoteDataSource remoteDataSource) {
        Log.d(TAG, "PersonRepository: " + remoteDataSource.toString());
        this.remoteDataSource = remoteDataSource;
    }

    //not sure how this applies
    public void getUserProfile(String login, final PersonCallback callback) {
        Log.d(TAG, "getUserProfile: " + login);

//        if cache is dirty make the network call
//        if (CacheManager.isCacheDirty()) {

            remoteDataSource.getProfileUserObs(login)
//                    make the network call on the worker thread
                    .subscribeOn(Schedulers.io())
//                    get the results back on the main thread
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RemoteObserver(callback));
//        }
    }

}
