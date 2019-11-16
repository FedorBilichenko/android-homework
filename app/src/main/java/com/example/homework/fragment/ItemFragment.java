package com.example.homework.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homework.R;


public class ItemFragment extends BaseFragment {

    private static final String NUMBER_ITEM = "number";
    private static final String COLOR_ITEM = "color";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String number = "0";
        int color = Color.BLUE;
        Bundle arguments = getArguments();
        if (arguments != null) {
            number = arguments.getString(NUMBER_ITEM);
            color = arguments.getInt(COLOR_ITEM);
        }
        TextView textView = view.findViewById(R.id.fragment_item_text);
        textView.setText(number);
        view.setBackgroundColor(color);
    }

    public static ItemFragment newInstance(int number, int color) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NUMBER_ITEM, String.valueOf(number));
        bundle.putInt(COLOR_ITEM, color);
        fragment.setArguments(bundle);
        return fragment;
    }
}
