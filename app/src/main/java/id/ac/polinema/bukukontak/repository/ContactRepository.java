package id.ac.polinema.bukukontak.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.AppDBProvider;
import id.ac.polinema.bukukontak.data.AppDatabase;
import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.data.ContactDAO;

public class ContactRepository
{
    private String TAG =
            ContactRepository.class.getName();

    // Properti Instance database.
    private AppDatabase database;

    // Properti untuk menyimpan data untuk viewModle.
    private LiveData<List<Contact>> contactList;

    public ContactRepository(Context context) {
        this.database = AppDBProvider.getInstanceDatabase(context);
    }

    public void saveContact(Contact contact)
    {
        if (isOnline())
        {
            this.saveContactToServer(contact);
        }
        else
        {
            this.saveContactToDb(contact);
        }
    }

    private void saveContactToServer(Contact contact)
    {
    }

    private void saveContactToDb(Contact contact)
    {
        Log.i(TAG, "saveContactToDb: ");
        new SaveTask()
                .execute(contact);
    }

    public LiveData<List<Contact>> getContactList()
    {
        if (this.isOnline())
        {
            this.getContactListFromWeb();
        }
        else
        {
            this.getContactListFromDb();
        }

        return this.contactList;
    }

    private boolean isOnline()
    {
        return false;
    }

    /**
     * Mengambil data dari
     * Database Local SQLite.
     */
    private void getContactListFromDb()
    {
        // Ambil dao dari database
        ContactDAO dao = this.database.contactDAO();

        // Mengambil data dari DAO
        this.contactList = dao.findAll();
    }

    /**
     * Mengambil data dari
     * Database Web Server atau API.
     */
    private void getContactListFromWeb()
    {

    }

    private class SaveTask extends AsyncTask<Contact, Void, Void> {

        @Override
        protected Void doInBackground(Contact... contacts) {
            database.contactDAO().addNew(contacts[0]);

            return null;
        }

    }
}
