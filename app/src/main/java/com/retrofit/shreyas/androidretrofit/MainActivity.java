package com.retrofit.shreyas.androidretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.retrofit.shreyas.androidretrofit.model.User;
import com.retrofit.shreyas.androidretrofit.remote.APIUtils;
import com.retrofit.shreyas.androidretrofit.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  MainActivity extends AppCompatActivity {
    Button btnAddUser;
    Button btnGetUserList;
    ListView listView;


    UserService userService;
    List<User> list = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddUser =(Button) findViewById(R.id.btnAddUser);
        btnGetUserList = (Button) findViewById(R.id.btnGetUserList);
        listView =(ListView) findViewById(R.id.listView);
        userService = APIUtils.getUserService();

        btnGetUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get users list
                getUserList();
            }
        });
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                intent.putExtra("user_name","");
                startActivity(intent);
            }
        });
    }
    public void getUserList(){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new UserAdapter(MainActivity.this,R.layout.list_user,list));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERROR:",t.getMessage());

            }
        });
    }
}
