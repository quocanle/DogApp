package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

public class DogBreed implements java.io.Serializable {
    @SerializedName("bred_for")
    private String bred_for;
    @SerializedName("breed_group")
    private String breed_group;
    @SerializedName("country_code")
    private String country_code;
    @SerializedName("height")
    private Height height;
    @SerializedName("id")
    private int id;
    @SerializedName("life_span")
    private String life_span;
    @SerializedName("name")
    private String name;
    @SerializedName("origin")
    private String origin;
    @SerializedName("temperament")
    private String temperament;
    @SerializedName("weight")
    private Weight weight;
    @SerializedName("url")
    private String url;

    public DogBreed(String bred_for, String breed_group, String country_code, Height height, int id, String life_span, String name, String origin, String temperament, Weight weight, String url) {
        this.bred_for = bred_for;
        this.breed_group = breed_group;
        this.country_code = country_code;
        this.height = height;
        this.id = id;
        this.life_span = life_span;
        this.name = name;
        this.origin = origin;
        this.temperament = temperament;
        this.weight = weight;
        this.url = url;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Height {
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    public static class Weight {
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }
}
