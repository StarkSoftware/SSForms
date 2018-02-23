package it.starksoftware.ssform.features.camera;

import java.util.List;

import it.starksoftware.ssform.model.Image;

public interface OnImageReadyListener {
    void onImageReady(List<Image> image);
}
