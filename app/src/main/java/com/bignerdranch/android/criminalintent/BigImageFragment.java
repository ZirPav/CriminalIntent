package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

public class BigImageFragment extends DialogFragment implements View.OnClickListener {

    private static final String ARG_FILEPATH = "filepath";

    private ImageView mBigImage;

    public Dialog onCreateDialog(Bundle savedInstanceState){
        String filePath = getArguments().getString(ARG_FILEPATH);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_big_image, null);

        mBigImage = v.findViewById(R.id.bigImage);
        mBigImage.setOnClickListener(this);
        Bitmap bitmap = PictureUtils.getScaledBitmapFull(filePath, getActivity());
        mBigImage.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
    @Override
    public void onClick(View view) {
        dismiss();
    }

    public static BigImageFragment newInstance(String filePath){
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, filePath);
        BigImageFragment fragment = new BigImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
