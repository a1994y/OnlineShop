package com.example.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private Context mContext;
    private ArrayList<String> mImagesBasket = new ArrayList<>();
    private  ArrayList<String> mNameProdBasket = new ArrayList<>();
    private  ArrayList<String> mpriceBasket = new ArrayList<>();
    private TextView activityTextView;

    public void setActivityTextView(TextView textView) {
        activityTextView = textView;
    }


    public BasketAdapter (Context mContext, ArrayList<String> mImages, ArrayList<String> mNameProd, ArrayList<String> mDescriptionProd) {
        this.mContext = mContext;
        this.mImagesBasket = mImages;
        this.mNameProdBasket = mNameProd;
        this.mpriceBasket = mDescriptionProd;
    }


    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);
        BasketAdapter.BasketViewHolder holder = new BasketAdapter.BasketViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketViewHolder holder, final int position) {

        Glide.with(mContext).load(mImagesBasket.get(position)).into(holder.imageViewBasket);
        holder.productNameBasket.setText(mNameProdBasket.get(position));
        holder.priceProductBaket.setText(mpriceBasket.get(position));
        holder.countBasket.setText("1");


        holder.plusBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int mat1 = 0;
                final int str = Integer.parseInt(holder.countBasket.getText().toString().trim());
                int one = 1;
                Integer result = str + one;
                holder.countBasket.setText(result.toString());

                Pay pay = new Pay();

                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher = pat.matcher(activityTextView.getText());
                while (matcher.find()) {
                    int amount = Integer.parseInt(matcher.group());
                    mat1 = amount;
                }


                Integer mat2 = mat1 + pay.plusPrice(mpriceBasket.get(position));

                activityTextView.setText("Итого: " + mat2 + " руб.");


            }
        });

        holder.minusBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int mat1 = 0;
                final int str = Integer.parseInt(holder.countBasket.getText().toString().trim());
                int one = 1;
                Integer result = str - one;
                holder.countBasket.setText(result.toString());

                Pay pay = new Pay();

                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher = pat.matcher(activityTextView.getText());
                while (matcher.find()) {
                    int amount = Integer.parseInt(matcher.group());
                    mat1 = amount;
                }


                Integer mat2 = mat1 - pay.plusPrice(mpriceBasket.get(position));

                activityTextView.setText("Итого: " + mat2 + " руб.");

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int mat1 = 0;
                int mat3 = 0;
                Pay pay = new Pay();


                Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher = pat.matcher(holder.countBasket.getText());
                while (matcher.find()) {
                    int amount = Integer.parseInt(matcher.group());
                    mat1 = amount;
                }

                Integer mat2 = mat1 * pay.plusPrice(mpriceBasket.get(position));


                Pattern pat1 = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
                Matcher matcher1 = pat1.matcher(activityTextView.getText());
                while (matcher1.find()) {
                    int amounts = Integer.parseInt(matcher1.group());
                    mat3 = amounts;
                }

                Integer mat4 = mat3 - mat2;

                activityTextView.setText("Итого: " + mat4 + " руб.");

                mImagesBasket.remove(position);
                mNameProdBasket.remove(position);
                mpriceBasket.remove(position);
                notifyItemRemoved(position);


            }
        });

    }


    @Override
    public int getItemCount() {
        return mImagesBasket.size();
    }

    class BasketViewHolder extends RecyclerView.ViewHolder{


        ImageView imageViewBasket;
        TextView productNameBasket;
        TextView priceProductBaket;
        Button plusBasket;
        Button minusBasket;
        TextView countBasket;
        Button delete;

        public BasketViewHolder (@NonNull View itemView) {
            super(itemView);


            imageViewBasket = itemView.findViewById(R.id.imageViewBasket);
            productNameBasket = itemView.findViewById(R.id.nameProductBasket);
            priceProductBaket = itemView.findViewById(R.id.priceProductBasket);
            plusBasket = itemView.findViewById(R.id.plus);
            minusBasket = itemView.findViewById(R.id.minus);
            countBasket = itemView.findViewById(R.id.countBasket);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
