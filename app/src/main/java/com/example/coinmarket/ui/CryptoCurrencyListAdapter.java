package com.example.coinmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.thorus.coinmarket.R;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Ales on 22. 11. 2016.
 */

public class CryptoCurrencyListAdapter extends RecyclerView.Adapter<CryptoCurrencyListAdapter.RecyclerViewHolder> {
    private Activity activity;


    private int selectedPosition = 0;
    private String selectedWord=null;
    private List<CryptoCurrency> cryptoCurrencyList;

    public CryptoCurrencyListAdapter(Activity context, List<CryptoCurrency> currencyList) {
        this.activity = context;
        this.cryptoCurrencyList = currencyList;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public int index;

        /*@BindView(R.id.cv) CardView cv;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.rank) TextView rank;
        @BindView(R.id.symbol) TextView symbol;
        @BindView(R.id.fiat_currency) TextView fiatCurrency;
        @BindView(R.id.hour_change_24) TextView percentChange24h;*/

        CardView cv;
        TextView name;
        TextView rank;
        TextView symbol;
        TextView fiatCurrency;
        TextView percentChange24h;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);

            rank = (TextView) itemView.findViewById(R.id.rank);
            symbol = (TextView) itemView.findViewById(R.id.symbol);
            fiatCurrency = (TextView) itemView.findViewById(R.id.fiat_currency);
            percentChange24h = (TextView) itemView.findViewById(R.id.hour_change_24);


            // Make this view clickable
            itemView.setClickable(true);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_row_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.index=position;
        //final String word = wordsList.get(position).getText();
        holder.name.setText(cryptoCurrencyList.get(position).getName());
        //holder.rank.setText(cryptoCurrencyList.get(position).getRank());
        holder.rank.setText("Rank: "+String.valueOf(cryptoCurrencyList.get(position).getRank()));
        holder.symbol.setText("Symbol: "+cryptoCurrencyList.get(position).getSymbol());
        holder.fiatCurrency.setText("Fiat Currency: "+String.valueOf(cryptoCurrencyList.get(position).getPriceUsd()));
        holder.percentChange24h.setText("24 hour change: "+String.valueOf(cryptoCurrencyList.get(position).getPercentChange24h()));

        //holder.word.setText(word);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedPosition(position);

                Bundle bundle= new Bundle();
                //bundle.putString("word", word);
                //bundle.putInt("position", position);

                Intent intent = new Intent(activity, CryptoCurrencyDetailActivity.class);
                intent.putExtras(bundle);

                final int REQUEST_CODE = 1;  // The request code
                activity.startActivityForResult(intent,REQUEST_CODE);

                // WordsAdapter.this.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });

        if (getSelectedPosition()==position) {
            holder.cv.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorSelected));    // selected
            //wordsAdapterInterface.onWordClicked(); // enable remove button
        } else {
            holder.cv.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorNotSelected)); // not selected
        }
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


