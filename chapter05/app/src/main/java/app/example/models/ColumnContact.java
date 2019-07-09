package app.example.models;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ColumnContact implements MultiItemEntity {
    public static final int COLUMN_LIST = 1;
    public static final int COLUMN_GRID = 2;

    private int type;
    private Contact contact;

    public ColumnContact(int type, Contact contact) {
        this.type = type;
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
