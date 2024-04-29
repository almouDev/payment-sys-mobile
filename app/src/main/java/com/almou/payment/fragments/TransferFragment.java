package com.almou.payment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.almou.payment.R;
import com.almou.payment.activities.MainActivity;
import com.almou.payment.utils.MainUtilities;

public class TransferFragment extends Fragment implements View.OnClickListener {
    private Double amount;
    private EditText amountEditText;
    private EditText destinationEditText;
    private Button submit;

    public TransferFragment() {
        // Required empty public constructor
    }
    private Boolean IsAccountExist(String phone){
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        amountEditText=view.findViewById(R.id.amount);
        destinationEditText=view.findViewById(R.id.destination);
        submit=view.findViewById(R.id.amount_submit_btn);
        submit.setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        try {
            Float amount=Float.parseFloat(amountEditText.getText().toString());
            String destination=destinationEditText.getText().toString();
            if (!MainUtilities.validatePhoneNumber(destination)){
                MainUtilities.showAlert(getContext(),"The destination account should be a valid phone number");
                return;
            }
            if (!IsAccountExist(destination)){
                MainUtilities.showAlert(getContext(),"The destination account doesn't exist");
                return;
            }
            NavController navController= Navigation.findNavController(view);
            navController.navigate(R.id.nav_confirm_transfer);
        }catch (NumberFormatException exception){
            MainUtilities.showAlert(getContext(),"Invalid amount specified");
        }
    }
}