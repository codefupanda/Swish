/**
 * 
 */
package com.codefupanda.app.swish.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.codefupanda.app.swish.R;
import com.codefupanda.app.swish.entity.Contact;

public class CustomAdapter extends BaseAdapter {

	private Context context;
	private List<Contact> contacts;

	private boolean[] itemChecked;

	static int selectcount = 0;

	public CustomAdapter(Context context, List<Contact> contacts) {
		this.context = context;
		this.contacts = contacts;
		itemChecked = new boolean[contacts.size()];
	}

	@Override
	public int getCount() {
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return contacts.indexOf(getItem(position));
	}

	/* private view holder class */
	private class ViewHolder {
		ImageView profilePic;
		TextView name;
		TextView dateOfBirth;
		CheckBox checkBox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			final ViewHolder holder = new ViewHolder();

			// add or setting data's to listItem row
			Contact contact = contacts.get(position);

			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.profilePic = (ImageView) convertView
					.findViewById(R.id.profile_pic);
			holder.dateOfBirth = (TextView) convertView
					.findViewById(R.id.date_of_birth);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.chk);

			holder.profilePic.setImageResource(contact.getProfilePic());
			holder.name.setText(contact.getName());
			holder.dateOfBirth.setText(DateUtils.formatDateTime(context,
					contact.getDateOfBirth().getTime(),
					DateUtils.FORMAT_SHOW_DATE));
			holder.checkBox.setChecked(false);

			if (itemChecked[position]) {
				holder.checkBox.setChecked(true);
			} else {
				holder.checkBox.setChecked(false);
			}
			
			holder.checkBox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (holder.checkBox.isChecked()) {
						itemChecked[position] = true;
						selectcount++;
					} else {
						itemChecked[position] = false;
						selectcount--;
					}
				}
			});

			convertView.setTag(holder);
		}

		return convertView;
	}

	/**
	 * Get count of items selected
	 * 
	 * @return count of items selected
	 */
	public static int getItemSelectCount() {
		return selectcount;
	}

}