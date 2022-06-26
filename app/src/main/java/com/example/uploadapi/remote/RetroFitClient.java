package com.example.uploadapi.remote;

////13 passo aula de upload de imagem

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroFitClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient(String url){

        if(retrofit == null){


            /*CRIA E CONFIGURA O OBJETO DERETROFIT**/
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

}



