package com.cosmoschat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cosmoschat.R;
import com.cosmoschat.model.BaseChatModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
	private ArrayList<BaseChatModel> models = new ArrayList<>();
	private OnChatListItemClickLister mListener;

	public ChatListAdapter(OnChatListItemClickLister mListener) {
		this.mListener = mListener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.item_chat_list, parent, false);
		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		BaseChatModel model = models.get(position);

		Picasso.with(holder.avatarCIV.getContext())
		    .load(model.getAvatar())
		    .resize(64, 64)
		    .centerCrop()
		    .into(holder.avatarCIV);

		holder.nameTV.setText(model.getName());
		holder.timeTV.setText(model.getTime());
		holder.recentTV.setText(model.getRecent());
	}

	@Override
	public int getItemCount() {
		return models.size();
	}

	public void addNewModel(BaseChatModel model) {
		models.add(model);
		notifyItemInserted(models.size() - 1);
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		CircleImageView avatarCIV;
		TextView nameTV, timeTV, recentTV;

		ViewHolder(View itemView) {
			super(itemView);

			avatarCIV = (CircleImageView) itemView.findViewById(R.id.civ_avatar);
			nameTV = (TextView) itemView.findViewById(R.id.tv_name);
			timeTV = (TextView) itemView.findViewById(R.id.tv_time);
			recentTV = (TextView) itemView.findViewById(R.id.tv_recent);

			itemView.setOnClickListener(this);
			avatarCIV.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.civ_avatar) {
				mListener.onAvatar(getAdapterPosition());
			} else {
				mListener.onItem(getAdapterPosition());
			}
		}
	}

	public interface OnChatListItemClickLister {
		void onAvatar(int position);
		void onItem(int position);
	}
}
