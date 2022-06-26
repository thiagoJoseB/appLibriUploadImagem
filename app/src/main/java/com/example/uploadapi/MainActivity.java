package com.example.uploadapi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uploadapi.remote.APIUtils;
import com.example.uploadapi.remote.ImageInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar;
    ImageView imgLivro;
    EditText txtTitulo;

    ////07
    final int GALERY = 1;

    ImageInterface imageInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ////05 aula de iamgem o 04 é actyvit

        btnCadastrar = findViewById(R.id.btnCadastrar);
        imgLivro = findViewById(R.id.imgLivro);
        txtTitulo = findViewById(R.id.txtTitulo);

        imageInterface = APIUtils.getUploadInterface();

        btnCadastrar.setOnClickListener(view -> {



            ///// 06 aula de iamgem
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            ///07 aula de imagem
            /// tipo imagem / qualquer imagem
            intent.setType("image/*");

            startActivityForResult(intent, GALERY);



        });

    }//// FIM DO METODO DO ONCREATE

    ///08 aula de imagem
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /// se oque vir for result canceled
        if(requestCode == this.RESULT_CANCELED){

            return;
        }

        ///09 aula de upload de iamgem
        //// retorno for da galeria
        if(requestCode == GALERY){

            //// retorno da galeria for diferende de null
            if(data != null){
                Uri uri = data.getData();

                try {
                    Bitmap bitmap  = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    /////representacao da imagem
                    imgLivro.setImageBitmap(bitmap);



                    ///20 passo da aula de upload de imagem
                    uploadImageRetrofit(bitmap);






                }catch (IOException e){
                    Log.d("IMAGEM", e.getMessage());

                }
                  ////10 ir no vscode

            }

        }

    } ///FIM DO METODO onActivityResult

    ///14 passo da aula de upload de imagem
    public void uploadImageRetrofit(Bitmap bitmap){

        ////15 OBJETO DE STREAMING
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ////16 COMPRESAO DO ARQUIVO
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);


        ////17 CONVERSAO PARA A BASE64
        String file = Base64.encodeToString(
                byteArrayOutputStream.toByteArray(),
                Base64.DEFAULT);

        ////18 REALIZAR A CHAMA DA CALL DA ROTA DE UPLOAD

        ////22 passo para a aula de upload de imagem 
        String titulo = txtTitulo.getText().toString();

        Call<String> call = imageInterface.uploadImage(file , titulo);



        ///19 passo de upload de imagem
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this,
                               "UPLOAD REALIZADO",
                                Toast.LENGTH_LONG);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("UPLOAD-ERRO", t.getMessage());

            }
        });

    }

}//// FIM DA CLASSE













