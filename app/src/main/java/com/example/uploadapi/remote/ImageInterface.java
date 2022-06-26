package com.example.uploadapi.remote;

////11 passo da aula de upload de imagem

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ImageInterface {

    ///12 titulo da imagem em forma de formulario
    @FormUrlEncoded

    @POST("testeUpload")
    ///a string para base64
    Call<String>uploadImage(
            ////nome q esta na api (Buffer)
            @Field("file") String file,

            ////21
            @Field("titulo")String titulo
    );
}
