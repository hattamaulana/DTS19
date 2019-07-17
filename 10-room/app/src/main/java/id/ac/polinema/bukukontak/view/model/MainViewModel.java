package id.ac.polinema.bukukontak.view.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.repository.ContactRepository;
import id.ac.polinema.bukukontak.view.MainActivity;

public class MainViewModel extends AndroidViewModel {
    // Properti Repository
    private ContactRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContactRepository(application);
    }

    /**
     * Method untuk menyimpan Kontak
     *
     */
    public void saveContact(Contact contact) {
        // store data ke repo
        Log.i(MainViewModel.class.getName(), "saveContact: OK");
        this.repository.saveContact(contact);
    }


    /**
     * Method untuk menampilkan Kontak
     *
     * @return
     */
    public LiveData<List<Contact>> getContactList() {
        // Mengambil data dari repo.
        return this.repository.getContactList();
    }
}
