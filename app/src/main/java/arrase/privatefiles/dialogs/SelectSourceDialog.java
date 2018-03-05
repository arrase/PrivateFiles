package arrase.privatefiles.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import arrase.privatefiles.R;
import arrase.privatefiles.constants.SecureFilesConstants;


public class SelectSourceDialog extends DialogFragment {

    String storage_type;
    String action;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title;
        Bundle arguments = getArguments();
        action = arguments.getString(SecureFilesConstants.VOLUME_ACTION);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (action != null && action.equals(SecureFilesConstants.VOLUME_CREATE)) {
            title = getString(R.string.create_volume);
        } else {
            title = getString(R.string.import_volume);
        }

        builder.setTitle(title)
                .setItems(R.array.volume_sources, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                storage_type = SecureFilesConstants.STORAGE_INTERNAL;
                                break;
                            case 2:
                                storage_type = SecureFilesConstants.STORAGE_SDCARD;
                                break;
                            case 3:
                                storage_type = SecureFilesConstants.STORAGE_GDRIVE;
                                break;
                        }

                    }
                });

        return builder.create();
    }
}
