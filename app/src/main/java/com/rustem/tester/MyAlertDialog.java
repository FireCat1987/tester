package com.rustem.tester;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by sbt-sabitov-rz on 26.09.2017.
 */

public class MyAlertDialog extends DialogFragment {
    private AlertDialogListener mListener = null;

    public MyAlertDialog() {
    }

    public interface AlertDialogListener {
        void onPositiveClick();
        void onNegativeClick();
    }

    public static MyAlertDialog newInstance(int totalGuesses) {
        MyAlertDialog frag = new MyAlertDialog();
        Bundle args = new Bundle();
        args.putInt("totalGuesses", totalGuesses);
        frag.setArguments(args);
        return frag;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        int mTotalGuesses = getArguments().getInt("totalGuesses", 0);

        android.app.AlertDialog.Builder builder =
                new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.results,
                        mTotalGuesses,
                        (1000 / (double) mTotalGuesses)));

        builder.setPositiveButton(R.string.reset_quiz,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        mListener.onPositiveClick();
                    }
                }
        );

        builder.setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        mListener.onNegativeClick();
                    }
                }
        );
        return builder.create();
    }
}
