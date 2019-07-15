package polinema.ac.id.dtsapp.data;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class DBTask extends AsyncTask<Object, Void, Void> {

    // Room database butuh Context
    // Dimasukkan WeakReference<> untuk Mencegah kebocoran memory
    private WeakReference<Context> databaseContext;

    // Activity yang ingin menjalankan operasi
    // database di background secara asynchronous
    private DBTaskEventListener eventListener;

    // Hasil dari operasi yang selesai dilakukan
    private Object operationResult;

    public DBTask(Context databaseContext, DBTaskEventListener eventListener) {
        this.databaseContext = new WeakReference<>(databaseContext);
        this.eventListener = eventListener;
    }

    @Override
    protected Void doInBackground(Object... objects) {
        RoomDatabase database = (DTSRoomDatabase) DBProvider
                .getAsynchronousInstance(this.databaseContext.get());

        // Menjalankan fungsi callback
        eventListener.runDatabaseOperation(database, objects);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // Menjalankan fungsi callback
        eventListener.onDatabaseOperationFinished(null);
    }
}
