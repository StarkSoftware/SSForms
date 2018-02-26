package it.starksoftware.ssform.model;

/**
 * Basic interface for all form elements,
 * every form header and element will implement this
 * Created by Adib on 18-Apr-17.
 */

public interface FormObject {

    /**
     * returns if header element or not
     */
    boolean isHeader();
    boolean isRequired();
    String getElementType();
}
