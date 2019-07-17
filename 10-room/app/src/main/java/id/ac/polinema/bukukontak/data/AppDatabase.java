package id.ac.polinema.bukukontak.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Contact.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    // method untuk mendapatkan dao
    // untuk access query ke database.
    public abstract ContactDAO contactDAO();
}
