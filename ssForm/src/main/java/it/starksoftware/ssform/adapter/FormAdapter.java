package it.starksoftware.ssform.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.codemybrainsout.placesearch.PlaceSearchDialog;
import com.google.android.flexbox.FlexboxLayout;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import it.starksoftware.ssform.R;
import it.starksoftware.ssform.activities.RxSignaturePicker;
import it.starksoftware.ssform.activities.RxTokenPicker;
import it.starksoftware.ssform.attach.AttachPicker;
import it.starksoftware.ssform.activities.RxAttachPicker;
import it.starksoftware.ssform.activities.RxImagePicker;
import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.helper.AppTools;
import it.starksoftware.ssform.interfaces.ButtonCallBack;
import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.interfaces.DateTimeCallBack;
import it.starksoftware.ssform.interfaces.RatingCallBack;
import it.starksoftware.ssform.interfaces.SearchableSpinnerCallBack;
import it.starksoftware.ssform.interfaces.SegmentCallBack;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;
import it.starksoftware.ssform.interfaces.SwitchCallBack;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementButton;
import it.starksoftware.ssform.model.FormElementCheckBox;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;
import it.starksoftware.ssform.model.FormElementDateTime;
import it.starksoftware.ssform.model.FormElementImageMultipleView;
import it.starksoftware.ssform.model.FormElementImageView;
import it.starksoftware.ssform.model.FormElementMemo;
import it.starksoftware.ssform.model.FormElementPlaceDialog;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.model.FormElementSearchableSpinner;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.model.FormElementSignature;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.model.FormElementToken;
import it.starksoftware.ssform.model.FormHeader;
import it.starksoftware.ssform.model.FormObject;
import it.starksoftware.ssform.model.FormSpinnerObject;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Image;
import it.starksoftware.ssform.model.TokesTags;
import it.starksoftware.ssform.ratings.BaseRatingBar;
import it.starksoftware.ssform.segmented.SegmentedGroup;
import it.starksoftware.ssform.signaturepad.SignaturePicker;
import it.starksoftware.ssform.tokens.AddTokensActivity;
import it.starksoftware.ssform.tokens.TokensPicker;
import it.starksoftware.ssform.view.GridSpacingItemDecoration;
import rx.Observable;
import rx.functions.Action1;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    // defining marker for header view
    private int IS_HEADER_VIEW = 0;
    private int IS_DEFAULT_VIEW = 1;
    private int IS_SWITCH_VIEW = 2;
    private int IS_IMAGE_VIEW = 3;
    private int IS_SPINNER_VIEW = 4;
    private int IS_MEMO_VIEW = 5;
    private int IS_DIVIDER_VIEW = 6;
    private int IS_SEGMENT_VIEW = 7;
    private int IS_ATTACH_VIEW = 8;
    private int IS_SIGNATURE_VIEW = 9;
    private int IS_RATING_VIEW = 10;
    private int IS_MULTIPLEIMAGE_VIEW = 11;
    private int IS_CUSTOM_KEYBOARD = 12;
    private int IS_DATE_TIME = 13;
    private int IS_SEARCHABLE_SPINNER_VIEW = 14;
    private int IS_BUTTON_VIEW = 15;
    private int IS_CHECKBOX_VIEW = 16;
    private int IS_PLACE_DIALOG_VIEW = 17;
    private int IS_TOKEN = 18;


    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<String> attachs = new ArrayList<>();
    private List<FormObject> mDataset;
    private Context mContext;
    private Activity mActivity;
    private Calendar mCalendarCurrentDate;
    private Calendar mCalendarCurrentTime;
    private SpinnerDialog spinnerDialog;
    private FormSpinAdapter spinAdapter;
    private int clickedPosition;
    private FragmentManager fManager;

    public FormAdapter(Context context, Activity activity, FragmentManager fragmentManager) {
        mContext = context;
        mActivity = activity;
        mDataset = new ArrayList<>();
        mCalendarCurrentDate = Calendar.getInstance();
        mCalendarCurrentTime = Calendar.getInstance();
        clickedPosition = -1;
        fManager = fragmentManager;
    }

    public void addElements(List<FormObject> formObjects) {
        this.mDataset = formObjects;
    }

    public void addElement(FormObject formObject) {

        this.mDataset.add(formObject);
    }

    public List<FormObject> getFormItems() {
        return mDataset;
    }

    public void removeElement(FormObject formObject) {

        this.mDataset.remove(formObject);
    }

    public void hideElement(FormObject formObject) {

        this.mDataset.remove(formObject);
    }

    public void setValueAtIndex(int position, String value) {
        FormElement formElement = (FormElement) mDataset.get(position);
        formElement.setValue(value);
    }

    public void setCustomKeyboardValueAtIndex(int position, String value) {
        FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) mDataset.get(position);
        formElement.setValue(value);
    }

    public void setValueAtTag(int tag, String value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElement) {
                FormElement formElement = (FormElement) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setImageValueAtTag(int tag, Bitmap value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementImageView) {
                FormElementImageView formElement = (FormElementImageView) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setImageMultipleValueAtTag(int tag, List<Bitmap> value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementImageMultipleView) {
                FormElementImageMultipleView formElement = (FormElementImageMultipleView) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setSwitchValueAtTag(int tag, Boolean value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSwitch) {
                FormElementSwitch formElement = (FormElementSwitch) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setSpinnerValueAtTag(int tag, FormSpinnerObject value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSpinner) {
                FormElementSpinner formElement = (FormElementSpinner) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setSpinnerPositionatTag(int tag, FormSpinnerObject value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSpinner) {
                FormElementSpinner formElement = (FormElementSpinner) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public void setMemoValueAtTag(int tag, String value) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementMemo) {
                FormElementMemo formElement = (FormElementMemo) f;
                if (formElement.getTag() == tag) {
                    formElement.setValue(value);
                    return;
                }
            }
        }
    }

    public FormElementCustomKeyboard getCustomKeyboardValueAtIndex(int index) {
        return ((FormElementCustomKeyboard) mDataset.get(index));
    }

    public FormElement getValueAtIndex(int index) {
        return ((FormElement) mDataset.get(index));
    }

    public FormElementMemo getMemoValueAtIndex(int index) {
        return ((FormElementMemo) mDataset.get(index));
    }

    //MODIFICHE PER FIX SPINNER
    public FormElementSpinner getSpinnerValueAtIndex(int index) {
        return ((FormElementSpinner) mDataset.get(index));
    }

    //

    public FormElementCustomKeyboard getCustomKeyboardValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementCustomKeyboard) {
                FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) f;
                if (formElement.getTag() == tag) {
                    Log.d("FM", "ELEMENT NAME -->" + formElement.getTitle());
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElement getValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElement) {
                FormElement formElement = (FormElement) f;
                if (formElement.getTag() == tag) {
                    Log.d("FM", "ELEMENT NAME -->" + formElement.getTitle());
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormDivider getDividerValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormDivider) {
                FormDivider formElement = (FormDivider) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementImageView getImageValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementImageView) {
                FormElementImageView formElement = (FormElementImageView) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementImageMultipleView getImageMultipleValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementImageMultipleView) {
                FormElementImageMultipleView formElement = (FormElementImageMultipleView) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementSwitch getSwitchValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSwitch) {
                FormElementSwitch formElement = (FormElementSwitch) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementRating getRatingValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementRating) {
                FormElementRating formElement = (FormElementRating) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }


    public FormElementButton getButtonValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementButton) {
                FormElementButton formElement = (FormElementButton) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementSpinner getSpinnerValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSpinner) {
                FormElementSpinner formElement = (FormElementSpinner) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementSearchableSpinner getSearchableSpinnerValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementSearchableSpinner) {
                FormElementSearchableSpinner formElement = (FormElementSearchableSpinner) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public int getPositionByTag(int tag) {

        int itemPosition = -1;

        for (int index = 0, count = this.mDataset.size(); index < count; ++index) {
            if (mDataset.get(index).getElementType().contentEquals("Header")) {
                FormHeader element = (FormHeader) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Switch")) {
                FormElementSwitch element = (FormElementSwitch) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("ImageView")) {
                FormElementImageView element = (FormElementImageView) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Spinner")) {
                FormElementSpinner element = (FormElementSpinner) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Memo")) {
                FormElementMemo element = (FormElementMemo) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Divider")) {
                FormDivider element = (FormDivider) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Segment")) {
                FormElementSegment element = (FormElementSegment) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Attach")) {
                FormElementAttach element = (FormElementAttach) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Signature")) {
                FormElementSignature element = (FormElementSignature) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Rating")) {
                FormElementRating element = (FormElementRating) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Basic")) {
                FormElement element = (FormElement) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("ImageViewMultiple")) {
                FormElementImageMultipleView element = (FormElementImageMultipleView) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("CustomKeyboard")) {
                FormElementCustomKeyboard element = (FormElementCustomKeyboard) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("DateTime")) {
                FormElementDateTime element = (FormElementDateTime) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("SearchableSpinner")) {
                FormElementSearchableSpinner element = (FormElementSearchableSpinner) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Button")) {
                FormElementButton element = (FormElementButton) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("CheckBox")) {
                FormElementCheckBox element = (FormElementCheckBox) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("PlaceDialog")) {
                FormElementPlaceDialog element = (FormElementPlaceDialog) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            } else if (mDataset.get(index).getElementType().contentEquals("Token")) {
                FormElementToken element = (FormElementToken) mDataset.get(index);
                if (element.getTag() == tag)
                    itemPosition = index;
            }
        }
        Log.d("FM", "Position --> " + itemPosition);
        Log.d("FM", "Tag --> " + tag);
        return itemPosition;
    }

    public FormElementAttach getAttachValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementAttach) {
                FormElementAttach formElement = (FormElementAttach) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }


    public FormElementCustomKeyboard getCustomKeyboardElementByTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementCustomKeyboard) {
                FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }

    public FormElementMemo getMemoValueAtTag(int tag) {
        for (FormObject f : this.mDataset) {
            if (f instanceof FormElementMemo) {
                FormElementMemo formElement = (FormElementMemo) f;
                if (formElement.getTag() == tag) {
                    return formElement;
                }
            }
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getElementType().contentEquals("Header")) {
            return IS_HEADER_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Switch")) {
            return IS_SWITCH_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("ImageView")) {
            return IS_IMAGE_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Spinner")) {
            return IS_SPINNER_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Memo")) {
            return IS_MEMO_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Divider")) {
            return IS_DIVIDER_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Segment")) {
            return IS_SEGMENT_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Attach")) {
            return IS_ATTACH_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Signature")) {
            return IS_SIGNATURE_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Rating")) {
            return IS_RATING_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("ImageViewMultiple")) {
            return IS_MULTIPLEIMAGE_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("CustomKeyboard")) {
            return IS_CUSTOM_KEYBOARD;
        } else if (mDataset.get(position).getElementType().contentEquals("DateTime")) {
            return IS_DATE_TIME;
        } else if (mDataset.get(position).getElementType().contentEquals("SearchableSpinner")) {
            return IS_SEARCHABLE_SPINNER_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Button")) {
            return IS_BUTTON_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("CheckBox")) {
            return IS_CHECKBOX_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("PlaceDialog")) {
            return IS_PLACE_DIALOG_VIEW;
        } else if (mDataset.get(position).getElementType().contentEquals("Token")) {
            return IS_TOKEN;
        } else {
            return IS_DEFAULT_VIEW;
        }
    }

    @Override
    public FormAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (parent != null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v;
            ViewHolder vh;
            switch (viewType) {
                case 0:
                    v = inflater.inflate(R.layout.form_element_header, parent, false);
                    vh = new ViewHolder(v, null, IS_HEADER_VIEW, null);
                    break;
                case 1:
                    v = inflater.inflate(R.layout.form_element, parent, false);
                    vh = new ViewHolder(v, new FormCustomEditTextListener(), IS_DEFAULT_VIEW, null);
                    break;
                case 2:
                    v = inflater.inflate(R.layout.form_element_switch, parent, false);
                    vh = new ViewHolder(v, null, IS_SWITCH_VIEW, null);
                    break;
                case 3:
                    v = inflater.inflate(R.layout.form_element_imageview, parent, false);
                    vh = new ViewHolder(v, null, IS_IMAGE_VIEW, null);
                    break;
                case 4:
                    v = inflater.inflate(R.layout.form_element_spinner, parent, false);
                    vh = new ViewHolder(v, null, IS_SPINNER_VIEW, null);
                    break;
                case 5:
                    v = inflater.inflate(R.layout.form_element_memo, parent, false);
                    vh = new ViewHolder(v, null, IS_MEMO_VIEW, new FormCustomEditMemoTextListener());
                    break;
                case 6:
                    v = inflater.inflate(R.layout.form_element_divider, parent, false);
                    vh = new ViewHolder(v, null, IS_DIVIDER_VIEW, null);
                    break;
                case 7:
                    v = inflater.inflate(R.layout.form_element_segment, parent, false);
                    vh = new ViewHolder(v, null, IS_SEGMENT_VIEW, null);
                    break;
                case 8:
                    v = inflater.inflate(R.layout.form_element_attach, parent, false);
                    vh = new ViewHolder(v, null, IS_ATTACH_VIEW, null);
                    break;
                case 9:
                    v = inflater.inflate(R.layout.form_element_signature, parent, false);
                    vh = new ViewHolder(v, null, IS_SIGNATURE_VIEW, null);
                    break;
                case 10:
                    v = inflater.inflate(R.layout.form_element_rating, parent, false);
                    vh = new ViewHolder(v, null, IS_RATING_VIEW, null);
                    break;
                case 11:
                    v = inflater.inflate(R.layout.form_element_imageview_multiple, parent, false);
                    vh = new ViewHolder(v, null, IS_MULTIPLEIMAGE_VIEW, null);
                    break;
                case 12:
                    v = inflater.inflate(R.layout.form_element_custom_keyboard, parent, false);
                    vh = new ViewHolder(v, null, IS_CUSTOM_KEYBOARD, null);
                    break;
                case 13:
                    v = inflater.inflate(R.layout.form_element_datetime, parent, false);
                    vh = new ViewHolder(v, null, IS_DATE_TIME, null);
                    break;
                case 14:
                    v = inflater.inflate(R.layout.form_element_searchable_spinner, parent, false);
                    vh = new ViewHolder(v, null, IS_SEARCHABLE_SPINNER_VIEW, null);
                    break;
                case 15:
                    v = inflater.inflate(R.layout.form_element_button, parent, false);
                    vh = new ViewHolder(v, null, IS_BUTTON_VIEW, null);
                    break;
                case 16:
                    v = inflater.inflate(R.layout.form_element_checkbox, parent, false);
                    vh = new ViewHolder(v, null, IS_CHECKBOX_VIEW, null);
                    break;
                case 17:
                    v = inflater.inflate(R.layout.form_element_place_dialog, parent, false);
                    vh = new ViewHolder(v, null, IS_PLACE_DIALOG_VIEW, null);
                    break;
                case 18:
                    v = inflater.inflate(R.layout.form_element_token, parent, false);
                    vh = new ViewHolder(v, null, IS_TOKEN, null);
                    break;
                default:
                    v = inflater.inflate(R.layout.form_element_header, parent, false);
                    vh = new ViewHolder(v, null, IS_HEADER_VIEW, null);
                    break;
            }
            return vh;
        } else
            return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // updates edit text listener index
        if (holder.mFormCustomEditTextListener != null)
            holder.mFormCustomEditTextListener.updatePosition(holder.getAdapterPosition());

        if (holder.mFormCustomEditMemoTextListener != null)
            holder.mFormCustomEditMemoTextListener.updatePosition(holder.getAdapterPosition());

        FormObject currentObject = mDataset.get(position);

        if (getItemViewType(position) == IS_HEADER_VIEW) {
            FormHeader formHeader = (FormHeader) currentObject;
            holder.mTextViewTitle.setText(formHeader.getTitle());
        } else if (getItemViewType(position) == IS_DIVIDER_VIEW) {
            FormDivider formHeader = (FormDivider) currentObject;
        } else if (getItemViewType(position) == IS_DEFAULT_VIEW) {
            FormElement formElement = (FormElement) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditTextValue.setText(formElement.getValue());

            switch (formElement.getType()) {
                case FormElement.TYPE_EDITTEXT_TEXT_SINGLELINE:
                    holder.mEditTextValue.setMaxLines(1);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_TEXT_MULTILINE:
                    holder.mEditTextValue.setSingleLine(false);
                    holder.mEditTextValue.setMaxLines(4);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_NUMBER:
                    holder.mEditTextValue.setRawInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                    KeyListener keyListenerkeyListenerEDITTEXT_NUMBER = DigitsKeyListener.getInstance("1234567890.");
                    holder.mEditTextValue.setKeyListener(keyListenerkeyListenerEDITTEXT_NUMBER);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_NUMBER_INTEGER:
                    holder.mEditTextValue.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                    KeyListener keyListenerNUMBER_INTEGER = DigitsKeyListener.getInstance("1234567890");
                    holder.mEditTextValue.setKeyListener(keyListenerNUMBER_INTEGER);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_EMAIL:
                    holder.mEditTextValue.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_PHONE:
                    holder.mEditTextValue.setRawInputType(InputType.TYPE_CLASS_PHONE);
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_EDITTEXT_PASSWORD:
                    holder.mEditTextValue.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    holder.mEditTextValue.setSelection(holder.mEditTextValue.getText().length());
                    setEditTextFocusEnabled(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_PICKER_DATE:
                    setDatePicker(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_PICKER_TIME:
                    setTimePicker(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_SPINNER_DROPDOWN:
                    setSingleOptionsDialog(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                case FormElement.TYPE_PICKER_MULTI_CHECKBOX:
                    setMultipleOptionsDialog(holder.mEditTextValue, position, holder.layoutRow);
                    break;
                default:
                    break;
            }

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }

        } else if (getItemViewType(position) == IS_SWITCH_VIEW) {
            final FormElementSwitch formElement = (FormElementSwitch) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditSwitchValue.setChecked(formElement.getValue());
            final SwitchCallBack switchCallBack = formElement.getSwitchCallBack();
            holder.mEditSwitchValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switchCallBack.callbackSwitchReturn(formElement, formElement.getTag(), isChecked);
                    formElement.setValue(isChecked);
                }
            });
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_SEGMENT_VIEW) {
            final FormElementSegment formElement = (FormElementSegment) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            SegmentedGroup segmentedGroup = holder.mEditSegmentGroupValue;
            final SegmentCallBack segmentCallBack = formElement.getSegmentCallBack();
            final RadioButton rbOne = (RadioButton) segmentedGroup.findViewById(R.id.radioOne);
            final RadioButton rbTwo = (RadioButton) segmentedGroup.findViewById(R.id.radioTwo);
            rbOne.setText(formElement.getSegmentedButtons().get(0).getText());
            rbOne.setTag(formElement.getSegmentedButtons().get(0).getTag());
            rbOne.setChecked(formElement.getValueCheckA());

            rbTwo.setText(formElement.getSegmentedButtons().get(1).getText());
            rbTwo.setTag(formElement.getSegmentedButtons().get(1).getTag());
            rbTwo.setChecked(formElement.getValueCheckB());

            segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    if (checkedId == R.id.radioOne) {
                        segmentCallBack.callbackSegmentReturn(radioGroup, 0);
                        formElement.setValueCheckA(true);
                        formElement.setValueCheckB(false);
                        rbOne.setChecked(true);
                        rbTwo.setChecked(false);
                    } else if (checkedId == R.id.radioTwo) {
                        segmentCallBack.callbackSegmentReturn(radioGroup, 1);
                        formElement.setValueCheckA(false);
                        formElement.setValueCheckB(true);
                        rbOne.setChecked(false);
                        rbTwo.setChecked(true);
                    }
                }
            });

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_IMAGE_VIEW) {
            FormElementImageView formElement = (FormElementImageView) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditImageViewValue.setImageBitmap(formElement.getValue());
            setImagePicker(holder.mEditImageViewValue, position, holder.layoutRow);
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_TOKEN) {
            final FormElementToken formElement = (FormElementToken) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            if (formElement.getValue() != null) {
                if(((FlexboxLayout) holder.tokens).getChildCount() > 0)
                    ((FlexboxLayout) holder.tokens).removeAllViews();
                for (int i = 0; i < formElement.getValue().size(); i++) {
                    holder.tokens.addView((View) formElement.getValue().get(i).getTokenItem(), 0);
                }
            }
            setTokenPicker(holder.btnAddTokens, position, formElement);

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_SIGNATURE_VIEW) {
            FormElementSignature formElement = (FormElementSignature) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditImageViewValue.setImageBitmap(formElement.getValue());
            setSignaturePicker(holder.mEditImageViewValue, position, holder.layoutRow);
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_PLACE_DIALOG_VIEW) {
            FormElementPlaceDialog formElement = (FormElementPlaceDialog) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mTextViewValue.setText(formElement.getValue());
            setPlaceDialogPicker(holder.mTextViewValue, position, holder.layoutRow, formElement);
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_MEMO_VIEW) {
            FormElementMemo formElement = (FormElementMemo) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditMemoTextValue.setText(formElement.getValue());
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_BUTTON_VIEW) {
            final FormElementButton formElement = (FormElementButton) currentObject;
            final ButtonCallBack buttonCallBack = formElement.getButtonCallBack();
            holder.mButtonTitle.setText(formElement.getTitle());
            holder.mButtonTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    buttonCallBack.callbackButtonReturn(formElement, formElement.getTag());
                }
            });
        } else if (getItemViewType(position) == IS_ATTACH_VIEW) {
            FormElementAttach formElement = (FormElementAttach) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mTextViewAttachValue.setText(formElement.getValue());
            setAttachPicker(holder.mTextViewAttachValue, position, holder.layoutRow);
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_RATING_VIEW) {
            final FormElementRating formElement = (FormElementRating) currentObject;
            final RatingCallBack ratingCallBack = formElement.getRatingCallBack();
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditRatingValue.setNumStars(formElement.getStars());
            holder.mEditRatingValue.setRating(formElement.getRatingValue());
            holder.mEditRatingValue.setOnRatingChangeListener(new BaseRatingBar.OnRatingChangeListener() {
                @Override
                public void onRatingChange(BaseRatingBar ratingBar, float rating) {
                    formElement.setRatingValue(Math.round(rating));
                    ratingCallBack.callbackRatingReturn(rating);
                }
            });
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_SEARCHABLE_SPINNER_VIEW) {
            final FormElementSearchableSpinner formElement = (FormElementSearchableSpinner) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mTextViewDetail.setText(formElement.getValue());
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {

                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
            setSearchableSpinnerView(holder.mTextViewDetail, position, holder.layoutRow, formElement);


        } else if (getItemViewType(position) == IS_SPINNER_VIEW) {
            final FormElementSpinner formElement = (FormElementSpinner) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            final FormSpinAdapter adapter = formElement.getSpinnerAdapter();
            final SpinnerCallBack spinnerCallBack = formElement.getCallback();
            holder.mEditSpinnerValue.setAdapter(adapter);
            if (formElement.getValue() != null) {
                int spinnerPosition = adapter.indexOfSpinner(formElement.getValue());
                holder.mEditSpinnerValue.setSelection(spinnerPosition);
            }

            if (formElement.getRefresh())
                holder.mEditSpinnerValue.setTag(formElement.getTag());

            holder.mEditSpinnerValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    FormSpinnerObject user = adapter.getItem(position);
                    formElement.setValue(user);
                    if (spinnerCallBack != null)
                        spinnerCallBack.callbackSpinnerReturn(user, formElement.getTag(), holder.mEditSpinnerValue);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapter) {

                }
            });
            holder.mEditSpinnerValue.setOnFocusChangeListener(new AdapterView.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    String aa = "";
                }
            });

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {

                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }

        } else if (getItemViewType(position) == IS_MULTIPLEIMAGE_VIEW) {
            FormElementImageMultipleView formElement = (FormElementImageMultipleView) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 10);
            holder.mEditImageViewMultipleValue.setLayoutManager(mLayoutManager);
            holder.mEditImageViewMultipleValue.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            holder.mEditImageViewMultipleValue.setItemAnimator(new DefaultItemAnimator());
            holder.mEditImageViewMultipleValue.setAdapter(formElement.getImgAdapter());
            setImagePickerMultiple(holder.btnAdd, position, formElement.getMaxImages(), holder.layoutRow);
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_CHECKBOX_VIEW) {
            final FormElementCheckBox formElement = (FormElementCheckBox) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());

            if (formElement.getCallback() != null) {
                final CheckBoxCallBack checkBoxCallBack = formElement.getCallback();
                holder.mEditCheckBoxValue.setChecked(formElement.isChecked());
                holder.mEditCheckBoxValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        checkBoxCallBack.callbackCheckBoxReturn(formElement.getTag(), holder.mEditCheckBoxValue, isChecked);
                    }
                });
            }
            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_CUSTOM_KEYBOARD) {
            final FormElementCustomKeyboard formElement = (FormElementCustomKeyboard) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            holder.mEditTextValue.setText(formElement.getValue());
            holder.mEditTextValue.setFocusableInTouchMode(true);
            holder.layoutRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    formElement.getmKeyboard().registerEditText(holder.mEditTextValue, formElement);
                }
            });
            formElement.getmKeyboard().registerEditText(holder.mEditTextValue, formElement);

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }
        } else if (getItemViewType(position) == IS_DATE_TIME) {
            final FormElementDateTime formElement = (FormElementDateTime) currentObject;
            holder.mTextViewTitle.setText(formElement.getTitle());
            switch (formElement.getType()) {
                case FormElementDateTime.TYPE_PICKER_DATE:
                    if (formElement.getValue() != null) {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        holder.mTextViewValue.setText(dateFormat.format(formElement.getValue()));
                    }
                    setDatePickerTextView(holder.mTextViewValue, position, holder.layoutRow, formElement.getMinDate(), formElement.getMaxDate());
                    break;
                case FormElementDateTime.TYPE_PICKER_TIME:
                    if (formElement.getValue() != null) {
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        holder.mTextViewValue.setText(dateFormat.format(formElement.getValue()));
                    }
                    setTimePickerTextView(holder.mTextViewValue, position, holder.layoutRow);
                    break;
                case FormElementDateTime.TYPE_PICKER_DATE_TIME:
                    if (formElement.getValue() != null) {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                        holder.mTextViewValue.setText(dateFormat.format(formElement.getValue()));
                    }
                    setDateTimePickerTextView(holder.mTextViewValue, position, holder.layoutRow, formElement);
                    break;
                default:
                    break;
            }

            if (holder.linearLayout.getLayoutParams() != null) {
                if (!formElement.getVisibility()) {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = 0;
                    holder.linearLayout.setLayoutParams(params);
                } else {
                    ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
                    params.height = -2;
                    holder.linearLayout.setLayoutParams(params);
                }
            }

        }
    }


    private int dpToPx(int dp) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void setTokenPicker(ImageButton imgButton, final int position, final FormElementToken formElement) {
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                getTokensPickerObservable(formElement.getTokensObject()).forEach(actionTokens);
            }
        });
    }

    private void setSignaturePicker(ImageView imgView, final int position, final LinearLayout layoutRow) {
        imgView.setFocusableInTouchMode(false);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                getSignaturePickerObservable().forEach(actionSignature);
            }
        });
    }

    private void setPlaceDialogPicker(final TextView tv, final int position, final LinearLayout layoutRow, final FormElementPlaceDialog formElement) {

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                PlaceSearchDialog placeSearchDialog = new PlaceSearchDialog.Builder(formElement.getmCtx())
                        .setHintText(formElement.getDialogTitle())
                        .setNegativeText("CANCEL")
                        .setNegativeTextColor(R.color.gray)
                        .setPositiveText("SUBMIT")
                        .setPositiveTextColor(R.color.red)
                        .setLocationNameListener(new PlaceSearchDialog.LocationNameListener() {
                            @Override
                            public void locationName(String locationName) {
                                ((FormElementPlaceDialog) mDataset.get(clickedPosition)).setValue(locationName);
                                tv.setText(locationName);
                            }
                        })
                        .build();
                placeSearchDialog.show();

            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                PlaceSearchDialog placeSearchDialog = new PlaceSearchDialog.Builder(formElement.getmCtx())
                        .setHintText(formElement.getDialogTitle())
                        .setNegativeText("CANCEL")
                        .setNegativeTextColor(R.color.gray)
                        .setPositiveText("SUBMIT")
                        .setPositiveTextColor(R.color.red)
                        .setLocationNameListener(new PlaceSearchDialog.LocationNameListener() {
                            @Override
                            public void locationName(String locationName) {
                                ((FormElementPlaceDialog) mDataset.get(clickedPosition)).setValue(locationName);
                                tv.setText(locationName);
                            }
                        })
                        .build();
                placeSearchDialog.show();

            }
        });

    }


    private void setImagePickerMultiple(ImageButton imgView, final int position, final int maxImages, final LinearLayout layoutRow) {
        imgView.setFocusableInTouchMode(false);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                getImageMultiplePickerObservable(maxImages).forEach(actionMultipleImages);
            }
        });
    }

    private void setImagePicker(ImageView imgView, final int position, final LinearLayout layoutRow) {
        imgView.setFocusableInTouchMode(false);
        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                getImagePickerObservable().forEach(action);
            }
        });
    }

    private void setAttachPicker(TextView tv, final int position, final LinearLayout layoutRow) {
        tv.setFocusableInTouchMode(false);
        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                getAttachPickerObservable(attachs).forEach(actionAttach);
            }
        });
    }


    Action1<ArrayList<String>> actionAttach = new Action1<ArrayList<String>>() {
        @Override
        public void call(ArrayList<String> attach) {
            if (attach != null) {

                attachs = new ArrayList<String>();
                for (int p = 0; p < attach.size(); p++)
                    attachs.add(attach.get(p));
                ((FormElementAttach) mDataset.get(clickedPosition)).setValue(String.valueOf(attach.size()));
                ((FormElementAttach) mDataset.get(clickedPosition)).setOptions(attachs);
                notifyItemChanged(clickedPosition);
            }
        }
    };

    Action1<Bitmap> actionSignature = new Action1<Bitmap>() {
        @Override
        public void call(Bitmap images) {
            if (images != null) {
                AppTools appTools = new AppTools();
                ((FormElementSignature) mDataset.get(clickedPosition)).setValue(images);
                notifyItemChanged(clickedPosition);
            }
        }
    };

    public View createCustomToken(Context context, final int pos, final ArrayList<FormTokenObject> selectedTokens, final int posFormItem) {
        View view = View.inflate(context,R.layout.token_item,null);
        TextView tvChip = view.findViewById(R.id.tvChip);
        tvChip.setText(selectedTokens.get(pos).getValue());
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTokens.remove(selectedTokens.get(pos));
                ArrayList<TokesTags> tokensView = new ArrayList<>();
                for (int i = 0; i < selectedTokens.size(); i++) {
                    View v = createCustomToken(mContext, i, selectedTokens, clickedPosition);
                    TokesTags item = new TokesTags();
                    item.setTokenItem(v);
                    tokensView.add(item);
                }
                ((FormElementToken) mDataset.get(clickedPosition)).setValue(tokensView);
                notifyItemChanged(clickedPosition);
            }
        });
        return view;
    }

    Action1<ArrayList<FormTokenObject>> actionTokens = new Action1<ArrayList<FormTokenObject>>() {
        @Override
        public void call(ArrayList<FormTokenObject> tokens) {
            if (tokens != null) {
                ArrayList<TokesTags> tokensView = new ArrayList<>();
                for (int i = 0; i < tokens.size(); i++) {
                    View v = createCustomToken(mContext, i, tokens, clickedPosition);
                    TokesTags item = new TokesTags();
                    item.setTokenItem(v);
                    tokensView.add(item);
                }
                AppTools appTools = new AppTools();
                ((FormElementToken) mDataset.get(clickedPosition)).setValue(tokensView);
                notifyItemChanged(clickedPosition);
            }
        }
    };

    Action1<List<Image>> action = new Action1<List<Image>>() {
        @Override
        public void call(List<Image> images) {
            if (images != null) {
                AppTools appTools = new AppTools();
                Uri imageUri = Uri.fromFile(new File(images.get(0).getPath()));

                try {
                    int rotateImage = appTools.getCameraPhotoOrientation((Activity) mContext, imageUri);
                    Bitmap bmp = appTools.getThumbnail(imageUri, rotateImage, mContext);

                    Glide.with((Activity) mContext)
                            .load(appTools.bitmapToByte(bmp))
                            .asBitmap()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    ((FormElementImageView) mDataset.get(clickedPosition)).setValue(resource);
                                    notifyItemChanged(clickedPosition);
                                }
                            });
                } catch (Exception ex) {
                    String sX = ex.toString();
                    String gg = "";
                }
            }
        }
    };

    Action1<List<Image>> actionMultipleImages = new Action1<List<Image>>() {
        @Override
        public void call(List<Image> images) {
            if (images != null) {
                AppTools appTools = new AppTools();
                Uri imageUri;
                List<Bitmap> listRes = new ArrayList<Bitmap>();

                for (int i = 0; i < images.size(); i++) {
                    try {
                        imageUri = Uri.fromFile(new File(images.get(i).getPath()));
                        int rotateImage = appTools.getCameraPhotoOrientation((Activity) mContext, imageUri);
                        Bitmap bmp = appTools.getThumbnail(imageUri, rotateImage, mContext);
                        listRes.add(bmp);
                    } catch (Exception ex) {
                        String sX = ex.toString();
                        String gg = "";
                    }
                }

                ImageCardAdapter adapter = new ImageCardAdapter(mActivity, listRes);
                ((FormElementImageMultipleView) mDataset.get(clickedPosition)).setImgAdapter(adapter);
                ((FormElementImageMultipleView) mDataset.get(clickedPosition)).setValue(listRes);
                notifyItemChanged(clickedPosition);
            }
        }
    };

    private Observable<ArrayList<String>> getAttachPickerObservable(ArrayList<String> currentItems) {
        return RxAttachPicker.getInstance().start(mContext, AttachPicker.create((Activity) mContext), currentItems);
    }

    private Observable<List<Image>> getImagePickerObservable() {
        return RxImagePicker.getInstance().start(mContext, ImagePicker.create((Activity) mContext), 0);
    }

    private Observable<List<Image>> getImageMultiplePickerObservable(int maxImages) {
        return RxImagePicker.getInstance().start(mContext, ImagePicker.create((Activity) mContext), maxImages);
    }

    private Observable<Bitmap> getSignaturePickerObservable() {
        return RxSignaturePicker.getInstance().start(mContext, SignaturePicker.create((Activity) mContext));
    }

    private Observable<ArrayList<FormTokenObject>> getTokensPickerObservable(ArrayList<FormTokenObject> objectTokens) {
        return RxTokenPicker.getInstance().start(mContext, TokensPicker.create((Activity) mContext), objectTokens);
    }

    private void setEditTextFocusEnabled(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
        editText.setFocusableInTouchMode(true);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    private void setDatePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {
        editText.setFocusableInTouchMode(false);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                        date,
                        mCalendarCurrentDate.get(Calendar.YEAR),
                        mCalendarCurrentDate.get(Calendar.MONTH),
                        mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

                // this could be used to set a minimum date
                // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                // display the picker
                datePickerDialog.show();
            }
        });

    }

    private void setDatePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final Date minDate, final Date maxDate) {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                        dateTextView,
                        mCalendarCurrentDate.get(Calendar.YEAR),
                        mCalendarCurrentDate.get(Calendar.MONTH),
                        mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

                if (maxDate != null)
                    datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());

                if (minDate != null)
                    datePickerDialog.getDatePicker().setMinDate(minDate.getTime());

                datePickerDialog.show();
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                        dateTextView,
                        mCalendarCurrentDate.get(Calendar.YEAR),
                        mCalendarCurrentDate.get(Calendar.MONTH),
                        mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

                if (maxDate != null)
                    datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());

                if (minDate != null)
                    datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
                datePickerDialog.show();
            }
        });

    }

    private void setTimePicker(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {

        editText.setFocusableInTouchMode(false);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // saves clicked position for further reference
                clickedPosition = position;

                // prepares time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                        time,
                        mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
                        mCalendarCurrentDate.get(Calendar.MINUTE),
                        true);

                // display the picker
                timePickerDialog.show();
            }
        });

    }


    private void setSearchableSpinnerView(final AppCompatTextView textView, final int position, final LinearLayout layoutRow, final FormElementSearchableSpinner formElementSearchableSpinner) {

        final SearchableSpinnerCallBack ratingCallBack = formElementSearchableSpinner.getCallback();

        ArrayList<String> items = formElementSearchableSpinner.getItems();
        spinnerDialog = new SpinnerDialog(mActivity, items, formElementSearchableSpinner.getDialogTitle(), "Annulla");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                FormSpinnerObject spinnerItem = formElementSearchableSpinner.getSpinnerObject().get(position);
                textView.setText(spinnerItem.getValue());
                ratingCallBack.callbackSearchableSpinnerReturn(formElementSearchableSpinner, formElementSearchableSpinner.getTag(), spinnerItem);
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                spinnerDialog.showSpinerDialog();
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                spinnerDialog.showSpinerDialog();
            }
        });
    }


    //
    private void setDateTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow, final FormElementDateTime formElementDateTime) {
        final SwitchDateTimeDialogFragment dateTimeFragment;
        dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                "Impostazione Data e Ora",
                "Conferma",
                "Annulla"
        );

        dateTimeFragment.set24HoursMode(true);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);  // number of days to add

        final DateTimeCallBack dateTimeCallBack = formElementDateTime.getCallback();

        dateTimeFragment.setMinimumDateTime(formElementDateTime.getMinDate());
        dateTimeFragment.setMaximumDateTime(new GregorianCalendar(2050, Calendar.DECEMBER, 31).getTime());
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {


        }

        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {


                if (clickedPosition >= 0) {
                    ((FormElementDateTime) mDataset.get(clickedPosition)).setValue(date);
                    notifyItemChanged(clickedPosition);
                    clickedPosition = -1;
                    dateTimeCallBack.callbackDateTimeReturn(date, formElementDateTime, formElementDateTime.getTag());
                }

            }

            @Override
            public void onNegativeButtonClick(Date date) {


            }

            @Override
            public void onNeutralButtonClick(Date date) {


            }
        });

        dateTimeFragment.startAtCalendarView();
        dateTimeFragment.setDefaultDateTime(new Date());
        String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
        //dateTimeFragment.show(mc.getFragmentManager(), TAG_DATETIME_FRAGMENT);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                dateTimeFragment.setDefaultDateTime(((FormElementDateTime) mDataset.get(clickedPosition)).getDefaultDate());
                dateTimeFragment.show(fManager, "TAG_DATETIME_FRAGMENT");
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedPosition = position;
                dateTimeFragment.setDefaultDateTime(((FormElementDateTime) mDataset.get(clickedPosition)).getDefaultDate());
                dateTimeFragment.show(fManager, "TAG_DATETIME_FRAGMENT");
            }
        });
    }

    private void setTimePickerTextView(final TextView textView, final int position, final LinearLayout layoutRow) {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // saves clicked position for further reference
                clickedPosition = position;

                // prepares time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                        timeTextView,
                        mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
                        mCalendarCurrentDate.get(Calendar.MINUTE),
                        true);

                // display the picker
                timePickerDialog.show();
            }
        });

        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // saves clicked position for further reference
                clickedPosition = position;

                // prepares time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                        timeTextView,
                        mCalendarCurrentDate.get(Calendar.HOUR_OF_DAY),
                        mCalendarCurrentDate.get(Calendar.MINUTE),
                        true);

                // display the picker
                timePickerDialog.show();
            }
        });

    }

    private void setSingleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {

        // get the element
        final FormElement currentObj = (FormElement) mDataset.get(position);

        editText.setFocusableInTouchMode(false);

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
        for (int i = 0; i < currentObj.getOptions().size(); i++) {
            options[i] = currentObj.getOptions().get(i);
        }

        // prepare the dialog
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("Pick one")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        editText.setText(options[which]);
                        currentObj.setValue(options[which].toString());
                        notifyItemChanged(clickedPosition);
                    }
                })
                .create();

        // display the dialog on click
        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }

    private void setMultipleOptionsDialog(final AppCompatEditText editText, final int position, final LinearLayout layoutRow) {

        // get the element
        final FormElement currentObj = (FormElement) mDataset.get(position);
        editText.setFocusableInTouchMode(false);

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
        final boolean[] optionsSelected = new boolean[currentObj.getOptions().size()];
        final ArrayList<Integer> mSelectedItems = new ArrayList();

        for (int i = 0; i < currentObj.getOptions().size(); i++) {
            options[i] = currentObj.getOptions().get(i);
            optionsSelected[i] = false;

            if (currentObj.getOptionsSelected().contains(options[i])) {
                optionsSelected[i] = true;
                mSelectedItems.add(i);
            }
        }

        // prepare the dialog
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("Pick one or more")
                .setMultiChoiceItems(options, optionsSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s = "";
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            s += options[mSelectedItems.get(i)];

                            if (i < mSelectedItems.size() - 1) {
                                s += ", ";
                            }
                        }
                        editText.setText(s);
                        ((FormElement) mDataset.get(position)).setValue(s);
                        notifyItemChanged(clickedPosition);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .create();

        // display the dialog on click
        layoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layoutRow;
        public LinearLayout linearLayout;
        public AppCompatTextView mTextViewTitle;
        public Button mButtonTitle;
        public AppCompatTextView mTextViewDetail;
        public AppCompatTextView mTextViewOptions;
        public AppCompatEditText mEditTextValue;
        public AppCompatEditText mEditMemoTextValue;
        public TextView mTextViewValue;
        public Switch mEditSwitchValue;
        public CheckBox mEditCheckBoxValue;
        public ImageView mEditImageViewValue;
        public TextView mTextViewAttachValue;
        public BaseRatingBar mEditRatingValue;
        public Spinner mEditSpinnerValue;
        public SegmentedGroup mEditSegmentGroupValue;
        public FormCustomEditTextListener mFormCustomEditTextListener;
        public FormCustomEditMemoTextListener mFormCustomEditMemoTextListener;
        public RecyclerView mEditImageViewMultipleValue;
        public ImageButton btnAdd;
        public FlexboxLayout tokens;
        public ImageButton btnAddTokens;

        public ViewHolder(View v, FormCustomEditTextListener listener, int viewType, FormCustomEditMemoTextListener memoTextListener) {
            super(v);
            mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
            mTextViewOptions = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
            linearLayout = (LinearLayout) v.findViewById(R.id.itemContainer);
            layoutRow = (LinearLayout) v.findViewById(R.id.layoutRow);

            if (viewType == 1) {
                mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
                mFormCustomEditTextListener = listener;
                if (mEditTextValue != null)
                    mEditTextValue.addTextChangedListener(mFormCustomEditTextListener);
            } else if (viewType == 2) {
                mEditSwitchValue = (Switch) v.findViewById(R.id.formElementValue);
            } else if (viewType == 3) {
                mEditImageViewValue = (ImageView) v.findViewById(R.id.formElementValue);
            } else if (viewType == 9) {
                mEditImageViewValue = (ImageView) v.findViewById(R.id.formElementValue);
            } else if (viewType == 4) {
                mEditSpinnerValue = (Spinner) v.findViewById(R.id.formElementValue);
            } else if (viewType == 7) {
                mEditSegmentGroupValue = (SegmentedGroup) v.findViewById(R.id.formElementValue);
            } else if (viewType == 8) {
                mTextViewAttachValue = (TextView) v.findViewById(R.id.formElementValue);
            } else if (viewType == 10) {
                mEditRatingValue = (BaseRatingBar) v.findViewById(R.id.formElementValue);
            } else if (viewType == 5) {
                mEditMemoTextValue = (AppCompatEditText) v.findViewById(R.id.formMemoElementValue);
                mFormCustomEditMemoTextListener = memoTextListener;
                if (mEditMemoTextValue != null)
                    mEditMemoTextValue.addTextChangedListener(mFormCustomEditMemoTextListener);
            } else if (viewType == 11) {
                mEditImageViewMultipleValue = (RecyclerView) v.findViewById(R.id.formElementValue);
                btnAdd = (ImageButton) v.findViewById(R.id.btnAdd);
            } else if (viewType == 12) {
                mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
            } else if (viewType == 13) {
                mTextViewValue = (TextView) v.findViewById(R.id.formElementTextViewValue);
            } else if (viewType == 14) {
                mTextViewDetail = (AppCompatTextView) v.findViewById(R.id.formElementValue);
            } else if (viewType == 15) {
                mButtonTitle = (Button) v.findViewById(R.id.btnAction);
            } else if (viewType == 16) {
                mEditCheckBoxValue = (CheckBox) v.findViewById(R.id.formElementValue);
            } else if (viewType == 17) {
                mTextViewValue = (TextView) v.findViewById(R.id.formElementTextViewValue);
            } else if (viewType == 18) {
                tokens = (FlexboxLayout) v.findViewById(R.id.tokens);
                btnAddTokens = (ImageButton) v.findViewById(R.id.btnAddTokens);
            }

        }

    }


    private class FormCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            FormElement formElement = (FormElement) mDataset.get(position);
            formElement.setValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }


    private class FormCustomEditMemoTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            FormElementMemo formElement = (FormElementMemo) mDataset.get(position);
            formElement.setValue(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendarCurrentDate.set(Calendar.YEAR, year);
            mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
            mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormatDate = "dd/MM/yy"; // custom format
            SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

            // act only if clicked position is a valid index
            if (clickedPosition >= 0) {
                ((FormElement) mDataset.get(clickedPosition)).setValue(sdfDate.format(mCalendarCurrentDate.getTime()));
                notifyItemChanged(clickedPosition);
                clickedPosition = -1;
            }
        }

    };


    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = "HH:mm"; // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.getDefault());

            // act only if clicked position is a valid index
            if (clickedPosition >= 0) {
                ((FormElement) mDataset.get(clickedPosition)).setValue(sdfTime.format(mCalendarCurrentTime.getTime()));
                notifyItemChanged(clickedPosition);
                clickedPosition = -1;
            }
        }
    };

    DatePickerDialog.OnDateSetListener dateTextView = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendarCurrentDate.set(Calendar.YEAR, year);
            mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
            mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormatDate = "dd/MM/yy"; // custom format
            SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

            // act only if clicked position is a valid index
            if (clickedPosition >= 0) {
                ((FormElementDateTime) mDataset.get(clickedPosition)).setValue(mCalendarCurrentDate.getTime());
                notifyItemChanged(clickedPosition);
                clickedPosition = -1;
            }
        }

    };


    TimePickerDialog.OnTimeSetListener timeTextView = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = "HH:mm"; // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.getDefault());

            // act only if clicked position is a valid index
            if (clickedPosition >= 0) {
                ((FormElementDateTime) mDataset.get(clickedPosition)).setValue(mCalendarCurrentTime.getTime());
                notifyItemChanged(clickedPosition);
                clickedPosition = -1;
            }
        }
    };

}