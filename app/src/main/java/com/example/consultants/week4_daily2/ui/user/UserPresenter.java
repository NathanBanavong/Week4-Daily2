package com.example.consultants.week4_daily2.ui.user;

import android.util.Log;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;
import com.example.consultants.week4_daily2.model.data.PersonCallback;
import com.example.consultants.week4_daily2.model.data.PersonRepository;
import com.example.consultants.week4_daily2.model.data.remote.RemoteDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements UserContract.Presenter {
public static final String TAG = UserPresenter.class.getSimpleName() + "_TAG";
    
    UserContract.View view;
    PersonRepository repository;
    RemoteDataSource remoteDataSource;

    public UserPresenter(PersonRepository repository) {
        Log.d(TAG, "UserPresenter: " + repository.toString());
        this.repository = repository;
    }


    @Override
    public void getUserProfile(String login) {
        Log.d(TAG, "getUserProfile: ");
        
        repository.getUserProfile(login, new PersonCallback() {
            @Override
            public void onSuccess(PersonProfile personProfile) {
                Log.d(TAG, "onSuccess: " + personProfile.getLogin());
                view.onUserProfile(personProfile);
            }

            @Override
            public void onFailure(String error) {
                Log.d(TAG, "onFailure: ");
                view.showError(error);
            }
        });
    }

    public void getProfileRepository(final String inputlogin) {
        Log.d(TAG, "getProfileRepository: " + inputlogin);
        remoteDataSource.getUserProfile(inputlogin).enqueue(new Callback<PersonProfile>() {
            @Override
            public void onResponse(Call<PersonProfile> call, Response<PersonProfile> response) {
                getUserProfile(inputlogin);
                Log.d(TAG, "onResponse: " + inputlogin);
//                Log.d(TAG, "onResponse: " + response.body().getId());
            }

            @Override
            public void onFailure(Call<PersonProfile> call, Throwable t) {
                Log.d(TAG, "onFailure: " + inputlogin);
            }

        });
    }


    @Override
    public void onAttach(UserContract.View view) {
        Log.d(TAG, "onAttach: ");
        this.view = view;
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        this.view = null;
    }

}
