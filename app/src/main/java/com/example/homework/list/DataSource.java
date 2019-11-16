package com.example.homework.list;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


public class DataSource {

    private final List<MyData> mData;

    private static DataSource sInstance;

    private int[] mColors = new int[]{
            Color.RED,
            Color.BLUE
    };

    public DataSource() {
        final int INITIAL_NUMBER = 100;

        mData = new ArrayList<>();

        for (int i = 0; i < INITIAL_NUMBER; i++) {
            mData.add(new MyData(i, mColors[i % mColors.length]));
        }
    }

    public List<MyData> getData() {
        return mData;
    }

    public void setData() {
        int dataSize = mData.size();
        mData.add(new MyData(dataSize, mColors[dataSize % mColors.length]));
    }

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public static class MyData {

        public MyData(int number, int color) {
            mNumber = number;
            mColor = color;
        }

        public int mNumber;
        public int mColor;
    }
}