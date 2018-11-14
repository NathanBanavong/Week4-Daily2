package com.example.consultants.week4_daily2.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.consultants.week4_daily2.R;
import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;
import com.example.consultants.week4_daily2.model.data.PersonCallback;
import com.example.consultants.week4_daily2.model.data.PersonRepository;
import com.example.consultants.week4_daily2.model.data.remote.RemoteDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserContract.View {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private RemoteDataSource remoteDataSource;
    private UserPresenter presenter;
    private EditText etLoginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBind();
    }

    public void onBind() {
        remoteDataSource = new RemoteDataSource();
        etLoginName = findViewById(R.id.etLoginName);
        presenter = new UserPresenter(new PersonRepository(remoteDataSource));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + Thread.currentThread().getName());
        presenter.onAttach(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + Thread.currentThread().getName());
        presenter.onDetach();
    }

    public void onShowProfile(View view) {
        Log.d(TAG, "onShowProfile: ");

        presenter.getProfileRepository(etLoginName.toString());

//passed in text to search from the edittext
//        remoteDataSource.getUserProfile(etLoginName.toString()).enqueue(new Callback<PersonProfile>() {
//            @Override
//            public void onResponse(Call<PersonProfile> call, Response<PersonProfile> response) {
//                presenter.getUserProfile(etLoginName.toString());
//                Log.d(TAG, "onResponse: " + Thread.currentThread().getName());
////                Log.d(TAG, "onResponse: " + response.body().getId());
//            }
//
//            @Override
//            public void onFailure(Call<PersonProfile> call, Throwable t) {
//
//            }
//
//        });
    }

    @Override
    public void onUserProfile(PersonProfile users) {
        Log.d(TAG, "onUserProfile: " + users.getLogin());
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: " + error);
    }

//    public void getUserProfile(View view) {
//
//        presenter.getUserProfile(.getText().toString(),
//                Integer.parseInt(etResults.getText().toString()));
//    }
}
