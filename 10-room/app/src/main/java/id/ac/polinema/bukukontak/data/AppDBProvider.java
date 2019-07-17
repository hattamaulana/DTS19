package id.ac.polinema.bukukontak.data;

import android.content.Context;

import androidx.room.Room;

import retrofit2.Retrofit;

public class AppDBProvider
{
    private static AppDatabase instance;

    public static AppDatabase getInstanceDatabase(Context context)
    {
        if (AppDBProvider.instance == null)
        {
            AppDBProvider.instance = Room
                    .databaseBuilder(context, AppDatabase.class, "app_database.db")
                    .build();
        }

        return AppDBProvider.instance;
    }
}
