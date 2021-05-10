package com.zkl.eventbusdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.greenrobot.eventbus.EventBus;

public class PublisherDialogFragment extends DialogFragment {

    private static final String TAG = "PublisherDialogFragment";
    private OnEventListener mListener;

    public interface OnEventListener {
        void OnSuccess();

        void onFailure();
    }

    public void setEventListener(OnEventListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Publisher");
        final String[] items = {"Success", "Failure"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //success
//                        if (mListener != null){
//                            mListener.OnSuccess();
//                        }

//                        mListener = (OnEventListener) getActivity();
//                        mListener.OnSuccess();

                        EventBus.getDefault().post(new SuccessEvent(1, "user1"));
                        break;
                    case 1:
                        //failure
//                        if (mListener != null){
//                            mListener.onFailure();
//                        }

//                        mListener = (OnEventListener) getActivity();
//                        mListener.onFailure();

                        EventBus.getDefault().post(new FailureEvent());
                        break;

                }

            }
        });
        return builder.create();
    }
}
