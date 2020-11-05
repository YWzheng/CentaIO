package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.centaio.CentaIO;
import com.example.demo.R;

public class TestActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        listView = findViewById(R.id.lv);

        findViewById(R.id.textView).setOnClickListener(v -> startActivity(new Intent(TestActivity.this, TestActivity.class)));
        listView.setAdapter(new Adapter());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CentaIO.event("li", "vo", "");
            new AlertDialog.Builder(this).setTitle("测试").setMessage(String.valueOf(position)).setPositiveButton("确定", (dialog, which) -> {

            }).show();
        });

    }

    public class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_layout, parent, false);
            return inflate;
        }
    }
}