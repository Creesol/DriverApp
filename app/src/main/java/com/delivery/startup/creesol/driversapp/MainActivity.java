package com.delivery.startup.creesol.driversapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.delivery.startup.creesol.driversapp.callback.loginCallBack;
import com.delivery.startup.creesol.driversapp.callback.loginDatacallback;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CardView Admin,Delivery,All_Items,All_Orders;
    private static String TAG="MainActivity";
    private RelativeLayout relativelogin,relativeMain;
    private Spinner adminSpinner;
    ArrayList<String> adminData;
    EditText password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        password=findViewById(R.id.password);
        All_Orders=findViewById(R.id.OrdersCard);


        adminData=new ArrayList<>();
        relativelogin=findViewById(R.id.RelativeLogin);
        relativeMain=findViewById(R.id.RelativeMain);
        Admin=findViewById(R.id.AdminCard);
        adminSpinner=findViewById(R.id.spinnerAdmin);
        Delivery=findViewById(R.id.DeliveryCard);
        All_Items=findViewById(R.id.AllItemDataCard);
        All_Orders=findViewById(R.id.OrdersCard);
        if(sp.getBoolean("logged",false)){
            Log.e(TAG, "onCreate1: "+sp.getBoolean("logged",false));
            goToMainActivity();
        }
        else{
            Log.e(TAG, "onCreateRunning: runs" );
            getDataFromServer.getAdminLoginData(getApplicationContext(), new loginDatacallback() {
                @Override
                public void onSuccess(ArrayList<String> list) {
                    adminSpinner.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, list));
                }

                @Override
                public void onError(String err) {
                    Log.e(TAG, "onError: There is an error" );

                }
            });
        }
        relativeMain.setVisibility(View.GONE);
        relativelogin.setVisibility(View.VISIBLE);

          All_Orders.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(MainActivity.this,AllOrders.class);
                  startActivity(intent);
              }
          });
        Delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });



    }

    public void goToMainActivity() {
        Log.e(TAG, "goToMainActivity: called" );
        /*Intent intent=new Intent(MainActivity.this,MapsActivity.class);
        startActivity(intent);*/
        relativelogin.setVisibility(View.GONE);
        relativeMain.setVisibility(View.VISIBLE);

    }

    public void loginMethod(String admin_id) {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);




        getDataFromServer.postAdminToken(getApplicationContext(), admin_id, refreshedToken, new loginCallBack() {
            @Override
            public void onSuccess(String msg) {
                sp.edit().putBoolean("logged",true).apply();
                Log.e(TAG, "onCreate2: "+sp.getBoolean("logged",false));
                goToMainActivity();
            }

            @Override
            public void notSuccess(String msg) {

            }

            @Override
            public void onError(String err) {
                Toast.makeText(MainActivity.this, "Error connecting to server", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            Log.e(TAG, "onCreate1: "+sp.getBoolean("logged",false));
            goToMainActivity();
        }

    }
    public void login(View view) {
        String pass=password.getText().toString();
        String name=adminSpinner.getSelectedItem().toString();

        getDataFromServer.adminTrueOrFalse(getApplicationContext(), name, pass, new loginCallBack() {
            @Override
            public void onSuccess(String msg) {

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    loginMethod(msg);
                    }


            @Override
            public void notSuccess(String msg) {
                Toast.makeText(MainActivity.this, "Enter Correct Password", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String err) {

            }
        });

    }

    public void AllItems(View view) {
        Intent intent=new Intent(MainActivity.this, All_Items.class);
        startActivity(intent);
    }
}
