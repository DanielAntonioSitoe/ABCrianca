package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class AprenderAnimais extends AppCompatActivity {
    String[] strings;
    int i = -1;
    private TextToSpeech mTTs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender_animais);
        strings = new String[]{"Cabrito","Cão","Cavalo","Coelho","Crocodilo" ,"Elefante" ,"Galinha", "Gato" ,"Leão","Papagaio","Pássaro","Pato","Porco","Tartaruga","Touro","Urso" ,"Vaca","Zebra"};
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("PT", "PT");
                    int result = mTTs.setLanguage(locale);
                    speek(null);
                }else {
                    Log.e("TTS","Falha ao Iniciar!");
                }
            }
        });
        next(null);
    }
    public void next(View view){
        try {
            i++;
            mostrar();
            speek(null);
            if (i>17){
                i=-1;
                next(view);
            }
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
            mostrar();
            speek(null);
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    private void mostrar(){
        ImageView imageView = findViewById(R.id.imageViewAprAni);

        switch (i){
            case 0:imageView.setBackgroundResource(R.drawable.cabrito);break;
            case 1:imageView.setBackgroundResource(R.drawable.cao);break;
            case 2:imageView.setBackgroundResource(R.drawable.cavalo);break;
            case 3:imageView.setBackgroundResource(R.drawable.coelho);break;
            case 4:imageView.setBackgroundResource(R.drawable.crocodilo);break;
            case 5:imageView.setBackgroundResource(R.drawable.elefante);break;
            case 6:imageView.setBackgroundResource(R.drawable.galinha);break;
            case 7:imageView.setBackgroundResource(R.drawable.gato);break;
            case 8:imageView.setBackgroundResource(R.drawable.leao);break;
            case 9:imageView.setBackgroundResource(R.drawable.papagaio);break;
            case 10:imageView.setBackgroundResource(R.drawable.passaro);break;
            case 11:imageView.setBackgroundResource(R.drawable.pato);break;
            case 12:imageView.setBackgroundResource(R.drawable.porco);break;
            case 13:imageView.setBackgroundResource(R.drawable.tartaruga);break;
            case 14:imageView.setBackgroundResource(R.drawable.touro);break;
            case 15:imageView.setBackgroundResource(R.drawable.urso);break;
            case 16:imageView.setBackgroundResource(R.drawable.vaca);break;
            case 17:imageView.setBackgroundResource(R.drawable.zebra);break;
        }
    }
    public void speek(View view){
        String text = strings[i];
        mTTs.speak(text, TextToSpeech.QUEUE_FLUSH,null);
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
