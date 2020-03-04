package com.espezzialy.irickandmorty.Model;

import java.util.List;

public class Encyclopedia
{
    private List<Result> results;

    public Encyclopedia(){

    }

    public Encyclopedia(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}