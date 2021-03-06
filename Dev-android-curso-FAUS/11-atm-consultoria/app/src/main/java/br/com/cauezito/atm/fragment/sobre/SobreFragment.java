package br.com.cauezito.atm.fragment.sobre;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.cauezito.atm.R;

public class SobreFragment extends Fragment {
    SobreViewModel sobreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sobreViewModel =
                ViewModelProviders.of(this).get(SobreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sobre, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        sobreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}