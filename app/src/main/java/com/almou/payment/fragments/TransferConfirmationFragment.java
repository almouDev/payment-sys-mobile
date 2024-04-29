package com.almou.payment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.almou.payment.R;

public class TransferConfirmationFragment extends DialogFragment implements View.OnClickListener {
    String amount;
    String transaction_id;
    private Button confirm_btn;
    private Button confirm_later_btn;
    public TransferConfirmationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            amount=getArguments().getString("amount");
            transaction_id=getArguments().getString("TransactionID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.confirmation_transfer_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textView=view.findViewById(R.id.transaction_id);
        TextView amountTex=view.findViewById(R.id.amount);
        confirm_btn=view.findViewById(R.id.tr_confirmation_btn);
        confirm_later_btn=view.findViewById(R.id.confirm_later);
        confirm_btn.setOnClickListener(this::onClick);
        confirm_later_btn.setOnClickListener(this);
        textView.setText(textView.getText().toString()+" "+transaction_id);
        amountTex.setText(amountTex.getText().toString()+" "+amount+" MAD");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_later:
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.nav_home);
                break;
            case R.id.confirm_btn:
        }
    }
}