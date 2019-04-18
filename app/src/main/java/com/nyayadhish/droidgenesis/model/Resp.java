package com.nyayadhish.droidgenesis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 13:24.
 */
public class Resp{
    private String status;
    private String totalResults;
    List<News> articles = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
