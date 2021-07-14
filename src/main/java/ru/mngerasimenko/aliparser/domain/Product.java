package ru.mngerasimenko.aliparser.domain;

public class Product {

    String url;
    String picture;
    String title;
    String currentPrice;
    String originalPrice;
    String folatR;
    String stockProgress;
    String solder;
    String claimed;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getFolatR() {
        return folatR;
    }

    public void setFolatR(String folatR) {
        this.folatR = folatR;
    }

    public String getStockProgress() {
        return stockProgress;
    }

    public void setStockProgress(String stockProgress) {
        this.stockProgress = stockProgress;
    }

    public String getSolder() {
        return solder;
    }

    public void setSolder(String solder) {
        this.solder = solder;
    }

    public String getClaimed() {
        return claimed;
    }

    public void setClaimed(String claimed) {
        this.claimed = claimed;
    }
}
