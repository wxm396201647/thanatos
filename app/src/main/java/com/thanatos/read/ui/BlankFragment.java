package com.thanatos.read.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thanatos.read.R;

/**
 * Created by wang.ming5 on 2018/4/8.
 */

public class BlankFragment extends Fragment {
    public BlankFragment() {
    }

    public static BlankFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.pager_text);
        textView.setText(getArguments().getString("text"));

    }

}
