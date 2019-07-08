package app.example.models;

public class Contact {
    private String name;
    private String phone;
    private String imageUri;

    public Contact(String name, String phone, String imageUri) {
        this.name = name;
        this.phone = phone;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
