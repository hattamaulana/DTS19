package polinema.ac.id.dtsapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DTSRoomDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
}
