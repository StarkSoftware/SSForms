package it.starksoftware.ssform.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.StarkSpinner.Interfaces.ISpinnerSelectedView;
import it.starksoftware.ssform.StarkSpinner.Tools.UITools;
import it.starksoftware.ssform.model.FormSpinnerObject;


public class StarkSpinnerAdapter extends ArrayAdapter<FormSpinnerObject> implements Filterable, ISpinnerSelectedView {

    private Context mContext;
    private boolean showIcon;
    private boolean allowNoSelection;
    private List<FormSpinnerObject> mBackupStrings;
    private List<FormSpinnerObject> mStrings;
    private StringFilter mStringFilter = new StringFilter();

    public StarkSpinnerAdapter(Context context, List<FormSpinnerObject> strings, boolean showIcon, boolean allowNoSelection) {
        super(context, R.layout.view_list_item);
        mContext = context;
        mStrings = strings;
        mBackupStrings = strings;
        this.showIcon = showIcon;
        this.allowNoSelection = allowNoSelection;
    }

    @Override
    public int getCount() {
        if (allowNoSelection) {
            return mStrings == null ? 0 : mStrings.size() + 1;
        } else {
            return mStrings.size();
        }
    }

    @Override
    public FormSpinnerObject getItem(int position) {
        if (allowNoSelection) {
            if (mStrings != null && position > 0)
                return mStrings.get(position - 1);
            else
                return null;
        } else {
            if (mStrings != null)
                return mStrings.get(position);
            else
                return null;
        }
    }

    int indexOfSpinner(FormSpinnerObject value)
    {
        if (allowNoSelection) {
            if (value == null)
                return 0;
            else {
                for (int index = 0, count = this.mStrings.size(); index < count; ++index) {
                    if (this.mStrings.get(index).getKey().toLowerCase(Locale.getDefault()).equals(value.getKey().toLowerCase(Locale.getDefault()))) {
                        return index + 1;
                    }
                }
                return 0;
            }
        } else {
            if (value == null)
                return 0;
            else {
                for (int index = 0, count = this.mStrings.size(); index < count; ++index) {
                    if (this.mStrings.get(index).getKey().toLowerCase(Locale.getDefault()).equals(value.getKey().toLowerCase(Locale.getDefault()))) {
                        return index;
                    }
                }
                return 0;
            }
        }
    }

    @SuppressLint("Assert")
    @Override
    public long getItemId(int position) {
        if (allowNoSelection) {
            if (mStrings == null && position > 0) {
                assert false;
                return mStrings.get(position).hashCode();
            }
            else
                return -1;
        } else {
            if (mStrings == null) {
                assert false;
                return mStrings.get(position).hashCode();
            }
            else
                return 0;
        }
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {

        View view;
        if (allowNoSelection) {
            if (position == 0) {
                view = getNoSelectionView();
            } else {
                view = View.inflate(mContext, R.layout.view_list_item_edit, null);
                ImageView letters = view.findViewById(R.id.ImgVw_Letters);
                TextView dispalyName = view.findViewById(R.id.TxtVw_DisplayName);
                dispalyName.setText(mStrings.get(position - 1).getValue());
                if (showIcon) {
                    letters.setVisibility(View.VISIBLE);
                    letters.setImageDrawable(getTextDrawable(mStrings.get(position - 1).getValue()));
                } else
                    letters.setVisibility(View.GONE);
            }
        } else {
            view = View.inflate(mContext, R.layout.view_list_item_edit, null);
            ImageView letters = view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position).getValue());
            if (showIcon) {
                letters.setVisibility(View.VISIBLE);
                letters.setImageDrawable(getTextDrawable(mStrings.get(position).getValue()));
            } else
                letters.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public View getSelectedView(int position) {
        View view;
        if (allowNoSelection) {
            if (position == 0) {
                view = getNoSelectionView();
            } else {
                view = View.inflate(mContext, R.layout.view_list_item, null);
                ImageView letters = view.findViewById(R.id.ImgVw_Letters);
                TextView dispalyName = view.findViewById(R.id.TxtVw_DisplayName);
                dispalyName.setText(mStrings.get(position - 1).getValue());
                if (showIcon) {
                    letters.setImageDrawable(getTextDrawable(mStrings.get(position - 1).getValue()));
                    letters.setVisibility(View.VISIBLE);
                } else
                    letters.setVisibility(View.GONE);
            }
        } else {
            view = View.inflate(mContext, R.layout.view_list_item, null);
            ImageView letters = view.findViewById(R.id.ImgVw_Letters);
            TextView dispalyName = view.findViewById(R.id.TxtVw_DisplayName);
            dispalyName.setText(mStrings.get(position).getValue());
            if (showIcon) {
                letters.setImageDrawable(getTextDrawable(mStrings.get(position).getValue()));
                letters.setVisibility(View.VISIBLE);
            } else
                letters.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public View getNoSelectionView() {
        return View.inflate(mContext, R.layout.view_list_no_selection_item, null);
    }

    private TextDrawable getTextDrawable(String displayName) {
        TextDrawable drawable;
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

    @NotNull
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
                if (text.getValue().toLowerCase(Locale.getDefault()).contains(constraint.toString().toLowerCase(Locale.getDefault()))) {
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

    private static class ItemView {
        public ImageView mImageView;
        public TextView mTextView;
    }

    public enum ItemViewType {
        ITEM, NO_SELECTION_ITEM;
    }
}
