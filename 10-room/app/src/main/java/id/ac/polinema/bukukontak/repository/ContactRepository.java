package id.ac.polinema.bukukontak.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
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
    private LiveData<List<Contact>> contactList;

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

        getContactList();
    }

    private void saveContactToDb(Contact contact) {
        new SaveTask(obj -> database.contactDAO().addNew((Contact) obj))
                .execute(contact);
    }

    private void saveContactToServer(Contact contact) {
        Call<Contact> call = this.service.postContact(contact);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {

            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Log.e(TAG, "onFailure: ERROR");
            }
        });
    }

    /**
     * Mengambil data dari
     * API atau DB SQLite
     *
     * @return
     */
    public LiveData<List<Contact>> getContactList() {
        if (this.isOnline()) this.getContactListFromWeb();

        this.getContactListFromDb();
        return this.contactList;
    }

    /**
     * Mengambil data dari
     * Database Local SQLite.
     */
    private void getContactListFromDb() {
        // Ambil dao dari database
        ContactDAO dao = this.database.contactDAO();

        // Mengambil data dari DAO
        this.contactList = dao.findAll();
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
                    ContactDAO dao = database.contactDAO();

                    // save kontak
                    Contact[] contacts = new Contact[response.body().size()];
                    for (int i = 0; i < contacts.length; i++) {
                        contacts[i] = response.body().get(i);
                    }

                    // delete semua data di database
                    new DeleteTask(() -> dao.deleteAll()).execute();

                    // Tambahkan semua data ke sqlite
                    new SaveTask(obj -> database.contactDAO().addNew(obj))
                            .execute(contacts);
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+ t.getMessage());
                }
            };

    private boolean isOnline() {
        // TODO : KEMBALIKAN KE true
        return true;
    }
}
