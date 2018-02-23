package it.starksoftware.ssform.model;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.helper.ImagePickerUtils;

public class ImageFactory {

    public static List<Image> singleListFromPath(String path) {
        List<Image> images = new ArrayList<>();
        images.add(new Image(0, ImagePickerUtils.getNameFromFilePath(path), path, true));
        return images;
    }
}