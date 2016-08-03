package com.selfscore.selfscoreapp.Utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


/**
 * Created by anshilbhansali on 8/3/16.
 */
public class ShareDialog extends android.support.v4.app.DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Share on");

        String[] arr = {"Facebook", "Twitter"};
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which == 0)
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Shared on Facebook", Snackbar.LENGTH_SHORT).show();
                else if(which == 1)
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Shared on Twitter", Snackbar.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        return builder.create();
    }

}

