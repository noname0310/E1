package com.example.e2;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class BindingOptions {

    @BindingAdapter("memoItem")
    public static void bindMemoItem(RecyclerView recyclerView, ObservableArrayList<MemoModel> items) {
        MemoAdapter adaptor = (MemoAdapter)recyclerView.getAdapter();
        if (adaptor != null) adaptor.setItem(items);
    }
}
