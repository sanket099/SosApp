package com.sanket.safewe;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = EmergencyContacts.class,version = 1,exportSchema = false) //add as array if multiple
public abstract class ContactDB extends RoomDatabase {
    private static ContactDB instance; //only one interface

    public abstract ContactDAO noteDao();

    public static synchronized ContactDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDB.class , "contactDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    } //synchronized : only one thread can access this method
    //fallbacktodestructivemigration : to handle versions

    private static Callback roomCallBack = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

           // new PopulateDb(instance).execute();
        }
    };

  /*  private static class  PopulateDb extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private PopulateDb(ContactDB db){
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Note... notes) {
           // noteDao.Insert(new Note("title","description" , 1,"date","time",true));

            return null;
        }
    }
*/
}
