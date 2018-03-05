package arrase.privatefiles.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import arrase.privatefiles.R;
import arrase.privatefiles.constants.PrivateFilesConstants;


public class SelectSourceDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(arguments.getString(PrivateFilesConstants.SELECT_STORAGE_TYPE_DIALOG_TITLE));
        builder.setItems(R.array.volume_sources, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent();
                switch (which) {
                    case 1:
                        i.putExtra(PrivateFilesConstants.STORAGE_TYPE, PrivateFilesConstants.STORAGE_INTERNAL);
                        break;
                    case 2:
                        i.putExtra(PrivateFilesConstants.STORAGE_TYPE, PrivateFilesConstants.STORAGE_SDCARD);
                        break;
                    case 3:
                        i.putExtra(PrivateFilesConstants.STORAGE_TYPE, PrivateFilesConstants.STORAGE_GDRIVE);
                        break;
                }

                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, i);

                dismiss();
            }
        });

        return builder.create();
    }
}
