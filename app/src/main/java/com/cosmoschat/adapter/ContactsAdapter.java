package com.cosmoschat.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmoschat.R;
import com.cosmoschat.model.ContactModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
	private ArrayList<ContactModel> models = new ArrayList<>();
	private OnContactsItemClickLister mListener;

	public ContactsAdapter(OnContactsItemClickLister listener) {
		this.mListener = listener;
	}

	public void addNewUser(ContactModel model) {
		models.add(model);
		notifyItemInserted(models.size() - 1);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		ContactModel model = models.get(position);

		Picasso.with(holder.avatarCIV.getContext())
		    .load(model.getAvatar())
		    .resize(64, 64)
		    .centerCrop()
		    .into(holder.avatarCIV);

		holder.nameTV.setText(model.getName());
		holder.statusTV.setText(model.getStatus());
	}

	@Override
	public int getItemCount() {
		return models.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		CircleImageView avatarCIV;
		TextView nameTV, statusTV;

		ViewHolder(View itemView) {
			super(itemView);

			avatarCIV = (CircleImageView) itemView.findViewById(R.id.civ_avatar);
			nameTV = (TextView) itemView.findViewById(R.id.tv_name);
			statusTV = (TextView) itemView.findViewById(R.id.tv_status);

			itemView.setOnClickListener(this);
			avatarCIV.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.civ_avatar) {
				mListener.onAvatar(models.get(getAdapterPosition()), getAdapterPosition());
			} else {
				mListener.onItem(models.get(getAdapterPosition()), getAdapterPosition());
			}
		}
	}

	public interface OnContactsItemClickLister {
		void onAvatar(ContactModel model, int position);
		void onItem(ContactModel model, int position);
	}
}
