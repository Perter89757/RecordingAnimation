package com.yiguo.recordinganimation.ActivityKiller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguo.recordinganimation.R;

/**
 * author: huang_yanhui
 * data:2017/9/21
 * time:16:01
 * emaill:huangyh@thinkive.com
 * description:
 */

public class EditNameDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        return view;
    }
}
