package com.recyclerview.using.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.recyclerview.using.java.adapter.ProductRecyclerViewAdapter;
import com.recyclerview.using.java.model.Product;
import java.util.ArrayList;

public class RecyclerViewNestedScrollingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_nested_scrolling);
        initView();
        init();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void init() {
        productArrayList = getData();
        setupRecyclerView(recyclerView);

        productRecyclerViewAdapter.setItemClickListener(new ProductRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onClick(int position, View view, Product item) {
                Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        productRecyclerViewAdapter.setItemLongClickListener(new ProductRecyclerViewAdapter.ItemLongClickListener() {
            @Override
            public void onLongClick(int position, View view, Product item) {
                Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<Product> getData() {
        ArrayList<Product> items = new ArrayList<Product>();
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        items.add(new Product("", "Samsung Galaxy M32", "12000"));
        items.add(new Product("", "TIMEX Analog Men's Watch", "250"));
        items.add(new Product("", "boAt Airdopes", "450"));
        return items;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), productArrayList);
        recyclerView.setAdapter(productRecyclerViewAdapter);
    }
}