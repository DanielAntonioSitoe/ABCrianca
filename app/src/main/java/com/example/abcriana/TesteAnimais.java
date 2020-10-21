package com.example.abcriana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TesteAnimais extends AppCompatActivity {
    String[] strings;
    int i = -1;
    private TextToSpeech mTTs;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private String keeper = "";
    private int a=0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_animais);
        strings = new String[]{"Cabrito","Cão","Cavalo","Coelho","Crocodilo" ,"Elefante" ,"Galinha", "Gato" ,"Leão","Papagaio","Pássaro","Pato","Porco","Tartaruga","Touro","Urso" ,"Vaca","Zebra"};
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Locale locale = new Locale("PT", "PT");
                    int result = mTTs.setLanguage(locale);
                }else {
                    Log.e("TTS","Falha ao Iniciar!");
                }
            }
        });
        next(null);

        checkVoiceCommandPermission();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(TesteAnimais.this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matchesfound = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(matchesfound != null){
                    keeper = matchesfound.get(0);
                    if(keeper.equalsIgnoreCase(strings[i])){
                        speek("Correcto ");
                        Toast.makeText(TesteAnimais.this,"Correcto! ",Toast.LENGTH_LONG).show();
                    }else {
                        speek("Errado ");
                        Toast.makeText(TesteAnimais.this, "Errado! ", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        findViewById(R.id.btnTextAni).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        speechRecognizer.startListening(speechRecognizerIntent);
                        keeper ="";
                        break;
                    case MotionEvent.ACTION_UP:
                        speechRecognizer.stopListening();
                        break;
                }
                return false;
            }
        });


    }
    public void next(View view){
        try {
            i++;
            mostrar();
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
        }catch (Exception e){
            i=-1;
            next(view);
        }
    }
    private void mostrar(){
        ImageView imageView = findViewById(R.id.imageViewTextAni);

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

    public void speek(String s){
        mTTs.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    protected void onDestroy() {
        if(mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }

    private  void checkVoiceCommandPermission(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if(!(ContextCompat.checkSelfPermission(TesteAnimais.this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }



}
