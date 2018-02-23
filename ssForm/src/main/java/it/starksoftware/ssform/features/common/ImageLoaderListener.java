package it.starksoftware.ssform.features.common;

import java.util.List;

import it.starksoftware.ssform.model.Folder;
import it.starksoftware.ssform.model.Image;

public interface ImageLoaderListener {
    void onImageLoaded(List<Image> images, List<Folder> folders);
    void onFailed(Throwable throwable);
}