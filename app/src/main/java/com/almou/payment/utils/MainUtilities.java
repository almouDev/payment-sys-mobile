package com.almou.payment.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.almou.payment.R;
import com.almou.payment.beans.Transaction;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainUtilities {
    public static String serverHost="http://192.168.1.4:8080";
    public static String authenticatedUser="0713158709";
    public static boolean validatePhoneNumber(String phoneNumber){
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public static void showAlert(Context context,String message){
        Context context1= context.getApplicationContext();
        LayoutInflater inflater=LayoutInflater.from(context1);
        LinearLayout layout= (LinearLayout) inflater.inflate(R.layout.alert_layout,null);
        TextView ms=layout.findViewById(R.id.alert_message);
        ms.setText(message);
        Dialog dialog=new Dialog(context);
        dialog.setContentView(layout);
        Button button=layout.findViewById(R.id.dismiss_alert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static List<Transaction> getData() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction deposit = new Transaction();
        deposit.setAmount(400.0);
        deposit.setDate(new Date());
        deposit.setType("Deposit");
        deposit.setTransaction_id(UUID.randomUUID());

        Transaction transfer = new Transaction();
        transfer.setAmount(200.0);
        transfer.setType("Transfer");
        transfer.setReceiver("0624242402");
        transfer.setSender("0713158709");
        transfer.setDate(new Date());
        transfer.setTransaction_id(UUID.randomUUID());

        Transaction receive = new Transaction();
        receive.setSender("0624242502");
        receive.setReceiver("0713158709");
        receive.setAmount(400.0);
        receive.setDate(new Date());
        receive.setType("Transfer");
        receive.setTransaction_id(UUID.randomUUID());
        transactions.add(deposit);
        transactions.add(receive);
        transactions.add(transfer);
        return transactions;
    }
    public static ResponseBody getResource(String url){
        return null;
    }
}
