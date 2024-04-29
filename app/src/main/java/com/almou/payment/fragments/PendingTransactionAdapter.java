package com.almou.payment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.almou.payment.R;
import com.almou.payment.beans.Transaction;

import java.util.List;
import java.util.UUID;

public class PendingTransactionAdapter extends TransactionAdapter  {
    private int resource;
    public PendingTransactionAdapter(Context context, int resource, List<Transaction> objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position,convertView,parent);
    }

}
