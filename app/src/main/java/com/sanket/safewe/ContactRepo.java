package com.sanket.safewe;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepo {

    private ContactDAO noteDao;
    private LiveData<List<EmergencyContacts>> allEmergencyContactss;

    public ContactRepo(Application application) { //application is subclass of context
        ContactDB database = ContactDB.getInstance(application);
        noteDao = database.noteDao();
        allEmergencyContactss = noteDao.getAllContacts(); //cant call abstract func but since instance is there we can do this
    }

    //methods for database operations :-

    // these methods are api that the repo exposes to the outside
    public void insert(EmergencyContacts note) {
        new InsertEmergencyContactsAsyncTask(noteDao).execute(note);
    }
    public void update(EmergencyContacts note) {
        new UpdateEmergencyContactsAsyncTask(noteDao).execute(note);
    }
    public void delete(EmergencyContacts note) {
        new DeleteEmergencyContactsAsyncTask(noteDao).execute(note);
    }
    public void deleteAllEmergencyContactss() {
        new DeleteAllEmergencyContactssAsyncTask(noteDao).execute();
    }
    public LiveData<List<EmergencyContacts>> getAllEmergencyContactss() {
        return allEmergencyContactss;
    }

    //room doesnt allow db op in main thread so we'll do this in background by async tasks
    private static class InsertEmergencyContactsAsyncTask extends AsyncTask<EmergencyContacts, Void, Void> { //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private ContactDAO noteDao;
        private InsertEmergencyContactsAsyncTask(ContactDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(EmergencyContacts... notes) { // ...  is similar to array
            noteDao.Insert(notes[0]); //single note
            return null;
        }
    }
    private static class UpdateEmergencyContactsAsyncTask extends AsyncTask<EmergencyContacts, Void, Void> {
        private ContactDAO noteDao;
        private UpdateEmergencyContactsAsyncTask(ContactDAO noteDao) { //constructor as the class is static
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(EmergencyContacts... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }
    private static class DeleteEmergencyContactsAsyncTask extends AsyncTask<EmergencyContacts, Void, Void> {
        private ContactDAO noteDao;
        private DeleteEmergencyContactsAsyncTask(ContactDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(EmergencyContacts... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllEmergencyContactssAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDAO noteDao;
        private DeleteAllEmergencyContactssAsyncTask(ContactDAO noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteAllContacts();
            return null;
        }
    }
}



