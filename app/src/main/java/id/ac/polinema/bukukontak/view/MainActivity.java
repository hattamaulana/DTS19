package id.ac.polinema.bukukontak.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import id.ac.polinema.bukukontak.R;
import id.ac.polinema.bukukontak.adapter.RecyclerContactListAdapter;
import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.view.model.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    // ViewModel
    private MainViewModel model;

    // Data
    private RecyclerContactListAdapter recyclerContactListAdapter;
    private LiveData<List<Contact>> contactList;

    // Components
    private RecyclerView recyclerContactList;
    private EditText edtName;
    private EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mendapatkan instance ViewModel
        this.model = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        this.initData();
        this.initComponents();
    }

    private void initData()
    {
        // Seting recycler view-nya
        this.recyclerContactListAdapter = new RecyclerContactListAdapter(this);
    }

    private void initComponents()
    {
        this.recyclerContactList = this.findViewById(R.id.recycler_contact_list);
        this.recyclerContactList.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerContactList.setAdapter(this.recyclerContactListAdapter);

        this.edtName = this.findViewById(R.id.edt_name);
        this.edtPhone = this.findViewById(R.id.edt_phone);


        // Mengambil data dari viewmodel.
        this.model.getContactList()
                .observe(this, observer);
    }

    private Observer<List<Contact>> observer =
            new Observer<List<Contact>>() {
                @Override
                public void onChanged(List<Contact> contacts) {
                    recyclerContactListAdapter.setContactList(contacts);
                }
            };

    public void onBtnSave_Click(View view)
    {
        Log.i(MainActivity.class.getName(), "onBtnSave_Click: OK");
        Contact newContact = this.makeContact();

        this.model.saveContact(newContact);
    }

    private Contact makeContact()
    {
        String name = this.edtName.getText().toString();
        String phoneNumber = this.edtPhone.getText().toString();

        Contact c = new Contact(name, phoneNumber);

        return c;
    }
}
