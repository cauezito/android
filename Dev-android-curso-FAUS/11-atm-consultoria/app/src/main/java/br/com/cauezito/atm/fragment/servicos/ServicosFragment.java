package br.com.cauezito.atm.fragment.servicos;

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

import br.com.cauezito.atm.R;

public class ServicosFragment extends Fragment {

    private ServicosViewModel servicosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        servicosViewModel =
                ViewModelProviders.of(this).get(ServicosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_servicos, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        servicosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}