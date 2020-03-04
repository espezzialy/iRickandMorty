package com.espezzialy.irickandmorty.Retrofit;

import com.espezzialy.irickandmorty.Model.Encyclopedia;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IRickMotyPedia {
     int pagina = 1;


    @GET("?page="+pagina)
    Observable<Encyclopedia> getListResult();

}
