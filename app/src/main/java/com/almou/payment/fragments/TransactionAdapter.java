package com.almou.payment.fragments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.almou.payment.R;
import com.almou.payment.beans.Transaction;
import com.almou.payment.utils.MainUtilities;

import java.sql.Date;
import java.util.List;
public class TransactionAdapter extends ArrayAdapter<Transaction> {
    private int resource;
    public TransactionAdapter(Context context,int resource, List<Transaction> objects) {
        super(context, resource, objects);
        this.resource=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        Transaction item = getItem(position);
        TextView type = convertView.findViewById(R.id.transaction_type);
        TextView amount = convertView.findViewById(R.id.amount);
        TextView date=convertView.findViewById(R.id.date);
        TextView transaction_id=convertView.findViewById(R.id.transaction_id);
        String type_string=item.getType();
        if (type_string.equals("Transfer")){
            type_string+= MainUtilities.authenticatedUser.equals(item.getSender())?" : To "+item.getReceiver():" : From "+item.getSender();
        }
        type.setText(type_string);
        amount.setText(amount.getText().toString()+item.getAmount()+" MAD");
        date.setText(date.getText().toString()+" "+item.getDate().toString());
        transaction_id.setText(transaction_id.getText().toString()+" "+item.getTransaction_id());
        return convertView;
    }
}

