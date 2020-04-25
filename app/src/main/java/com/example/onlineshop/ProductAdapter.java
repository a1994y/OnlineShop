package com.example.onlineshop;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    DatabaseHelper dbHelper;
    private ArrayList<String> mImages = new ArrayList<>();
    private  ArrayList<String> mNameProd = new ArrayList<>();
    private  ArrayList<String> mDescriptionProd = new ArrayList<>();
    private  ArrayList<String> mRealDescription = new ArrayList<>();
    Dialog myDialog;



    public ProductAdapter (Context mContext, ArrayList<String> mImages, ArrayList<String> mNameProd, ArrayList<String> mDescriptionProd, ArrayList<String> mRealDescription) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mNameProd = mNameProd;
        this.mDescriptionProd = mDescriptionProd;
        this.mRealDescription = mRealDescription;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        ProductViewHolder holder = new ProductViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull ProductViewHolder holder, final int position) {

        final String lil = "";

        Glide.with(mContext).load(mImages.get(position)).into(holder.imageView);
        holder.productName.setText(mNameProd.get(position));
        holder.descriptionProduct.setText(mDescriptionProd.get(position));
        holder.realDiscrip.setText(mRealDescription.get(position));

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.fragment_shop);

        holder.product_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView dialog_name = myDialog.findViewById(R.id.nameProduct_fragment);
                TextView dialog_discrip = myDialog.findViewById(R.id.description_fragment);
                TextView dialog_price = myDialog.findViewById(R.id.price_fragment);
                ImageView dialog_image = myDialog.findViewById(R.id.imageView_fragment);

                Glide.with(mContext).load(mImages.get(position)).into(dialog_image);
                dialog_name.setText(mNameProd.get(position));
                dialog_price.setText(mDescriptionProd.get(position));
                dialog_discrip.setText(mRealDescription.get(position));

                myDialog.show();

            }
        });

        holder.buttonBUY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BasketSinglton.getBasketsinglton().addmImages(mImages.get(position));
                BasketSinglton.getBasketsinglton().addmNameProd(mNameProd.get(position));
                BasketSinglton.getBasketsinglton().addmDescriptionProd(mDescriptionProd.get(position));

            }
        });

    }

    @Override
    public int getItemCount () {
        return mImages.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView productName;
        TextView descriptionProduct;
        Button buttonBUY;
        TextView realDiscrip;
        LinearLayout product_id;

        public ProductViewHolder (@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            productName = itemView.findViewById(R.id.nameProduct);
            descriptionProduct = itemView.findViewById(R.id.descriptionProduct);
            buttonBUY = itemView.findViewById(R.id.buyButton);
            realDiscrip = itemView.findViewById(R.id.descriptionReal);
            product_id = itemView.findViewById(R.id.product_id);
        }
    }

}
