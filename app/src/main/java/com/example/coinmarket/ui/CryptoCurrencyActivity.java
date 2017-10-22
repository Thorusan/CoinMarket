package com.example.coinmarket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coinmarket.data.SharedPrefVariables;
import com.example.coinmarket.restconnection.RestDataCallback;
import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.thorus.coinmarket.R;

import com.example.coinmarket.restconnection.RestServiceController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencyActivity extends AppCompatActivity implements RestDataCallback {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.edit_search) EditText editWord;
    @BindView(R.id.btn_search) Button searchBtn;
    @BindView(R.id.swiperefresh) SwipeRefreshLayout swiperefresh;

    int touchPosition=-1;
    CryptoCurrencyListAdapter cryptoCurrencyListAdapter;
    List<CryptoCurrency> cryptoCurrencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.crypto_currency_activity);

            // Bind views
            ButterKnife.bind(this);
            // set toolbar for Settings
            setSupportActionBar(toolbar);

            final String currency = SharedPrefVariables.getCurrencyFromSharedPreferences(this);
            //if (currency == null) currency ="USD"; // default currenty is USD

            final int limit = 100; // limit top results

            // Retrofit network call for gettings the list of Cryptocurrencies
            callRetrofitServiceAndSetCurrencyList(currency, limit);

            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // Refresh items
                    callRetrofitServiceAndSetCurrencyList(currency, limit);
                }
            });
        } catch (Exception ex) {
           System.out.println(ex.getMessage());
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

        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, CryptoCurrencySettingsActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SETTINGS);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SETTINGS){
            if(resultCode == RESULT_OK){
                //here is the result
                if (data.hasExtra("currency")) {
                    String selectedCurrency = data.getStringExtra("currency");
                    int limit = 100;
                    callRetrofitServiceAndSetCurrencyList(selectedCurrency, limit);
                }
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(getApplicationContext(), "Nothing was changed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //region Network Service

    private void callRetrofitServiceAndSetCurrencyList(String currency,  int limit) {

        RestServiceController controller = new RestServiceController(this, CryptoCurrencyActivity.this);
        controller.getCurrencyDataAndSetList(currency, limit);
    }

    //endregion

    //region Interface methods

    @Override
    public void passCurrencyDataAndSetAdapter(List<CryptoCurrency> cryptoCurrencyList) {
        this.cryptoCurrencyList = cryptoCurrencyList;
        setCryptoCurrencyListAdapter();

    }

    private void setCryptoCurrencyListAdapter() {
        String currency = SharedPrefVariables.getCurrencyFromSharedPreferences(this);
        cryptoCurrencyListAdapter = new CryptoCurrencyListAdapter(this, cryptoCurrencyList, currency);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cryptoCurrencyListAdapter);

        // Stop refresh animation
        swiperefresh.setRefreshing(false);
    }

    //endregion

    public static final int REQUEST_CODE_SETTINGS = 10;
}
