package com.example.corona_tracker_final.api;

import java.util.Map;

public class CountryData {

    private String updated;
    private String country;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String active;
    private String tests;
    private String recovered;
    private String todayRecovered;
                                                //    private Map<String,String> countryinfo;
    private String countryinfo;

    public CountryData(String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String active, String tests, String recovered, String todayRecovered, String countryinfo) {
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.active = active;
        this.tests = tests;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.countryinfo = countryinfo;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getCountryinfo() {
        return countryinfo;
    }

    public void setCountryinfo(String countryinfo) {
        this.countryinfo = countryinfo;
    }
}