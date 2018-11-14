package com.example.consultants.week4_daily2.model.data.remote;

import android.app.Person;
import android.util.Log;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;
import com.example.consultants.week4_daily2.model.data.PersonCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RemoteObserver implements Observer<PersonProfile> {
    public static final String TAG = RemoteObserver.class.getSimpleName() + "_TAG";

    PersonCallback callback;

    public RemoteObserver(PersonCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: " + callback.toString());
    }

    @Override
    public void onNext(PersonProfile personProfile) {
        Log.d(TAG, "onNext: " + personProfile.getId());
        //TODO -> do i need to create personResponse for result
        callback.onSuccess(personProfile);

    }

    @Override
    public void onError(Throwable e) {
        callback.onFailure(e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }
}
