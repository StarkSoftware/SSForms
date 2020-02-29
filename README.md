# SSForms

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/AAlessandroni) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ssForms-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6784)

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)

SSForms is Android library to create dynamic Recyclerview forms.
The goal of the library is to get the same power of hand-made forms but spending 1/10 of the time.

## Installation
Add this in your app's **build.gradle** file:
```
repositories {
	mavenCentral() 
	maven { 
		url "https://starksoftware.github.io/SSForms"
	}
}
```

```
dependencies {
// ...
    compile 'it.starksoftware:ssForm:1.1.0'
// ...
}
```

# Screenshot

|    Main Form     |    Main Form |   DateTime Picker |
| ------------- |:-------------:|:-------------:|
| <img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_9.png" width="324" height="576"/>|<img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_5.png" width="324" height="576"/> | <img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_1.png" width="324" height="576"/> |

|    Place Dialog     |    TokenBox |   Token Items |
| ------------- |:-------------:|:-------------:|
| <img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_6.png" width="324" height="576"/>|<img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/token_main.png" width="324" height="576"/> | <img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/token_items.png" width="324" height="576"/> |

|    Gallery Picker    |    Signature Pad|   Search Listview |
| ------------- |:-------------:|:-------------:|
|<img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_2.png" width="324" height="576"/> | <img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_7.png" width="324" height="576"/>|<img src="https://github.com/StarkSoftware/SSForms/blob/master/screenshot/image_4.png" width="324" height="576"/> | 


# Features
FormElementBasic - Simple text input form

FormElementAttach - Add attach from Android filesystem

FormElementButton - Add button to your form

FormElementDateTime - Date and Time picker in same dialog

FormElementTime - Add a time field 

FormElementDate - Date field

FormElementImageMultipleView - Multiple images picker

FormElementImageView - Single image picker

FormElementMemo - Multiline text

FormElementRating - Add rating star

FormElementSearchableSpinner - A dialog listview with search feature

FormElementSegment - A segment like iOS

FormElementSignature - Signature field

FormElementSpinner - Spinner combobox

FormElementSwitch - Switch

FormElementCheckBox - Checkbox field

FormElementPlaceDialog - Dialog for get location (Google Place API required)

FormElementToken - Token Box

FormElementDateSwitcher - Date Switcher

FormElementInputLayout - InputLayout

FormElementProfileView - ProfileView;

FormElementSmileRating - SmileRating;


# License
The library is available as open source under the terms of the GNU General Public License v3.0
