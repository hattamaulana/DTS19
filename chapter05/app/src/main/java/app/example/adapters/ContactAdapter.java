package app.example.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.example.R;
import app.example.models.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Holder> {

    private final String TAG = ContactAdapter.class.getName();

    private List<Contact> contacts;
    private OnContactClickListener listener;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public interface OnContactClickListener {
        void onClick(View view, int position);
    }

    public void setOnClickListener(OnContactClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.Holder holder, int position) {
        Contact item = contacts.get(position);

        holder.name.setText(item.getName());
        holder.phoneNumber.setText(item.getPhone());
        Picasso.get()
                .load(item.getImageUri())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);

        Log.i(TAG, "onBindViewHolder: "+ item.getImageUri());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView phoneNumber;

        Holder(@NonNull final View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imageContact);
            name = (TextView) itemView.findViewById(R.id.txtName);
            phoneNumber = (TextView) itemView.findViewById(R.id.txtPhone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
