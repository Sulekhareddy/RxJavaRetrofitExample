package com.example.sulekha.rxjavaretrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sulekha.rxjavaretrofitexample.network.ApIService;
import com.example.sulekha.rxjavaretrofitexample.network.ApiClient;
import com.example.sulekha.rxjavaretrofitexample.network.model.StarwarsCharacter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ApIService apIService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private StarwarsCharacter starwarsCharacter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apIService = ApiClient.getClient(getApplicationContext())
                .create(ApIService.class);
        //textView = findViewById(R.id.textView);

        disposable.add(
                apIService.getCharacter(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<StarwarsCharacter>(){

                        @Override
                        public void onSuccess(StarwarsCharacter starwarsCharacter) {
                            Log.i("StarwarsCharacter", starwarsCharacter.getName());
                            //setStarwarsCharacter(starwarsCharacter);
                            //textView.setText(starwarsCharacter.getName());

                        }

                        @Override
                        public void onError(Throwable e) {
                                Log.e("StarwarsCharacter", e.getMessage());
                        }
                    })
        );
    }

    /*public void setStarwarsCharacter(StarwarsCharacter starwarsCharacter){
        this.starwarsCharacter = starwarsCharacter;
    }*/

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
