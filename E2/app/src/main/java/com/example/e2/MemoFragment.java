package com.example.e2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.e2.databinding.ActivityFragmentMemoBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Locale;

public class MemoFragment extends Fragment {

    private android.content.Context Context;
    private ActivityFragmentMemoBinding Binding;
    private ObservableArrayList<MemoModel> items = new ObservableArrayList<>();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static MemoFragment newInstance() {
        return new MemoFragment();
    }

    @Override
    public void onAttach(@NonNull android.content.Context context) {
        super.onAttach(context);
        Context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_memo, container, false);

        Binding.recyclerMemo.setLayoutManager(new LinearLayoutManager(Context, LinearLayoutManager.VERTICAL, false));

        MemoAdapter adapter = new MemoAdapter();
        Binding.recyclerMemo.setAdapter(adapter);

        adapter.setOnItemClickListener((view, item) -> {
            Intent intent = new Intent(Context, MemoActivity.class);
            intent.putExtra("memo_text", item.getText());
            intent.putExtra("is_edit", true);
            startActivity(intent);
        });

        adapter.setonItemLongClickListener((view, item) -> {
            return true;
        });

        Binding.setItems(items);
        getMemos();

        Binding.fabMemo.setOnClickListener(view -> startActivity(new Intent(Context, MemoActivity.class)));

        return Binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMemos();
    }

    private void getMemos() {
        items.clear();
        firebaseFirestore
                .collection("memo")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot d :queryDocumentSnapshots) {
                        items.add(d.toObject(MemoModel.class));
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm aa", Locale.ENGLISH);
                    Collections.sort(items, (memoModel, t1) -> {
                        try {
                            return format.parse(t1.getTime()).compareTo(format.parse(memoModel.getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    });
                })
                .addOnFailureListener(e -> Toast.makeText(Context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
    }
}