package polinema.ac.id.dtsapp.data;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DBProvider {

    private static DTSRoomDatabase instance;
    private static DTSRoomDatabase asynchronousInstance;

    public static DTSRoomDatabase getInstance(Context context) {
        if(DBProvider.instance == null) {
            DBProvider.instance = Room
                    .databaseBuilder(context, DTSRoomDatabase.class, "dtsapp.db")
                    .allowMainThreadQueries().build();
        }

        return DBProvider.instance;
    }

    public static DTSRoomDatabase getAsynchronousInstance(Context context) {
        if(DBProvider.asynchronousInstance == null) {
            DBProvider.asynchronousInstance = Room.databaseBuilder(
                    context, DTSRoomDatabase.class, "dtsapp.db").build();
        }

        return DBProvider.asynchronousInstance;
    }
}
