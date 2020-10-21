package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class AprenderNumeros extends AppCompatActivity {
    int[] nrs ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    int i = -1;
    TextView textView;
    private TextToSpeech mTTs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_numeros);
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("PT", "BR");
                    int result = mTTs.setLanguage(locale);
                    speek(null);
                }else {
                    Log.e("TTS","Falha ao Iniciar!");
                }
            }
        });
        textView =findViewById(R.id.textViewAprNumer);
        next(null);
    }
    public void next(View view){
        try {
            i++;
            textView.setText(nrs[i]+"");
            speek(null);
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    public void prev(View view){
        try {
            if(i!=0){
                --i;
            }
            textView.setText(nrs[i]+"");
            speek(null);
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    public void speek(View view){
        String text = nrs[i]+"";
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if(mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
}
