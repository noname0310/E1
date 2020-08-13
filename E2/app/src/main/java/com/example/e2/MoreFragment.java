package com.example.e2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.e2.databinding.ActivityFragmentChatBinding;
import com.example.e2.databinding.ActivityFragmentMoreBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class MoreFragment extends Fragment {

    private android.content.Context Context;
    private ActivityFragmentMoreBinding Binding;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

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

        Binding.setUser(UserCache.getUser(Context));

        Binding.btnMoreLogout.setOnClickListener(view -> logout());

        Binding.btnMoreReset.setOnClickListener(foo -> {
            UserCache.getUser(Context).getEmail();
            Toast.makeText(Context, "재설정 이메일이 전송되었습니다", Toast.LENGTH_SHORT).show();
            logout();
        });

        Binding.btnMoreDelete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Context);
            builder.setTitle("회원탈퇴");
            builder.setMessage("정말로 회원 탈퇴 하시겠습니까?");
            builder.setPositiveButton("탈퇴하기", (dialogInterface, i) -> {
                try {
                    firebaseAuth.getCurrentUser().delete().addOnSuccessListener(runnable -> {
                        firebaseFirestore
                                .collection("users")
                                .document(UserCache.getUser(Context).getEmail())
                                .delete()
                                .addOnSuccessListener(runnable1 -> {
                                    Toast.makeText(Context, "정상적으로 탈퇴되었습니다", Toast.LENGTH_SHORT).show();
                                    logout();
                                })
                                .addOnFailureListener(e1 -> Toast.makeText(Context, e1.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
                    })
                    .addOnFailureListener(e -> Toast.makeText(Context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    Toast.makeText(Context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("취소", (dialogInterface, i) -> {});
            builder.show();
        });

        return Binding.getRoot();
    }

    private void logout() {
        UserCache.clear(Context);
        firebaseAuth.signOut();
        startActivity(new Intent(Context, login.class));
        try {
            getActivity().finish();
        } catch (Exception e) {
            Toast.makeText(Context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
