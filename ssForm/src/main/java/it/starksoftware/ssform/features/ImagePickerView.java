package it.starksoftware.ssform.features;

import it.starksoftware.ssform.features.common.MvpView;
import it.starksoftware.ssform.model.Folder;
import it.starksoftware.ssform.model.Image;

import java.util.List;

public interface ImagePickerView extends MvpView {
    void showLoading(boolean isLoading);
    void showFetchCompleted(List<Image> images, List<Folder> folders);
    void showError(Throwable throwable);
    void showEmpty();
    void showCapturedImage();
    void finishPickImages(List<Image> images);
}
