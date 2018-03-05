package arrase.privatefiles.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import arrase.privatefiles.R;
import arrase.privatefiles.constants.PrivateFilesConstants;
import arrase.privatefiles.dialogs.SelectSourceDialog;


public class VolumeListFragment extends Fragment {

    private Boolean isFabOpen = false;
    private FloatingActionButton create_fab, import_fab, add_fab;
    private Animation fab_open, fab_close;
    private Context mContext;
    private String volumeAction;

    public VolumeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.volume_list, container, false);

        // Main fab
        add_fab = (FloatingActionButton) v.findViewById(R.id.add_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        // Create new volume fab
        create_fab = (FloatingActionButton) v.findViewById(R.id.create_fab);
        create_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                volumeAction = PrivateFilesConstants.VOLUME_CREATE;

                Bundle arguments = new Bundle();
                arguments.putString(
                        PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG_TITLE, getString(R.string.create_volume)
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.setTargetFragment(VolumeListFragment.this, PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG);
                dialog.show(getFragmentManager(), "");
            }
        });

        // Import existing volume fab
        import_fab = (FloatingActionButton) v.findViewById(R.id.import_fab);
        import_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                volumeAction = PrivateFilesConstants.VOLUME_IMPOT;

                Bundle arguments = new Bundle();
                arguments.putString(
                        PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG_TITLE, getString(R.string.import_volume)
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.setTargetFragment(VolumeListFragment.this, PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG);
                dialog.show(getFragmentManager(), "SelectSource");
            }
        });

        // Load animations
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);

        return v;
    }

    // Run fab animation
    private void animateFAB() {

        if (isFabOpen) {

            create_fab.startAnimation(fab_close);
            import_fab.startAnimation(fab_close);
            create_fab.setClickable(false);
            import_fab.setClickable(false);
            isFabOpen = false;

        } else {

            create_fab.startAnimation(fab_open);
            import_fab.startAnimation(fab_open);
            create_fab.setClickable(true);
            import_fab.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String storage_type = bundle != null ? bundle.getString(PrivateFilesConstants.STORAGE_TYPE) : null;
                }
                break;
        }
    }
}
