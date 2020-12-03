package com.inshikchung.zikgucal.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.inshikchung.zikgucal.R;

public class GalleryFragment extends Fragment {

    Button fast2Btn, tailBtn, zipBtn, amazonBtn, bestBtn, ebayBtn;



    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               /* textView.setText(s);*/
            }
        });

        fast2Btn = (Button) root.findViewById(R.id.fast2Btn);
        tailBtn = (Button) root.findViewById(R.id.tailBtn);
        zipBtn = (Button) root.findViewById(R.id.zipBtn);
        amazonBtn = (Button) root.findViewById(R.id.amazonBtn);
        bestBtn = (Button) root.findViewById(R.id.bestBtn);
        ebayBtn = (Button) root.findViewById(R.id.ebayBtn);

        fast2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://2fasts.com"));
                startActivity(intent);
            }
        });

        tailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://post.malltail.com"));
                startActivity(intent);
            }
        });

        zipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ohmyzip.com"));
                startActivity(intent);
            }
        });

        amazonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amazon.com"));
                startActivity(intent);
            }
        });

        bestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bestbuy.com"));
                startActivity(intent);
            }
        });

        ebayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ebay.com"));
                startActivity(intent);
            }
        });
        return root;





    }
}
