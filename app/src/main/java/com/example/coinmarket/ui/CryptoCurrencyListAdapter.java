package com.example.coinmarket.ui;

import android.app.Activity;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;


public class CryptoCurrencyListAdapter extends RecyclerView.Adapter<CryptoCurrencyListAdapter.RecyclerViewHolder> {
    private final String selectedCurrency;
    private Activity activity;

    private int selectedPosition = 0;
    private String selectedWord=null;
    private List<CryptoCurrency> cryptoCurrencyList;

    public CryptoCurrencyListAdapter(Activity context, List<CryptoCurrency> currencyList, String currency) {
        this.activity = context;
        this.cryptoCurrencyList = currencyList;
        this.selectedCurrency = currency;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public int index;

        @BindView(R.id.cv) CardView cv;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.rank) TextView rank;
        @BindView(R.id.symbol) TextView symbol;
        @BindView(R.id.price) TextView price;
        @BindView(R.id.percentChange24h) TextView percentChange24h;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

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
        holder.price.setText("Price: "+String.valueOf(getFiatCurrencyPrice(position, selectedCurrency)));
        holder.percentChange24h.setText("24 hour change: "+String.valueOf(cryptoCurrencyList.get(position).getPercentChange24h()));

        //holder.word.setText(word);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setSelectedPosition(position);

            Intent intent = new Intent(activity, CryptoCurrencyDetailActivity.class);

            intent.putExtra("id", cryptoCurrencyList.get(position).getId());
            intent.putExtra("name", cryptoCurrencyList.get(position).getName());
            intent.putExtra("symbol", cryptoCurrencyList.get(position).getSymbol());
            intent.putExtra("rank", cryptoCurrencyList.get(position).getRank());
            intent.putExtra("price", getFiatCurrencyPrice(position, selectedCurrency));
            intent.putExtra("priceBtc", cryptoCurrencyList.get(position).getPriceBtc());
            intent.putExtra("_24hVolume", get24hVolume(position, selectedCurrency));
            intent.putExtra("marketCap", getMarketCap(position, selectedCurrency));
            intent.putExtra("availableSupply", cryptoCurrencyList.get(position).getAvailableSupply());
            intent.putExtra("totalSupply", cryptoCurrencyList.get(position).getTotalSupply());
            intent.putExtra("percentChange1h", cryptoCurrencyList.get(position).getPercentChange1h());
            intent.putExtra("percentChange24h", cryptoCurrencyList.get(position).getPercentChange24h());
            intent.putExtra("percentChange7d", cryptoCurrencyList.get(position).getPercentChange7d());
            //intent.putExtra("lastUpdated", cryptoCurrencyList.get(position).getLastUpdated())

            /** show CryptoCurrencyDetailActivity */
            final int REQUEST_CODE = 123;  // The request code
            activity.startActivityForResult(intent,REQUEST_CODE);
            }
        });

        if (getSelectedPosition()==position) {
            holder.cv.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorSelected));    // selected
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

    private Double getFiatCurrencyPrice(int position, String currency) {
        switch (currency) {
            case "USD":
                return cryptoCurrencyList.get(position).getPriceUsd();
            case "EUR":
                return cryptoCurrencyList.get(position).getPriceEur();
            case "CNY":
                return cryptoCurrencyList.get(position).getPriceCny();
            default:
                return cryptoCurrencyList.get(position).getPriceUsd();
        }
    }

    private Double get24hVolume(int position, String currency) {
        switch (currency) {
            case "USD":
                return cryptoCurrencyList.get(position).get24hVolumeUsd();
            case "EUR":
                return cryptoCurrencyList.get(position).get24hVolumeEur();
            case "CNY":
                return cryptoCurrencyList.get(position).get24hVolumeCny();
            default:
                return cryptoCurrencyList.get(position).get24hVolumeUsd();
        }
    }

    private Double getMarketCap(int position, String currency) {
        switch (currency) {
            case "USD":
                return cryptoCurrencyList.get(position).getMarketCapUsd();
            case "EUR":
                return cryptoCurrencyList.get(position).getMarketCapEur();
            case "CNY":
                return cryptoCurrencyList.get(position).getMarketCapCny();
            default:
                return cryptoCurrencyList.get(position).getMarketCapUsd();
        }
    }
}


