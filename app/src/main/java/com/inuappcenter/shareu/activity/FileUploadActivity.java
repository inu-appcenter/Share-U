package com.inuappcenter.shareu.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.inuappcenter.shareu.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FileUploadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_file_register);
        EditText edtv_content = findViewById(R.id.edtv_content);
    }
}
