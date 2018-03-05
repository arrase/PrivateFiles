package arrase.privatefiles.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import arrase.privatefiles.R;
import arrase.privatefiles.constants.SecureFilesConstants;
import arrase.privatefiles.dialogs.SelectSourceDialog;

/**
 * A placeholder fragment containing a simple view.
 */
public class VolumeListFragment extends Fragment {

    private Boolean isFabOpen = false;
    private FloatingActionButton create_fab, import_fab, add_fab;
    private Animation fab_open, fab_close;
    private Context mContext;
    private OnFragmentInteractionListener mListener;

    public VolumeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.volume_list, container, false);

        add_fab = (FloatingActionButton) v.findViewById(R.id.add_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        create_fab = (FloatingActionButton) v.findViewById(R.id.create_fab);
        create_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Bundle arguments = new Bundle();
                arguments.putString(
                        SecureFilesConstants.VOLUME_ACTION, SecureFilesConstants.VOLUME_CREATE
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.show(getFragmentManager(), "");
            }
        });

        import_fab = (FloatingActionButton) v.findViewById(R.id.import_fab);
        import_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                Bundle arguments = new Bundle();
                arguments.putString(
                        SecureFilesConstants.VOLUME_ACTION, SecureFilesConstants.VOLUME_IMPOT
                );

                SelectSourceDialog dialog = new SelectSourceDialog();
                dialog.setArguments(arguments);
                dialog.show(getFragmentManager(), "SelectSource");
            }
        });

        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);

        return v;
    }

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mContext = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
}
