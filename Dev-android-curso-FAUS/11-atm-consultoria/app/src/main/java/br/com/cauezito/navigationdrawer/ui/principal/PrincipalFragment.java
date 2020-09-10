package br.com.cauezito.navigationdrawer.ui.principal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import br.com.cauezito.navigationdrawer.R;

public class PrincipalFragment extends Fragment {

    private PrincipalViewModel principalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        principalViewModel =
                ViewModelProviders.of(this).get(PrincipalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_principal, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        principalViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}