package it.starksoftware.ssform.StarkSpinner.Interfaces;

import android.view.View;
import android.widget.AdapterView;

public interface OnItemSelectedListener {
    void onItemSelected(View view, int position, long id);
    void onNothingSelected();
}
