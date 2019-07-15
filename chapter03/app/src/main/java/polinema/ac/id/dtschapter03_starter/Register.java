package polinema.ac.id.dtschapter03_starter;

import android.os.Parcel;
import android.os.Parcelable;

public class Register implements Parcelable {
    private String nama;
    private String tanggalLahir;
    private String username;
    private String password;
    private String jenisKelamin;

    public Register(String nama, String tanggalLahir, String username, String jenisKelamin) {
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.username = username;
        this.password = password;
        this.jenisKelamin = jenisKelamin;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.tanggalLahir);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.jenisKelamin);
    }

    protected Register(Parcel in) {
        this.nama = in.readString();
        this.tanggalLahir = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.jenisKelamin = in.readString();
    }

    public static final Parcelable.Creator<Register> CREATOR = new Parcelable.Creator<Register>() {
        @Override
        public Register createFromParcel(Parcel source) {
            return new Register(source);
        }

        @Override
        public Register[] newArray(int size) {
            return new Register[size];
        }
    };
}
