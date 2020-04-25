package com.example.onlineshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    RecyclerView recyclerView;
    Dialog mDialog;

    String menuItems = "";
    int numb = 1;
    String uRLS = "";

    private ArrayList<String> mImagesUrls = new ArrayList<>();
    private ArrayList<String> mNameProduct = new ArrayList<>();
    private ArrayList<String> mDescriptionProduct = new ArrayList<>();
    private ArrayList<String> mRealDescription = new ArrayList<>();

    Button menu_item, menu_item1, menu_item2, menu_item3,menu_item4, menu_item5, menu_item6;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        menu_item = findViewById(R.id.menu_item);
        menu_item1 = findViewById(R.id.menu_item1);
        menu_item2 = findViewById(R.id.menu_item2);
        menu_item3 = findViewById(R.id.menu_item3);
        menu_item4 = findViewById(R.id.menu_item4);
        menu_item5 = findViewById(R.id.menu_item5);
        menu_item6 = findViewById(R.id.menu_item6);

        menu_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog = new Dialog(Shop.this);
                mDialog.setContentView(R.layout.fragment_menu);

                Button but_contact = mDialog.findViewById(R.id.contact);
                Button but_promo = mDialog.findViewById(R.id.promo);
                Button but_delivery = mDialog.findViewById(R.id.delivery);

                but_contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Shop.this, Contact.class);
                        startActivity(intent);

                    }
                });

                but_promo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Shop.this, Promo.class);
                        startActivity(intent);

                    }
                });

                but_delivery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Shop.this, Delivery.class);
                        startActivity(intent);

                    }
                });

                mDialog.show();

            }
        });

        menu_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImagesUrls.clear();
                mNameProduct.clear();
                mDescriptionProduct.clear();
                mRealDescription.clear();
                delRecyclerView();


                menuItems = "https://horoshiki.ru/pizza/";
                uRLS = ".rol_img";

                MyAsyncTask mAT = new MyAsyncTask();
                mAT.execute();

            }
        });

        menu_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mImagesUrls.clear();
                mNameProduct.clear();
                mDescriptionProduct.clear();
                mRealDescription.clear();
                delRecyclerView();


                menuItems = "https://horoshiki.ru/desserts/";
                uRLS = ".rol_img";

                MyAsyncTask mAT = new MyAsyncTask();
                mAT.execute();

            }
        });

        menu_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImagesUrls.clear();
                mNameProduct.clear();
                mDescriptionProduct.clear();
                mRealDescription.clear();
                delRecyclerView();


                menuItems = "https://horoshiki.ru/sousi/";
                uRLS = ".rol_img";

                MyAsyncTask mAT = new MyAsyncTask();
                mAT.execute();

            }
        });

        menu_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImagesUrls.clear();
                mNameProduct.clear();
                mDescriptionProduct.clear();
                mRealDescription.clear();
                delRecyclerView();


                menuItems = "https://horoshiki.ru/drinks/";
                uRLS = ".rol_img";

                MyAsyncTask mAT = new MyAsyncTask();
                mAT.execute();

            }
        });

        menu_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mImagesUrls.clear();
                mNameProduct.clear();
                mDescriptionProduct.clear();
                mRealDescription.clear();
                delRecyclerView();


                menuItems = "https://horoshiki.ru/combos/?sub=10";
                uRLS = ".rol_img";

                MyAsyncTask mAT = new MyAsyncTask();
                mAT.execute();

            }
        });

        menu_item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupMenu(v);

            }
        });
       /* String operation = getIntent().getStringExtra("product_NAME");

        if(operation!=null)
        {

            String namProd = getIntent().getExtras().getString("product_NAME");
            mNameProduct.add(namProd);


            String urls = getIntent().getExtras().getString("imageUrl");
            mImagesUrls.add(urls);

            String prodDecr = getIntent().getExtras().getString("product_DESCRIPTION");
            mDescriptionProduct.add(prodDecr);

            initRecyclerView();


        }*/


    }

    public void onStart(){

        Parsing mt = new Parsing();
        mt.execute();


        super.onStart();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground (Void... voids) {



            Document doc = null;

                try {
                    doc = Jsoup.connect(menuItems).get();

                    Elements imgURL = doc.select(uRLS);
                    Elements rollTitle = doc.select(".rol_title");
                    Elements priceRoll = doc.select(".price_rol_1");
                    Elements discriptionRoll = doc.select(".rol_description");


                    for (Element image : imgURL) {

                        mImagesUrls.add("https://horoshiki.ru/" + image.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src"));

                    }

                    for (Element title : rollTitle) {

                        mNameProduct.add(title.text());

                    }

                    for (Element price : priceRoll) {

                        mDescriptionProduct.add("Цена: " + price.text() + " руб.");

                    }

                    for (Element disciption : discriptionRoll) {

                        mRealDescription.add(disciption.text());

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }



            return null;
        }

        protected void onPostExecute(Void list){

            initRecyclerView();

        }
    }


    class Parsing extends AsyncTask<Void, Void, Void> {


        String infoURL = "";



        @Override
        protected Void doInBackground (Void... voids) {




            Document doc = null;
            try {
                doc = Jsoup.connect("https://horoshiki.ru/combos/?sub=10").get();



                Elements imgURL = doc.select(".rol_img");
                Elements rollTitle = doc.select(".rol_title");
                Elements priceRoll = doc.select(".price_rol_1");
                Elements discriptionRoll = doc.select(".rol_description");


                for (Element image : imgURL) {

                   mImagesUrls.add("https://horoshiki.ru/" + image.select("img[src~=(?i)\\.(png|jpe?g|gif)]").attr("src"));

                }

                for (Element title : rollTitle) {

                    mNameProduct.add(title.text());

                }

                for (Element price : priceRoll) {

                    mDescriptionProduct.add("Цена: " + price.text() + " руб.");

                }

                for (Element disciption : discriptionRoll) {

                    mRealDescription.add(disciption.text());

                }

                /*System.out.println("Ссылка на: " + imgURL.toString());
                infoURL = imgURL.toString();*/


            } catch (IOException e) {
                e.printStackTrace();
            }




            return null;
        }




        protected void onPostExecute(Void list){

            initRecyclerView();
            /*initRecyclerViewMenuItem();*/


        }

    }


    private void showPopupMenu (View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu);


        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=2";
                                uRLS = ".rol_img_main";

                                MyAsyncTask mAT = new MyAsyncTask();
                                mAT.execute();

                            case R.id.menu2:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=3";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT1 = new MyAsyncTask();
                                mAT1.execute();

                            case R.id.menu3:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=4";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT2 = new MyAsyncTask();
                                mAT2.execute();
                                return true;
                            case R.id.menu4:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=5";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT3 = new MyAsyncTask();
                                mAT3.execute();

                            case R.id.menu5:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=6";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT4 = new MyAsyncTask();
                                mAT4.execute();

                            case R.id.menu6:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=7";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT5 = new MyAsyncTask();
                                mAT5.execute();

                            case R.id.menu7:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=8";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT6 = new MyAsyncTask();
                                mAT6.execute();

                            case R.id.menu8:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=9";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT7 = new MyAsyncTask();
                                mAT7.execute();

                            case R.id.menu9:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=28";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT8 = new MyAsyncTask();
                                mAT8.execute();

                            case R.id.menu10:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=29";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT9 = new MyAsyncTask();
                                mAT9.execute();

                            case R.id.menu11:

                                mImagesUrls.clear();
                                mNameProduct.clear();
                                mDescriptionProduct.clear();
                                mRealDescription.clear();
                                delRecyclerView();


                                menuItems = "https://horoshiki.ru/sushi_rolls/?sub=30";
                                uRLS = ".rol_img_main";


                                MyAsyncTask mAT10 = new MyAsyncTask();
                                mAT10.execute();

                            default:
                                return false;
                        }
                    }
                });

    /*popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
        @Override
        public void onDismiss(PopupMenu menu) {
            Toast.makeText(getApplicationContext(), "onDismiss",
                    Toast.LENGTH_SHORT).show();
        }
    });*/
        popupMenu.show();
}

    private void delRecyclerView(){

        recyclerView = findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(this, mImagesUrls, mNameProduct, mDescriptionProduct, mRealDescription);
        recyclerView.removeAllViewsInLayout();
        recyclerView.removeAllViews();
        adapter.notifyDataSetChanged();

    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.recyclerView);
        ProductAdapter adapter = new ProductAdapter(this, mImagesUrls, mNameProduct, mDescriptionProduct, mRealDescription);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter.notifyDataSetChanged();

    }

}
