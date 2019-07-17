package id.ac.polinema.bukukontak.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.AppDBProvider;
import id.ac.polinema.bukukontak.data.AppDatabase;
import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.data.ContactDAO;
import id.ac.polinema.bukukontak.remotedata.AppServiceProvider;
import id.ac.polinema.bukukontak.remotedata.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {
    private String TAG =
            ContactRepository.class.getName();

    // Properti Instance database.
    private AppDatabase database;
    private ContactService service;

    // Properti untuk menyimpan data untuk viewModle.
    private MutableLiveData<List<Contact>> contactList =
            new MutableLiveData<>();

    /**
     * Constructor
     *
     * @param context
     */
    public ContactRepository(Context context) {
        this.database = AppDBProvider.getInstanceDatabase(context);
        this.service = AppServiceProvider.getInstance();
    }

    /**
     * Menyimpan Kontak
     *
     * @param contact
     */
    public void saveContact(Contact contact) {
        if (isOnline()) {
            this.saveContactToServer(contact);
        } else {
            this.saveContactToDb(contact);
        }
    }

    /**
     * Mengambil data dari
     * API atau DB SQLite
     *
     * @return
     */
    public synchronized LiveData<List<Contact>> getContactList() {
        if (this.isOnline()) {
            this.getContactListFromWeb();
        } else {
            this.getContactListFromDb();
        }

        return this.contactList;
    }

    private boolean isOnline() {
        return true;
    }

    private void saveContactToServer(Contact contact) {
    }

    private void saveContactToDb(Contact contact) {
        new SaveTask().execute(contact);
    }

    /**
     * Mengambil data dari
     * Database Local SQLite.
     */
    private void getContactListFromDb() {
        // Ambil dao dari database
        ContactDAO dao = this.database.contactDAO();

        // Mengambil data dari DAO
        contactList.setValue(dao.findAll().getValue());
    }

    /**
     * Mengambil data dari
     * Database Web Server atau API.
     */
    private void getContactListFromWeb() {
        service.getContacts()
                .enqueue(callback);
    }

    private Callback<List<Contact>> callback =
            new Callback<List<Contact>>() {
                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                    contactList.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+ t.getMessage());
                }
            };

    private class SaveTask extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            database.contactDAO().addNew(contacts[0]);
            return null;
        }
    }
}
