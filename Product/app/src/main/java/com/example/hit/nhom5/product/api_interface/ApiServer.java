package com.example.hit.nhom5.product.api_interface;

import com.example.hit.nhom5.product.model.AllProduct;
import com.example.hit.nhom5.product.model.DataCategories;
import com.example.hit.nhom5.product.model.Login;
import com.example.hit.nhom5.product.model.LoginResponse;
import com.example.hit.nhom5.product.model.SignUp;
import com.example.hit.nhom5.product.model.SignUpResponse;
import com.example.hit.nhom5.product.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServer {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

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

    @GET("api/v1/users/get-by/{email}")
    Call<User> getUserByEmail(@Path("email") String email);

    @POST("api/v1/users/add-user")
    Call<SignUpResponse> signUp(@Body SignUp signUp);

    @POST("auth/login")
    Call<LoginResponse> login(@Body Login login);
}
