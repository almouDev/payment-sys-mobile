package com.almou.payment.fragments;

import static com.almou.payment.utils.MainUtilities.getData;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.almou.payment.R;
import com.almou.payment.beans.Transaction;

public class PendingTransactionFragment extends ListFragment {

    public PendingTransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PendingTransactionAdapter adapter=new PendingTransactionAdapter(getActivity(),R.layout.pending_transaction_item,getData());
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_pending_transaction, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        PendingTransactionAdapter adapter= (PendingTransactionAdapter) l.getAdapter();
        Transaction transaction=adapter.getItem(position);
        NavController navController= Navigation.findNavController(v);
        Bundle bundle=new Bundle();
        bundle.putString("amount",transaction.getAmount()+"");
        bundle.putString("TransactionID",transaction.getTransaction_id()+"");
        navController.navigate(R.id.nav_confirm_transfer,bundle);
    }
}