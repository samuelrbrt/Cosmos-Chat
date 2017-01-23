package com.cosmoschat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmoschat.R;
import com.cosmoschat.model.ChatModel;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
	private static final int VIEW_TYPE_FROM = 0;
	private static final int VIEW_TYPE_TO = 1;

	private ArrayList<ChatModel> models = new ArrayList<>();

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView;

		switch (viewType) {
			case VIEW_TYPE_FROM:
				itemView = inflater.inflate(R.layout.item_view_chat_from, parent, false);
				return new ViewHolder(itemView);

			case VIEW_TYPE_TO:
				itemView = inflater.inflate(R.layout.item_view_chat_to, parent, false);
				return new ViewHolder(itemView);

			default:
				//TODO: Implement new message, day time separator
				return null;

		}
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		ChatModel model = models.get(position);

		holder.messageTV.setText(model.getMessage());
		holder.timeTV.setText(model.getTime());
	}

	@Override
	public int getItemCount() {
		return models.size();
	}

	public void addItem(ChatModel model) {
		models.add(model);
		notifyItemInserted(models.size() - 1);
	}

	public void updateItem(ChatModel model, int position) {
		models.set(position, model);
		notifyItemChanged(position, model);
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		TextView messageTV, timeTV;

		ViewHolder(View itemView) {
			super(itemView);

			messageTV = (TextView) itemView.findViewById(R.id.tv_item_message);
			timeTV = (TextView) itemView.findViewById(R.id.tv_item_time);
		}
	}
}
