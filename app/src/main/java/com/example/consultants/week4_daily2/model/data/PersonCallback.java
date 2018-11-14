package com.example.consultants.week4_daily2.model.data;

import com.example.consultants.week4_daily2.model.PersonProfile.PersonProfile;

public interface PersonCallback {

    void onSuccess(PersonProfile personProfile);

    void onFailure(String error);

}
