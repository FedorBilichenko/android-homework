package com.example.homework.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.list.DataSource;

import java.util.List;

public class ListFragment extends BaseFragment {
    private MyDataAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.list);
        Button button = view.findViewById(R.id.add_btn);

        mAdapter = new MyDataAdapter(DataSource.getInstance().getData());

        final int columnsNumber = getResources().getInteger(R.integer.columns_number);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columnsNumber, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(mAdapter);

        button.setOnClickListener(v -> {
            int nextNumber = mAdapter.getItemCount();
            int color = (nextNumber % 2) == 0 ? Color.RED : Color.BLUE;
                    mAdapter.mData.add(
                            nextNumber,
                            new DataSource.MyData(
                                    nextNumber,
                                    color));
                mAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(nextNumber - 1);
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private static final int TYPE_FIRST = 0;
        private static final int TYPE_SECOND = 1;
        List<DataSource.MyData> mData;

        public MyDataAdapter(List<DataSource.MyData> data) {
            mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ListActivity", "onCreateViewHolder " + viewType);
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataSource.MyData data = mData.get(position);
            holder.mTextView.setText(String.valueOf(data.mNumber));
            holder.mTextView.setBackgroundColor(data.mColor);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position % 2 == 0) ? TYPE_FIRST : TYPE_SECOND;
        }
        }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.list_item_text);
            mTextView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                DataSource.MyData myData = mAdapter.mData.get(pos);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment, ItemFragment.newInstance(myData.mNumber, myData.mColor))
                        .addToBackStack(null)
                        .commit();
            });
        }
    }
}
