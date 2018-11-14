package com.example.consultants.week4_daily2.ui.user;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;
import com.example.consultants.week4_daily2.ui.base.BasePresenter;
import com.example.consultants.week4_daily2.ui.base.BaseView;

import java.util.List;

public interface UserContract {

    interface View extends BaseView {

        //will display the information
        void onUserProfile(PersonProfile users);
    }

    interface Presenter extends BasePresenter<View> {
        void getUserProfile(String login);

        //not getting anything from the activity to be used
//        void getUserProfile();
    }

}
