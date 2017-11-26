package com.example.coinmarket.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coinmarket.data.SharedPrefVariables;
import com.example.thorus.coinmarket.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CryptoCurrencySettingsActivity extends AppCompatActivity {

    @BindView(R.id.radioCurrency)
    RadioGroup radioCurrencyGroup;
    @BindView(R.id.radioUSD)
    RadioButton radioUSD;
    @BindView(R.id.radioEUR)
    RadioButton radioEUR;
    @BindView(R.id.radioCNY)
    RadioButton radioCNY;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    private String selectedCurrency = "USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_currency_settings);

        // Bind views
        ButterKnife.bind(this);

        // This overrides the radiogroup onCheckListener
        radioCurrencyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    String text = getString(R.string.choosen_fiat_currency_message) + checkedRadioButton.getText();
                    Toast.makeText(CryptoCurrencySettingsActivity.this, text, Toast.LENGTH_SHORT).show();
                    selectedCurrency = checkedRadioButton.getText().toString();
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrencyInPreference();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(getString(R.string.currency_selected), selectedCurrency);

                setResult(Activity.RESULT_OK, resultIntent);
                CryptoCurrencySettingsActivity.this.finish();
            }
        });
    }

    /**
     * Save selected currency to Shared Preferences
     */
    private void saveCurrencyInPreference() {
        int selectedId = radioCurrencyGroup.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.radioUSD:
                SharedPrefVariables.storeCurrencyToSharedPreferences(this, "USD");
                break;
            case R.id.radioEUR:
                SharedPrefVariables.storeCurrencyToSharedPreferences(this, "EUR");
                break;
            case R.id.radioCNY:
                SharedPrefVariables.storeCurrencyToSharedPreferences(this, "CNY");
                break;
            default:
                SharedPrefVariables.storeCurrencyToSharedPreferences(this, "USD");
                break;
        }
    }
}
