package id.go.kominfo.dts.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String img;
    private String title;
    private String writer;
    private int stars;
    private String description;

    public Book(String img, String title, String writer, int stars, String description) {
        this.img = img;
        this.title = title;
        this.writer = writer;
        this.stars = stars;
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        dest.writeInt(this.stars);
        dest.writeString(this.description);
    }

    protected Book(Parcel in) {
        this.img = in.readString();
        this.title = in.readString();
        this.writer = in.readString();
        this.stars = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
