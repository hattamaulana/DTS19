package id.ac.polinema.bukukontak.remotedata;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppServiceProvider
{

    private static ContactService instance;

    private static final String BASE_URI = "http://192.168.65.1";

    public static ContactService getInstance() {
        if (instance == null) {
            // Siapkan Logging untuk HTTP Client
            HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Buat HTTP Client, nantinya untuk di-set-kan ke Retrofit Builder
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if (!httpClient.interceptors().contains(httpLogging))
                httpClient.addInterceptor(httpLogging);

            // Buat dulu Retrofit Builder
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(AppServiceProvider.BASE_URI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build());

            // Dari Builder baru bisa didapatkan instance Retrofit
            Retrofit retrofit = builder.build();
            instance = retrofit.create(ContactService.class);
        }

        return instance;
    }
}
