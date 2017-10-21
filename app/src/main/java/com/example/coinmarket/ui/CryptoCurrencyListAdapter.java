package com.example.coinmarket.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coinmarket.restconnection.RestDataCallback;
import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.thorus.coinmarket.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Ales on 22. 11. 2016.
 */

public class CryptoCurrencyListAdapter extends RecyclerView.Adapter<CryptoCurrencyListAdapter.RecyclerViewHolder> {
    private Context mContext;


    private int selectedPosition = 0;
    private String selectedWord=null;
    private List<CryptoCurrency> cryptoCurrencyList;

    public CryptoCurrencyListAdapter(Context context, List<CryptoCurrency> currencyList) {
        this.mContext = context;
        this.cryptoCurrencyList = currencyList;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public int index;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            // Make this view clickable
            itemView.setClickable(true);


            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.index=position;
        //final String word = wordsList.get(position).getText();
        holder.name.setText(cryptoCurrencyList.get(position).getName());

        //holder.word.setText(word);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedPosition(position);

                // WordsAdapter.this.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });

        /*if (getSelectedPosition()==position) {
            holder.name.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorSelected));    // selected
            //wordsAdapterInterface.onWordClicked(); // enable remove button
        } else {
            holder.name.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorNotSelected)); // not selected
        }*/
    }

    @Override
    public int getItemCount() {
        return cryptoCurrencyList.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int pos) {
        selectedPosition=pos;
    }


}


