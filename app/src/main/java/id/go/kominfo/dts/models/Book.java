package id.go.kominfo.dts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String img;
    private String title;
    private String writer;
    private int starts;

    public Book(String img, String title, String writer, int starts) {
        this.img = img;
        this.title = title;
        this.writer = writer;
        this.starts = starts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.title);
        dest.writeString(this.writer);
        dest.writeInt(this.starts);
    }

    protected Book(Parcel in) {
        this.img = in.readString();
        this.title = in.readString();
        this.writer = in.readString();
        this.starts = in.readInt();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getStars() {
        return starts;
    }
}
