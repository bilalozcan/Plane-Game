package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Utils;

/*29 Eylul 2020*/
/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {

    private Bitmap menuarkaplan;
    private Bitmap oynabutonu;
    private Bitmap oyuncuucagi;
    private int oynabutonuX, oynabutonuY, oyuncuucagiX, oyuncuucagiY;
    private int kaydirmaMiktarı, kaydirmaHizi;
    private Rect menuarkaplanKaynak, menuarkaplanHedef;
    private int ucaginivmesi;
    Paint yazirengi;
    Canvas canvas;


    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        menuMuziginiHazirla();
        arkaPlanYukle();
        oyuncuUcagiYukle();
        oynaButonuYukle();
        yaziYazdirYukle();
    }
    private void yaziYazdirYukle() {
        yazirengi = new Paint();
        yazirengi.setTextSize(100);
        yazirengi.setTextAlign(Paint.Align.LEFT);
        yazirengi.setARGB(255, 112,209,244);
        yazirengi.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void menuMuziginiHazirla() {
        root.menumuzigi.prepare();
        root.menumuzigi.start();
    }

    //Oynama butonunu çizer
    private void oynaButonuYukle() {
        oynabutonu = Utils.loadImage(root, "playbutton.png");
        oynabutonuX = 1920 / 2 - oynabutonu.getWidth() / 2;
        oynabutonuY = 1080 / 2 - oynabutonu.getHeight() / 2;
    }

    private void oyuncuUcagiYukle() {
        oyuncuucagi = Utils.loadImage(root,"blue.png");
        oyuncuucagiX = 0 - oyuncuucagi.getWidth() * 4;
        oyuncuucagiY = 1080 / 2;
    }
    private void arkaPlanYukle() {
        kaydirmaMiktarı = 0;
        kaydirmaHizi = 20;
        menuarkaplan = Utils.loadImage(root, "BG4.png");
        menuarkaplanKaynak = new Rect();
        menuarkaplanHedef = new Rect();
        menuarkaplanKaynak.set(0, 0, 0 + menuarkaplan.getWidth(), 0 + menuarkaplan.getHeight());
        menuarkaplanHedef.set(0,0,menuarkaplan.getWidth(),menuarkaplan.getHeight());
    }
    public void update() {
        arkaplaniKaydir();
        ucaginBaslangicHareketi();
    }

    public void draw(Canvas canvas) {
        this.canvas = canvas;
        canvas.scale(getWidth() / 1920f, getHeight() / 1080f);
        arkaplaniCiz();
        oyuncuUcagiCiz();
        //oynaButonuCiz();
        ekranaYaziYaz();
    }

    private void oynaButonuCiz() {
        canvas.drawBitmap(oynabutonu, oynabutonuX, oynabutonuY, null);
    }

    private void ekranaYaziYaz() {
        canvas.drawText("Tap To Start" , 1920/3, getHeight() - 25, yazirengi);
    }
    private void ucaginBaslangicHareketi() {
        if(oyuncuucagiX < oynabutonuX - oyuncuucagi.getWidth() && ((600 - oyuncuucagiX) / 8) > 0) {
            oyuncuucagiX += (1920 / 6 - oyuncuucagiX) / 8;
        }
    }
    private void oyuncuUcagiCiz() {
        canvas.drawBitmap(oyuncuucagi, oyuncuucagiX, oyuncuucagiY, null);
    }
    private void arkaplaniCiz() {

        arkaplaniKaydiranBolum();
        arkaplaniTamamlayanBolum();
    }
    private void arkaplaniKaydiranBolum() {
        menuarkaplanKaynak.set(kaydirmaMiktarı,0, menuarkaplan.getWidth(), menuarkaplan.getHeight());
        menuarkaplanHedef.set(0, 0, 1920-kaydirmaMiktarı, 1080);
        canvas.drawBitmap(menuarkaplan, menuarkaplanKaynak, menuarkaplanHedef, null);
    }
    private void arkaplaniTamamlayanBolum() {
        menuarkaplanKaynak.set(0,0, kaydirmaMiktarı, menuarkaplan.getHeight());
        menuarkaplanHedef.set(1920-kaydirmaMiktarı, 0, 1920, 1080);
        canvas.drawBitmap(menuarkaplan, menuarkaplanKaynak, menuarkaplanHedef, null);
    }
    private void arkaplaniKaydir() {
        if(kaydirmaMiktarı <= 1920 - kaydirmaHizi) {
            kaydirmaMiktarı += kaydirmaHizi;
        } else {
            kaydirmaMiktarı = 0;
        }
    }
    public void keyPressed(int key) {
    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return false;
    }

    private void oynaButonunaBasildimi(int x, int y) {
        root.menumuzigi.stop();
        root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_ATES]);
        root.canvasManager.setCurrentCanvas(new GameCanvas(root));
    }
    public void touchDown(int x, int y, int id) {
        oynaButonunaBasildimi(x, y);
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
        root.menumuzigi.stop();

    }

    public void resume() {
    }

    public void reloadTextures() {
    }

    public void showNotify() {
    }

    public void hideNotify() {
    }

}
