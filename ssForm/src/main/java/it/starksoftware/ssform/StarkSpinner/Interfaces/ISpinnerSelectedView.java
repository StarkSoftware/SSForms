package it.starksoftware.ssform.StarkSpinner.Interfaces;

import android.view.View;

public interface ISpinnerSelectedView {
    View getNoSelectionView();
    View getSelectedView(int position);
}
