package com.inshikchung.zikgucal.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.inshikchung.zikgucal.R;

public class SlideshowFragment extends Fragment {


    EditText edtWidth, edtHeight, edtDepth;
    TextView tvWeight, tvKg;
    Button weightBtn;
    double width, height, depth, result, kgResult;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);*/
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
          //      textView.setText(s);
            }
        });

        edtWidth = (EditText) rootView.findViewById(R.id.edtWidth);
        edtHeight = (EditText) rootView.findViewById(R.id.edtHeight);
        edtDepth = (EditText) rootView.findViewById(R.id.edtDepth);

        tvWeight = (TextView) rootView.findViewById(R.id.tvWeight);
        tvKg = (TextView) rootView.findViewById(R.id.tvKg);


        weightBtn = (Button) rootView.findViewById(R.id.weightBtn);

        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                width = Double.parseDouble(edtWidth.getText().toString());
                height = Double.parseDouble(edtHeight.getText().toString());
                depth = Double.parseDouble(edtDepth.getText().toString());

                result = Math.round(((( width * height * depth ) /166)*100)/100.0) ;

               // result = Math.round(((( width * height * depth ) /6000)*100)/100.0) ;



                result = Math.ceil(result *2)/2;

                tvWeight.setText(""+result);
                kgResult = result;
                //kgResult = Math.round(((result * 0.4539237)*100)/100.0);
                kgResult = (result * 0.4539237);
                kgResult = (float) (Math.ceil(kgResult * 2)/2);

                tvKg.setText(""+kgResult);
            }
        });





        return rootView;
    }
}
