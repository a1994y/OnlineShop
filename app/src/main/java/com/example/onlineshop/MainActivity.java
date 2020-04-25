package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;

public class MainActivity extends TabActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");
        // название вкладки
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_shop_black_24dp));
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(new Intent(this, Shop.class));
        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        // указываем название и картинку
        // в нашем случае вместо картинки идет xml-файл,
        // который определяет картинку по состоянию вкладки
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.ic_shopping_cart_black_24dp));
        tabSpec.setContent(new Intent(this, Pay.class));
        tabHost.addTab(tabSpec);

        // вторая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag1");


    }
}
