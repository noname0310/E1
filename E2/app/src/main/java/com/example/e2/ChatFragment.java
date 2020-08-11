package com.example.e2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.e2.databinding.ActivityFragmentChatBinding;
import com.example.e2.databinding.ActivityFragmentMemoBinding;

public class ChatFragment extends Fragment {

    private android.content.Context Context;
    private ActivityFragmentChatBinding Binding;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        Context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_chat, container, false);

        return Binding.getRoot();
    }
}
