package com.example.uploadapi.remote;

////13 passo da aula de upload de imagems

public class APIUtils {

    public APIUtils() {
    }

    public static final String API_URL = "http://10.107.144.19:3000";

    public static ImageInterface getUploadInterface(){

        //// onde esta API
        return RetroFitClient.getClient(API_URL)
                //// AS  ROTAS
                .create(ImageInterface.class);


}


}
