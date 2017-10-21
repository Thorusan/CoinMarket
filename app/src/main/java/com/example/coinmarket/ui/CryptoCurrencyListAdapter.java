package com.example.coinmarket.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thorus.coinmarket.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ales on 22. 11. 2016.
 */

public class CryptoCurrencyListAdapter extends RecyclerView.Adapter<CryptoCurrencyListAdapter.RecyclerViewHolder> {
    private Context mContext;


    private int selectedPosition = 0;
    private String selectedWord=null;
    private ArrayList cryptoCurrencyList;

    public CryptoCurrencyListAdapter(Context context, List<CryptoCurrencyRowData> currencyList) {
        this.mContext = context;

    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView word;
        public int index;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //word = (TextView) itemView.findViewById(R.id.word);
            // Make this view clickable
            itemView.setClickable(true);
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

        //holder.word.setText(word);


        holder.word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectedPosition(position);

                // WordsAdapter.this.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });


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

    public String getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(String word) {
        selectedWord=word;
    }

    /**
     * Created by Thorus on 18. 10. 2017.
     */

    static class CryptoCurrencyRowData {
    }
}


