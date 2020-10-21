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

public class TesteFrutas extends AppCompatActivity {

    String[] strings;
    int i = -1;
    private TextToSpeech mTTs;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private String keeper = "";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_frutas);
        strings = new String[]{"Ananás", "Banana","Laranja","Limão", "Maçã","Melão","Ovo", "Tomate"};
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
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(TesteFrutas.this);
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
                        speek("Correcto");
                        Toast.makeText(TesteFrutas.this,"Correcto!",Toast.LENGTH_LONG).show();
                    }else {
                        speek("Errado");
                        Toast.makeText(TesteFrutas.this, "Errado!", Toast.LENGTH_LONG).show();
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

        findViewById(R.id.btnTextfruta).setOnTouchListener(new View.OnTouchListener() {
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
            if (i>7){
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
        ImageView imageView = findViewById(R.id.imageViewTextFruta);

        switch (i){
            case 0:imageView.setBackgroundResource(R.drawable.ananas);break;
            case 1:imageView.setBackgroundResource(R.drawable.banana);break;
            case 2:imageView.setBackgroundResource(R.drawable.laranja);break;
            case 3:imageView.setBackgroundResource(R.drawable.limao);break;
            case 4:imageView.setBackgroundResource(R.drawable.maca);break;
            case 5:imageView.setBackgroundResource(R.drawable.melao);break;
            case 6:imageView.setBackgroundResource(R.drawable.ovo);break;
            case 7:imageView.setBackgroundResource(R.drawable.tomate);break;
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
            if(!(ContextCompat.checkSelfPermission(TesteFrutas.this, Manifest.permission.RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }


}
