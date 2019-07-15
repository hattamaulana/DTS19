package polinema.ac.id.dtsapp.data;

import android.arch.persistence.room.RoomDatabase;

public interface DBTaskEventListener {
    Object runDatabaseOperation(RoomDatabase roomDatabase, Object... obj);
    void onDatabaseOperationFinished(Object object);
}
