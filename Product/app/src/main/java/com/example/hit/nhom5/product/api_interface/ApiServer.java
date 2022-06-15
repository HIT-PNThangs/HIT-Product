package com.example.hit.nhom5.product.api_interface;

import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.DataCategories;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServer {
    Gson gson = new GsonBuilder().
            setDateFormat("yyyy-MM-dd HH:mm:ss").
            create();

    ApiServer apiServer = new
            Retrofit.Builder().
            baseUrl("https://produt-sale-fastfood.herokuapp.com/").
            addConverterFactory(GsonConverterFactory.create(gson)).
            build().
            create(ApiServer.class);

    @GET("api/v1/categories/list/{id}")
    Call<DataCategories> getDataCategories(@Path("id") int id);

    @GET("api/v1/products/get")
    Call<AllProduct> getAllProduct();

}
