package arrase.privatefiles.fragments;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import arrase.privatefiles.R;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.privatefiles_settings);
    }
}
