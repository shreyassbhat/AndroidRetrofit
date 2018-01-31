package com.retrofit.shreyas.androidretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.shreyas.androidretrofit.model.User;
import com.retrofit.shreyas.androidretrofit.remote.APIUtils;
import com.retrofit.shreyas.androidretrofit.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    UserService userService;
    EditText edtusname;
    EditText edtusnum;
    EditText edtpass;
    Button btnBack;
    Button btnSave;
    Button btnDel;
    TextView tUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tUserName = (TextView) findViewById(R.id.tUserName);
        edtusname = (EditText) findViewById(R.id.edtusname);
        edtusnum = (EditText) findViewById(R.id.edtusnum);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        userService = APIUtils.getUserService();
        Bundle extras = getIntent().getExtras();
        final String UserName = extras.getString("user_name");
        String UserNum = extras.getString("user_num");
        String Password = extras.getString("user_password");

        edtusname.setText(UserName);
        edtusnum.setText(UserNum);
        edtpass.setText(Password);

        if (UserName != null && UserName.trim().length() > 0) {
            edtusname.setFocusable(false);
        } else {
            tUserName.setVisibility(View.INVISIBLE);
            edtusname.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User();
                u.setName(edtusname.getText().toString());
                u.setNum(Integer.parseInt(edtusnum.getText().toString()));
                u.setPassword(edtpass.getText().toString());
                if (UserName != null && UserName.trim().length() > 0) {
                    //update user
                    updateUser(UserName, u);
                } else {
                    //addd user
                    addUser(u);
                }

            }

        });
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }

    });
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deleteUser(UserName);
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }

    });

    }

    public void addUser(User u) {
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "User Createed Sucessfully", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR:", t.getMessage());
            }


        });

    }

    public void updateUser(String UserName, User u) {
        Call<User> call = userService.updateUser(UserName, u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "User updated Sucessfully", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR:", t.getMessage());
            }


        });

    }
    public void deleteUser(String UserName) {
        Call<User> call = userService.deleteUser(UserName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "User deleted Sucessfully", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR:", t.getMessage());
            }


        });
}
    }


