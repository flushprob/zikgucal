package com.inshikchung.zikgucal.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.inshikchung.zikgucal.R;

import java.net.URL;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    EditText edtWeight, edtPurchase, edtRate;
    TextView tvResult, tvOverpay, tvShipping;
    Spinner spinnerKind;
    Button calBtn;
    double exchangeRate = 1234.56;
    double taxRate = 0;
    double shippingAdd=0;
    int shippingBase = 12000;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        spinnerKind = (Spinner) view.findViewById(R.id.productSpinner);

        calBtn = (Button) view.findViewById(R.id.calBtn);
        edtPurchase = (EditText) view.findViewById(R.id.edtPurchase);
        edtWeight = (EditText) view.findViewById(R.id.edtWeight);
        edtRate = (EditText) view.findViewById(R.id.edtRate);

        tvResult = (TextView) view.findViewById(R.id.tvResult);
        tvOverpay = (TextView) view.findViewById(R.id.tvOverpay);
        tvShipping = (TextView) view.findViewById(R.id.tvShipping);

        edtRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                exchangeRate = Double.parseDouble(edtRate.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                shippingAdd = Double.parseDouble(edtWeight.getText().toString());
                shippingAdd = Math.ceil(shippingAdd*2)/2;
                int shipAdd = (int) Math.ceil(1500*shippingAdd);
                shippingBase += shipAdd;


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        ArrayAdapter kindAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.kindArray, android.R.layout.simple_spinner_item);
        kindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKind.setAdapter(kindAdapter);

        spinnerKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        taxRate = 1;
                        break;
                    case 1:
                        taxRate = 1.1;
                        break;
                    case 2:
                        taxRate = 1.1;
                        break;
                    case 3:
                        taxRate = 1.1;
                        break;
                    case 4:
                        taxRate = 1.0;
                        break;
                    case 5:
                        taxRate = 1.0;
                        break;
                    case 6:
                        taxRate = 1.1;
                        break;
                    case 7:
                        taxRate = 1.165;
                        break;
                    case 8:
                        taxRate = 1.18;
                        break;
                    case 9:
                        taxRate = 1.18;
                        break;
                    case 10:
                        taxRate = 1.18;
                        break;
                    default:
                        break;



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double pAmount, pWeight, pResult = 0, taxResult, pKind;
                int result;

                pAmount = (Math.round(Double.parseDouble(edtPurchase.getText().toString())*100)/100.0);

                pResult = pAmount * exchangeRate * taxRate;

                taxResult = pResult - (pAmount *exchangeRate);

                result = (int) (Math.round(taxResult));

                pResult = (Math.round(pResult)) + shippingBase;

                tvOverpay.setText(""+result);
                tvOverpay.setTextColor(Color.RED);

                if(pAmount<=150) {

                    tvOverpay.setText("150달러 이하 면세");
                }



                Toast.makeText(getActivity(), "계산완료", Toast.LENGTH_SHORT).show();

                tvShipping.setText(""+shippingBase);
                tvResult.setText(""+pResult);
                shippingBase = 12000;

            }
        });









        return view;
    }
}

