package arrase.privatefiles.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import arrase.privatefiles.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PrivateFilesActivityFragment extends Fragment {

    public PrivateFilesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_private_files, container, false);
    }
}
