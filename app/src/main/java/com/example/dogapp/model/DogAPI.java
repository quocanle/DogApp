package com.example.dogapp.model;

import java.util.List;

import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Single;

public interface DogAPI {
    @GET("DevTides/DogsApi/master/dogs.json")
    Single<List<DogBreed>> getDogs();
}
