package it.starksoftware.ssform.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.StarkSpinner.Interfaces.ISpinnerSelectedView;
import it.starksoftware.ssform.StarkSpinner.Tools.UITools;
import it.starksoftware.ssform.model.FormSpinnerObject;


public class StarkSpinnerAdapter extends ArrayAdapter<FormSpinnerObject> implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private boolean showIcon;
    private List<FormSpinnerObject> mBackupStrings;
    private List<FormSpinnerObject> mStrings;
    private StringFilter mStringFilter = new StringFilter();

    public StarkSpinnerAdapter(Context context, List<FormSpinnerObject> strings, boolean showIcon) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mStrings = strings;
        mBackupStrings = strings;
        this.showIcon = showIcon;
    }

    @Override
    public int getCount() {
        return mStrings == null ? 0 : mStrings.size() + 1;
    }

    @Override
    public FormSpinnerObject getItem(int position) {
        if (mStrings != null && position > 0)
            return mStrings.get(position - 1);
        else
            return null;
    }

    public int indexOfSpinner(FormSpinnerObject value)
    {
        if (value == null)
            return 0;
        else {
            for (int index = 0, count = this.mStrings.size(); index < count; ++index) {
                if (this.mStrings.get(index).getKey().equals(value.getKey())) {
                    return index + 1;
                }
            }
            return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mStrings == null && position > 0)
            return mStrings.get(position).hashCode();
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item_edit, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position - 1).getValue());
            if (showIcon) {
                letters.setVisibility(View.VISIBLE);
                letters.setImageDrawable(getTextDrawable(mStrings.get(position - 1).getValue()));
            }
            else
                letters.setVisibility(View.GONE);


        }
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view = null;
        if (position == 0) {
            view = getNoSelectionView();
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = (ImageView) view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = (TextView) view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position-1).getValue());
            if (showIcon) {
                letters.setImageDrawable(getTextDrawable(mStrings.get(position-1).getValue()));
                letters.setVisibility(View.VISIBLE);
            }
            else
                letters.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public View getNoSelectionView() {
        View view = View.inflate(mContext, R.layout.view_list_no_selection_item, null);
        return view;
    }

    private TextDrawable getTextDrawable(String displayName) {
        TextDrawable drawable = null;
        if (!TextUtils.isEmpty(displayName)) {
            int color2 = ColorGenerator.MATERIAL.getColor(displayName);
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .textColor(Color.WHITE)
                    .toUpperCase()
                    .endConfig()
                    .round()
                    .build(displayName.substring(0, 1), color2);
        } else {
            drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(UITools.dpToPx(mContext, 32))
                    .height(UITools.dpToPx(mContext, 32))
                    .endConfig()
                    .round()
                    .build("?", Color.GRAY);
        }
        return drawable;
    }

    @Override
    public Filter getFilter() {
        return mStringFilter;
    }

    public class StringFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = mBackupStrings.size();
                filterResults.values = mBackupStrings;
                return filterResults;
            }
            final ArrayList<FormSpinnerObject> filterStrings = new ArrayList<>();
            for (FormSpinnerObject text : mBackupStrings) {
                if (text.getValue().toLowerCase().contains(constraint)) {
                    filterStrings.add(text);
                }
            }
            filterResults.count = filterStrings.size();
            filterResults.values = filterStrings;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStrings = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    }

    private class ItemView {
        public ImageView mImageView;
        public TextView mTextView;
    }

    public enum ItemViewType {
        ITEM, NO_SELECTION_ITEM;
    }
}
