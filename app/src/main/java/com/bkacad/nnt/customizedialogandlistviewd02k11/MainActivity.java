package com.bkacad.nnt.customizedialogandlistviewd02k11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView lvTodo;
    private ArrayAdapter<String> myAdapter;
    private List<String> dataTodo;
    private NewTaskDialog newTaskDialog = null;

    private void initUI(){
        btnAdd = findViewById(R.id.btn_main_add);
        lvTodo = findViewById(R.id.lv_main_todo);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        // Sự kiện khi click vào button - hiển thị dialog lên
        // B1: Nếu dialog chưa có sẽ tạo
        // B2: Nếu có rồi sẽ hiển thị lên luôn
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTaskDialog == null){
                    // Tạo mới
                    newTaskDialog = new NewTaskDialog(MainActivity.this) {
                        @Override
                        public void eventNewTaskListener(String todo) {
                            dataTodo.add(todo);
                            myAdapter.notifyDataSetChanged();
                        }
                    };
                }
                newTaskDialog.show();
            }
        });


        // Thử nghiệm xem listview đã hoạt động hay chưa??
        dataTodo = new ArrayList<>();
        dataTodo.add("1. Đi chợ");
        dataTodo.add("2. Học bài");
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataTodo );
        lvTodo.setAdapter(myAdapter);
        // Có thể thêm nhấn giữ xoá
        lvTodo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // XOá dữ liệu theo vị trí
                // Báo Adapter -> dữ liẹu dã thay đổi -> render lại listview đi
                return false;
            }
        });
    }
}