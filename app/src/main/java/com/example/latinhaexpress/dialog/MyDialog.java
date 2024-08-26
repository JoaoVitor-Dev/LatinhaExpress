package com.example.latinhaexpress.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.latinhaexpress.views.MainActivity;

public class MyDialog extends DialogFragment
{
    private String title;
    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private DialogInterface.OnClickListener positiveClickListener;
    private DialogInterface.OnClickListener negativeClickListener;

    public static MyDialog newInstance(String title, String message) {
        MyDialog fragment = new MyDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            title = getArguments().getString("title");
            message = getArguments().getString("message");
        }

        return new androidx.appcompat.app.AlertDialog.Builder(requireActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveClickListener)
                .setNegativeButton(negativeButtonText, negativeClickListener)
                .create();
    }

    public MyDialog setPositiveButton(String text, DialogInterface.OnClickListener listener) {
        positiveButtonText = text;
        positiveClickListener = listener;
        return this;
    }

    public MyDialog setNegativeButton(String text, DialogInterface.OnClickListener listener) {
        negativeButtonText = text;
        negativeClickListener = listener;
        return this;
    }
}
