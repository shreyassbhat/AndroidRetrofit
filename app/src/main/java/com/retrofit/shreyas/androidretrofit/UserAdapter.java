package com.retrofit.shreyas.androidretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.retrofit.shreyas.androidretrofit.model.User;

import java.util.List;

/**
 * Created by User on 31-01-2018.
 */

public class UserAdapter extends ArrayAdapter<User> {

    private  Context context;
    private List<User> users;

    public UserAdapter(@NonNull Context context,@LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_user,parent,false);

        TextView txtUserName = (TextView)rowView.findViewById(R.id.txtUserName);
        TextView txtUserNum = (TextView)rowView.findViewById(R.id.txtUserNum);
        TextView txtUserPassword = (TextView)rowView.findViewById(R.id.txtUserPassword);

        txtUserName.setText(String.format("user_name: %s",users.get(pos).getName()));
        txtUserNum.setText(String.format("user_num: %s",users.get(pos).getNum()));
        txtUserPassword.setText(String.format("user_password: %s",users.get(pos).getPassword()));

        rowView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //start Activity User form
                Intent intent = new Intent(context,UserActivity.class);
                intent.putExtra("user_name",String.valueOf(users.get(pos).getName()));
                intent.putExtra("user_num",String.valueOf(users.get(pos).getNum()));
                intent.putExtra("user_password",String.valueOf(users.get(pos).getPassword()));
                context.startActivity(intent);
            }



        });
           return  rowView;
    }
}
