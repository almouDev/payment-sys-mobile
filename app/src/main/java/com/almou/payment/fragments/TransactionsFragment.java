package com.almou.payment.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.almou.payment.R;
import com.almou.payment.beans.Transaction;
import com.almou.payment.utils.MainUtilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionsFragment extends ListFragment {

    public TransactionsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions_fragments, container, false);

        // Set up the list adapter
        TransactionAdapter adapter = new TransactionAdapter(getActivity(),R.layout.transactions_item, getData());
        setListAdapter(adapter);

        return view;
    }

    private List<Transaction> getData() {
        return MainUtilities.getData();
    }
}