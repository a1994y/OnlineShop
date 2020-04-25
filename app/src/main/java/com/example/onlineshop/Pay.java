package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pay extends AppCompatActivity {

    RecyclerView recyclerViewPay;
    Button buyproduct;
    public TextView pricefinal;
    int countamount;

    private ArrayList<String> mImagesUrlsPay = new ArrayList<>();
    private ArrayList<String> mNameProductPay = new ArrayList<>();
    private ArrayList<String> mDescriptionProductPay = new ArrayList<>();
    private PaymentsClient paymentsClient;





    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);




        Wallet.WalletOptions walletOption = new Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build();

       paymentsClient = Wallet.getPaymentsClient(this, walletOption);

      pricefinal = findViewById(R.id.finalprice);
      buyproduct = findViewById(R.id.buyproduct);



      buyproduct.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent(Pay.this, PayForm.class);
              intent.putExtra("Amount", pricefinal.getText());
              startActivity(intent);



          }
      });



    }



    public void onStart(){

        pricefinal = findViewById(R.id.finalprice);

        int totalamount = 0;

        mImagesUrlsPay = BasketSinglton.getBasketsinglton().getmNameProd();


        mDescriptionProductPay = BasketSinglton.getBasketsinglton().getmDescriptionProd();



        mNameProductPay = BasketSinglton.getBasketsinglton().getmImages();


               for (int i = 0; i<mDescriptionProductPay.size(); i++){

                   Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                   Matcher matcher=pat.matcher(mDescriptionProductPay.get(i));
                   while (matcher.find()) {
                       int amount = Integer.parseInt(matcher.group());
                       //totalamount = totalamount + amount;
                       countamount = countamount + amount;
                   }


        }


        pricefinal.setText("Итого: " + countamount + " руб.");



        initRecyclerViewPay();



        super.onStart();
    }


    public int plusPrice(String price){


        int mat1 = 0;

        String txt = price;
        Pattern pat= Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher= pat.matcher(txt);
        while (matcher.find()) {
            int amount = Integer.parseInt(matcher.group());
            mat1 = amount;
        }



        return mat1;
    }

    public void onResume(){


        int totalamount = 0;

        mImagesUrlsPay = BasketSinglton.getBasketsinglton().getmNameProd();


        mDescriptionProductPay = BasketSinglton.getBasketsinglton().getmDescriptionProd();



        mNameProductPay = BasketSinglton.getBasketsinglton().getmImages();




        for (int i = 0; i<mDescriptionProductPay.size(); i++){

            Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
            Matcher matcher=pat.matcher(mDescriptionProductPay.get(i));
            while (matcher.find()) {
                int amount = Integer.parseInt(matcher.group());
                totalamount = totalamount + amount;
            }


        }


        pricefinal.setText("Итого: " + totalamount + " руб.");

        initRecyclerViewPay();

        super.onResume();
    }



    private void initRecyclerViewPay(){

        recyclerViewPay = findViewById(R.id.recyclerViewPay);
        BasketAdapter adapter = new BasketAdapter(this, mImagesUrlsPay, mNameProductPay, mDescriptionProductPay);
        recyclerViewPay.setAdapter(adapter);
        adapter.setActivityTextView(pricefinal);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerViewPay.setLayoutManager(manager);
        adapter.notifyDataSetChanged();

    }

}
