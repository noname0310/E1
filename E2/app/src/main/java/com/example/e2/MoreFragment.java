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
import com.example.e2.databinding.ActivityFragmentMoreBinding;

public class MoreFragment extends Fragment {

    private android.content.Context Context;
    private ActivityFragmentMoreBinding Binding;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        Context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_more, container, false);

        return Binding.getRoot();
    }
}
