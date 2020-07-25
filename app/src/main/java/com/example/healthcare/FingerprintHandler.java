package com.example.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private  Context context;

    public FingerprintHandler(Context context){
        this.context=context;
    }
    public  void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal= new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an auth error"+ errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Auth failed",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error: "+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can now access the app",true);
    }

    private void update(String s, boolean b) {

        TextView paraLabel=(TextView)((Activity)context).findViewById(R.id.paraLable);
        ImageView imageView=(ImageView)((Activity)context).findViewById(R.id.fingerprintimage);
        Button Loginbutton=(Button) ((Activity)context).findViewById(R.id.Loginbutton);
        paraLabel.setText(s);

        if(b== false){
            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }else {
            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.action_done);
            Loginbutton.setText("Click here to view the main page");
            Loginbutton.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            Loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Activity)context, MenuPage.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}