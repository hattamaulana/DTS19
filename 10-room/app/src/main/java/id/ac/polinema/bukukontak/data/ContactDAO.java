package id.ac.polinema.bukukontak.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO
{
    /**
     * Query untuk menambahkan data
     * ke dalam database.
     *
     * @param contact
     */
    @Insert
    void addNew(Contact contact);

    /**
     * Mengambil data semua data contact.
     *
     * @return
     */
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> findAll();


    @Query("DELETE FROM contact")
    void deleteAll();
}
