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
import android.widget.TextView;
import android.widget.Toast;

import com.example.coinmarket.data.SharedPrefVariables;
import com.example.coinmarket.restconnection.RestDataCallback;
import com.example.coinmarket.restconnection.RestServiceController;
import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.coinmarket.ui.dialogs.DialogsUtil;
import com.example.coinmarket.utils.NetworkConnection;
import com.example.thorus.coinmarket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencyActivity extends AppCompatActivity implements RestDataCallback {

    public static final int REQUEST_CODE_SETTINGS = 10;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.text_current_currency)
    TextView textCurrentCurrency;
    //@BindView(R.id.edit_search) EditText editWord;
    //@BindView(R.id.btn_search) Button searchBtn;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    int touchPosition = -1;
    CryptoCurrencyListAdapter cryptoCurrencyListAdapter;
    List<CryptoCurrency> cryptoCurrencyList;
    private String currencySelected;
    private int limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_currency_activity);

        // Bind views
        ButterKnife.bind(this);
        // set toolbar for Settings
        setSupportActionBar(toolbar);

        currencySelected = SharedPrefVariables.getCurrencyFromSharedPreferences(this);
        limit = 100; // limit top results

        populateControls();

        if (!NetworkConnection.isNetworkAvailable(this)) {
            DialogsUtil.showDialogNetworkFailed(this);
            return;
        }
        // Retrofit network call for gettings the list of Cryptocurrencies
        callRetrofitServiceAndSetCurrencyList(currencySelected, limit);

    }

    private void populateControls() {
        // set Currency to text
        textCurrentCurrency.setText(getString(R.string.prices_currency) + currencySelected);

        // register listener
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!NetworkConnection.isNetworkAvailable(CryptoCurrencyActivity.this)) {
                    DialogsUtil.showDialogNetworkFailed(CryptoCurrencyActivity.this);
                    toControls();
                    return;
                }
                // Refresh items
                currencySelected = SharedPrefVariables.getCurrencyFromSharedPreferences(CryptoCurrencyActivity.this);
                callRetrofitServiceAndSetCurrencyList(currencySelected, limit);
            }
        });
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
                startActivityForResult(intent, REQUEST_CODE_SETTINGS);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //region Network Service

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SETTINGS) {
            if (resultCode == RESULT_OK) {
                //here is the result
                if (data.hasExtra(getString(R.string.currency_selected))) {
                    currencySelected = data.getStringExtra(getString(R.string.currency_selected));
                    int limit = 100;
                    textCurrentCurrency.setText(getString(R.string.crypto_currency_prices)
                            + currencySelected);
                    callRetrofitServiceAndSetCurrencyList(currencySelected, limit);
                }
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(getApplicationContext(), "Nothing was changed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void toControls() {
        //stop Refresh animation
        swiperefresh.setRefreshing(false);
    }


    //endregion

    //region Interface methods

    private void callRetrofitServiceAndSetCurrencyList(String currency, int limit) {
        RestServiceController controller = new RestServiceController(this, CryptoCurrencyActivity.this);
        controller.getCurrencyDataAndSetList(currency, limit);
    }

    @Override
    public void passCurrencyDataAndSetAdapter(List<CryptoCurrency> cryptoCurrencyList) {
        this.cryptoCurrencyList = cryptoCurrencyList;
        setCryptoCurrencyListAdapter(cryptoCurrencyList);
        toControls();
    }

    //endregion

    private void setCryptoCurrencyListAdapter(List<CryptoCurrency> cryptoCurrencyList) {
        String currency = SharedPrefVariables.getCurrencyFromSharedPreferences(this);
        if (cryptoCurrencyListAdapter == null) {
            cryptoCurrencyListAdapter = new CryptoCurrencyListAdapter(this, currency, cryptoCurrencyList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(cryptoCurrencyListAdapter);
        } else {
            cryptoCurrencyListAdapter.setCurrency(currency);
            cryptoCurrencyListAdapter.setCurrencyList(cryptoCurrencyList);
            cryptoCurrencyListAdapter.notifyDataSetChanged();
        }

    }
}
