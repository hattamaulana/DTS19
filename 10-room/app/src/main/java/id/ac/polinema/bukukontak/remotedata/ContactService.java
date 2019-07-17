package id.ac.polinema.bukukontak.remotedata;

import java.util.List;

import id.ac.polinema.bukukontak.data.Contact;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ContactService {

    @GET("/bukukontak_service/api.php/contacts")
    Call<List<Contact>> getContacts();

    @POST("/bukukontak_service/api.php/contacts")
    Call<Contact> postContact(Contact contact);

}
