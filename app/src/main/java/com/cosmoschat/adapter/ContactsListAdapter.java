package com.cosmoschat.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmoschat.R;
import com.cosmoschat.model.ContactModel;

import java.util.ArrayList;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {
	private ArrayList<ContactModel> models = new ArrayList<>();

	public void addNewContact(ContactModel model) {
		models.add(model);
		notifyItemInserted(models.size() - 1);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		ViewHolder(View itemView) {
			super(itemView);
		}
	}
}
