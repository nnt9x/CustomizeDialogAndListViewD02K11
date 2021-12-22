package com.bkacad.nnt.customizedialogandlistviewd02k11;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public abstract class NewTaskDialog extends Dialog {

    private EditText edtTodo;
    private Button btnSave, btnCancel;

    // Tạo 1 hàm callback về
    public abstract void eventNewTaskListener(String todo);

    private void initUI(){
        btnSave = findViewById(R.id.btn_dialog_new_todo_save);
        btnCancel = findViewById(R.id.btn_dialog_new_todo_cancel);
        edtTodo = findViewById(R.id.edt_dialog_new_todo);
    }


    public NewTaskDialog(@NonNull Context context) {
        super(context);
    }
    // Ctrl + O

    @Override
    public void show() {
        super.show();
        // Reset lại dữ liệu của editText
        edtTodo.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_new_todo);
        initUI();
        // Khi click vào thoát
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = edtTodo.getText().toString().trim();
                if(todo.isEmpty()){
                    edtTodo.setError("Không được để trống");
                    return;
                }
                // Gửi dữ liệu về MainActivity ??
                eventNewTaskListener(todo);
                dismiss();

            }
        });

    }
}
