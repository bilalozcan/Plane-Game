package com.ngdroidapp;

import android.graphics.Canvas;

import istanbul.gamelab.ngdroid.base.BaseActivity;
import istanbul.gamelab.ngdroid.core.AppManager;
import istanbul.gamelab.ngdroid.base.BaseApp;
import istanbul.gamelab.ngdroid.core.NgMediaPlayer;
import istanbul.gamelab.ngdroid.core.SoundManager;
import istanbul.gamelab.ngdroid.util.Log;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */

public class NgApp extends BaseApp {

    public int SESEFEKTİ_BUTTON = 0, SESEFEKTİ_ATES = 1, SESEFEKTİ_PATLAMA = 2, SESEFEKTİ_POWERUP = 3;
    NgMediaPlayer menumuzigi;
    NgMediaPlayer oyunmuzigi;
    SoundManager sesefektcalar;
    public int sesEfektListesi[];
    public int sesEfektSayisi;

    public NgApp(BaseActivity nitraBaseActivity, AppManager appManager) {
        super(nitraBaseActivity, appManager);
    }


    public void setup() {
        sesveMuzikYuklemeleri();

        appManager.setUnitResolution(AppManager.RESOLUTION_QHD);
        appManager.setFrameRate(20);

        MenuCanvas mc = new MenuCanvas(this);
        canvasManager.setCurrentCanvas(mc);


    }

    private void sesveMuzikYuklemeleri() {
        menumuzigi = new NgMediaPlayer(this);
        oyunmuzigi = new NgMediaPlayer(this);
        menumuzigi.load("sounds/menumuzigi.mp3");
        oyunmuzigi.load("sounds/oyunmuzigi.mp3");
        sesEfektSayisi = 4;
        sesefektcalar = new SoundManager(this);
        sesEfektListesi = new int [sesEfektSayisi];
        try {
            sesEfektListesi[SESEFEKTİ_BUTTON] = sesefektcalar.load("sounds/buttonclick.wav");
            sesEfektListesi[SESEFEKTİ_ATES] = sesefektcalar.load("sounds/dusmanates.wav");
            sesEfektListesi[SESEFEKTİ_PATLAMA] = sesefektcalar.load("sounds/se2.wav");
            sesEfektListesi[SESEFEKTİ_POWERUP] = sesefektcalar.load("sounds/powerup.wav");


        } catch(Exception e) {

        }
    }
    public int xOranla(int x) {
        x = x * (getWidth() / 1920);
        return x;
    }

    public int yOranla(int y) {
        y = y * (getHeight() / 1080);
        return y;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void touchDown(int x, int y, int id) {

    }

    public void touchMove(int x, int y, int id) {

    }

    public void touchUp(int x, int y, int id) {

    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void pause() {
        Log.i("NGAPP", "pause");
    }

    public void resume() {
        Log.i("NGAPP", "resume");
    }

    public void reloadTextures() {
        Log.i("NGAPP", "reloadTextures");
    }
}
