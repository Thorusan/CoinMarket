package com.example.coinmarket.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.thorus.coinmarket.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencyDetailActivity extends AppCompatActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.rank) TextView rank;
    @BindView(R.id.symbol) TextView symbol;
    @BindView(R.id.priceUsd) TextView priceUsd;
    @BindView(R.id._24hVolumeUsd) TextView _24hVolumeUsd;
    @BindView(R.id.marketCapUsd) TextView marketCapUsd;
    @BindView(R.id.availableSupply) TextView availableSupply;
    @BindView(R.id.totalSupply) TextView totalSupply;
    @BindView(R.id.percentChange1h) TextView percentChange1h;
    @BindView(R.id.percentChange24h) TextView percentChange24h;
    @BindView(R.id.percentChange7d) TextView percentChange7d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.crypto_currency_detail_activity);

            ButterKnife.bind(this);

            if (getIntent() != null) {
                name.setText(getIntent().getStringExtra("name"));

                rank.setText("Rank: "+String.valueOf(getIntent().getIntExtra("rank",0)));
                symbol.setText("symbol: "+getIntent().getStringExtra("symbol"));
                priceUsd.setText("Price: "+String.valueOf(getIntent().getDoubleExtra("priceUsd",0.0)));
                _24hVolumeUsd.setText("24h Volume: "+String.valueOf(String.format("%.2f", getIntent().getDoubleExtra("_24hVolumeUsd",0))));
                marketCapUsd.setText("Market cap: "+String.valueOf(String.format("%.2f", getIntent().getDoubleExtra("marketCapUsd",0))));
                availableSupply.setText("Available supply: "+String.valueOf(String.format("%.2f", getIntent().getDoubleExtra("availableSupply",0))));
                totalSupply.setText("Total supply: "+String.valueOf(String.format("%.2f",getIntent().getDoubleExtra("totalSupply",0))));
                percentChange1h.setText("Change 1h: "+String.valueOf(getIntent().getDoubleExtra("percentChange1h",0)));
                percentChange24h.setText("Change 24h: "+String.valueOf(getIntent().getDoubleExtra("percentChange24h",0)));
                percentChange7d.setText("Change 7d: "+String.valueOf(getIntent().getDoubleExtra("percentChange7d",0)));
            }
        } catch (Exception ex) {
            System.out.println("Napaka: "+ex.getMessage());
        }

    }
}
