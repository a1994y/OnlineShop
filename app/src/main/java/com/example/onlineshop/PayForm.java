package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.cloudpayments.sdk.cp_card.CPCard;
import ru.cloudpayments.sdk.cp_card.api.CPCardApi;


public class PayForm extends AppCompatActivity {


    TextView textTotal;
    EditText editCardNumber;
    EditText editCardDate;
    EditText editCardCvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_form);

        textTotal = findViewById(R.id.text_total);
        editCardNumber = findViewById(R.id.edit_card_number);
        editCardDate = findViewById(R.id.edit_card_date);
        editCardCvc = findViewById(R.id.edit_card_cvc);

        String cardNumber = editCardNumber.getText().toString();
        String cardDate = editCardDate.getText().toString();
        String cardCVC = editCardCvc.getText().toString();

        int mat = 0;

        Intent intent = getIntent();
        String mAmount = intent.getStringExtra("Amount");

        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(mAmount);
        while (matcher.find()) {
            int amount = Integer.parseInt(matcher.group());
            mat = amount;
        }


        textTotal.setText("Всего к оплате: " + mat + " руб.");
    }
}
