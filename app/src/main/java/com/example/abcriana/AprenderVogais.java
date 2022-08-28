package com.example.abcriana;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AprenderVogais extends AppCompatActivity {
    String[] strings;
    int i = -1;
    TextView textView;
    private TextToSpeech mTTs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_vogais);
        strings = new String[]{"A","E","I","O","U"};
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("PT", "PT");
//                    Voice v = new Voice("Male",locale,)
//                            int r = mTTs.setVoice(new Voice())
                    int result = mTTs.setLanguage(locale);
                    speek(null);
                }else {
                    Log.e("TTS","Falha ao Iniciar!");
                }
            }
        });
        textView =findViewById(R.id.textViewAprAlfa);
        next(null);
    }
    public void next(View view){
        try {
            i++;
            textView.setText(strings[i]);
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
            textView.setText(strings[i]);
            speek(null);
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    public void speek(View view){
        String text = strings[i];
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
