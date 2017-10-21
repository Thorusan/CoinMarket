package com.example.coinmarket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coinmarket.restconnection.RestDataCallback;
import com.example.coinmarket.restconnection.response.CryptoCurrency;
import com.example.thorus.coinmarket.R;

import com.example.coinmarket.restconnection.RestServiceController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencyActivity extends AppCompatActivity implements RestDataCallback {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.edit_earch) EditText editWord;
    @BindView(R.id.btn_search) Button searchBtn;

    int touchPosition=-1;
    CryptoCurrencyListAdapter cryptoCurrencyListAdapter;
    List<CryptoCurrency> cryptoCurrencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

            ButterKnife.bind(this);

            String currency ="EUR";
            int limit = 10;

            RestServiceController controller = new RestServiceController(this, CryptoCurrencyActivity.this);
            controller.getCurrencyData(currency);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                //here is the result
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(getApplicationContext(), "Nothing was changed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void passCurrencyDataAndSetAdapter(List<CryptoCurrency> cryptoCurrencyList) {
        this.cryptoCurrencyList = cryptoCurrencyList;
        setCryptoCurrencyListAdapter();

    }

    private void setCryptoCurrencyListAdapter() {
        cryptoCurrencyListAdapter = new CryptoCurrencyListAdapter(this, cryptoCurrencyList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(cryptoCurrencyListAdapter);

    }
}
