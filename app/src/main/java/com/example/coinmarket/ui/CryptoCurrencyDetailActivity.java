package com.example.coinmarket.ui;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.thorus.coinmarket.R;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencyDetailActivity extends AppCompatActivity {
    @BindView(R.id.name) TextView name;
    @BindView(R.id.rank) TextView rank;
    @BindView(R.id.symbol) TextView symbol;
    @BindView(R.id.price) TextView price;
    @BindView(R.id.priceBtc) TextView priceBtc;
    @BindView(R.id._24hVolumeUsd) TextView _24hVolume;
    @BindView(R.id.marketCapUsd) TextView marketCap;
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

            // fmtLocale = Locale.getDefault(Category.FORMAT);
            Locale locale;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = this.getResources().getConfiguration().getLocales().get(0);
            } else {
                locale = this.getResources().getConfiguration().locale;
            }

            NumberFormat formatter = NumberFormat.getInstance(locale);
            formatter.setMaximumFractionDigits(2);

            if (getIntent() != null) {
                name.setText(getIntent().getStringExtra("name"));

                rank.setText("Rank: "+String.valueOf(getIntent().getIntExtra("rank",0)));
                symbol.setText("symbol: "+getIntent().getStringExtra("symbol"));
                price.setText("Price: "+String.valueOf(getIntent().getDoubleExtra("price",0.0)));

                priceBtc.setText("Price in bitcoin: "+String.valueOf(getIntent().getDoubleExtra("priceBtc",0.0)));

                String _24hVolumeFormattedValue = String.valueOf(formatter.format(getIntent().getDoubleExtra("_24hVolume",0)));
                _24hVolume.setText("24h Volume: "+_24hVolumeFormattedValue);

                String marketCapFormattedValue= String.valueOf(formatter.format(getIntent().getDoubleExtra("marketCap",0)));
                marketCap.setText("Market cap: "+marketCapFormattedValue);

                String availableSupplyFormattedValue = String.valueOf(formatter.format(getIntent().getDoubleExtra("availableSupply",0)));
                availableSupply.setText("Available supply: "+availableSupplyFormattedValue);

                String totalSupplyFormattedValue = String.valueOf(formatter.format(getIntent().getDoubleExtra("totalSupply",0)));
                totalSupply.setText("Total supply: "+totalSupplyFormattedValue);

                percentChange1h.setText("Change 1h: "+String.valueOf(getIntent().getDoubleExtra("percentChange1h",0)));
                percentChange24h.setText("Change 24h: "+String.valueOf(getIntent().getDoubleExtra("percentChange24h",0)));
                percentChange7d.setText("Change 7d: "+String.valueOf(getIntent().getDoubleExtra("percentChange7d",0)));
            }
        } catch (Exception ex) {
            System.out.println("Napaka: "+ex.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
