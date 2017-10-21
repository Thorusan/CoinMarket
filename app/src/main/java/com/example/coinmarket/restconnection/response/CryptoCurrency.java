package com.example.coinmarket.restconnection.response;

import java.util.Date;

import java.util.Date;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("price_usd")
    @Expose
    private Double priceUsd;
    @SerializedName("price_btc")
    @Expose
    private Double priceBtc;
    @SerializedName("24h_volume_usd")
    @Expose
    private Double _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    @Expose
    private Double marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    private Double availableSupply;
    @SerializedName("total_supply")
    @Expose
    private Double totalSupply;
    @SerializedName("percent_change_1h")
    @Expose
    private Double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private Double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private Double percentChange7d;
    @SerializedName("last_updated")
    @Expose
    private Integer lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set24hVolumeUsd(Double _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
