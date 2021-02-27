package com.sanket.safewe;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class ContactViewModel extends AndroidViewModel {  // android view model not view model

    // difference : android vm : passed application , you should never store context in the view mmodel ;
    // results in memory leak!

    private ContactRepo repository ;
    private LiveData<List<EmergencyContacts>> allEmergencyContactss;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepo(application);
        allEmergencyContactss = repository.getAllEmergencyContactss();
    }



    public void insert(EmergencyContacts note) {
        repository.insert(note);
    }
    public void update(EmergencyContacts note) {
        repository.update(note);
    }
    public void delete(EmergencyContacts note) {
        repository.delete(note);
    }
    public void deleteAllEmergencyContactss() {
        repository.deleteAllEmergencyContactss();
    }
    public LiveData<List<EmergencyContacts>> getAllEmergencyContactss() {
        return allEmergencyContactss;
    }
}





