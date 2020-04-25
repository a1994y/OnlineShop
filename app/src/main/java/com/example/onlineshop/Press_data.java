package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Press_data extends AppCompatActivity {


    public static int PICK_FILE = 1;
    Button selectImage, add_PRODUCT;
    TextView imageUrl;
    EditText product_NAME, product_DESCRIPTION;
    ImageView imageSet;
    String path = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_data);

        selectImage = findViewById(R.id.selectImage);
        imageUrl = findViewById(R.id.imageUrl);
        add_PRODUCT = findViewById(R.id.add_PRODUCT);
        product_NAME= findViewById(R.id.product_NAME);
        product_DESCRIPTION = findViewById(R.id.product_DESCRIPTION);
        imageSet = findViewById(R.id.image_set);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*image/*");
                startActivityForResult(intent, PICK_FILE);

            }
        });

        imageSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*image/*");
                startActivityForResult(intent, PICK_FILE);

            }
        });



        add_PRODUCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                Intent intent = new Intent(Press_data.this, Shop.class);
                intent.putExtra("imageUrl", imageUrl.getText().toString());
                intent.putExtra("product_NAME", product_NAME.getText().toString());
                intent.putExtra("product_DESCRIPTION", product_DESCRIPTION.getText().toString());
                startActivity(intent);


            }
        });
    }



    private String readFile(Uri uri)
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            reader.close();
        }
        catch (IOException e) {e.printStackTrace();}
        return builder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageUrl = findViewById(R.id.imageUrl);

        if (requestCode == PICK_FILE) {
            if (resultCode == RESULT_OK) {


                Uri uri = data.getData();
                String name = uri.getPath();
                //String ext = FilenameUtils.getExtension(name);
                String fileContent = readFile(uri);
                imageUrl.setText(name);
                path = name;
                File imgFile = new  File(path);
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageSet.setImageBitmap(myBitmap);

            }
        }
    }


}
