package it.starksoftware.ssform.model;

/**
 * Created by andreaalessandroni on 26/02/18.
 */

public class Validator {

    private String validatorMessage;
    private boolean validatorStatus;
    private int validatorTag;


    public String getValidatorMessage() {
        return validatorMessage;
    }

    public void setValidatorMessage(String validatorMessage) {
        this.validatorMessage = validatorMessage;
    }

    public boolean isValidatorStatus() {
        return validatorStatus;
    }

    public void setValidatorStatus(boolean validatorStatus) {
        this.validatorStatus = validatorStatus;
    }

    public int getValidatorTag() {
        return validatorTag;
    }

    public void setValidatorTag(int validatorTag) {
        this.validatorTag = validatorTag;
    }
}
