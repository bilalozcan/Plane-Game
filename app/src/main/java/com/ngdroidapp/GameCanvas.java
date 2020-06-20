package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.Random;
import java.util.Vector;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {
    private int DUSMANUCAGI_TUR = 0, DUSMANUCAGI_AKS = 1, DUSMANUCAGI_KONUMX = 2, DUSMANUCAGI_KONUMY = 3,
    DUSMANUCAGI_HIZ = 4, DUSMANUCAGI_TEKRARATES = 5;

    private int OYUNCUMERMISI_TUR = 0, OYUNCUMERMISI_AKS = 1, OYUNCUMERMISI_DOGX = 2, OYUNCUMERMISI_DOGY = 3,
    OYUNCUMERMISI_KONUMX = 4, OYUNCUMERMISI_KONUMY = 5, OYUNCUMERMISI_HIZ = 6, OYUNCUMERMİSİ_GUC = 7;

    private int DUSMANMERMISI_TUR = 0, DUSMANMERMISI_AKS = 1, DUSMANMERMISI_DOGX = 2, DUSMANMERMISI_DOGY = 3,
            DUSMANMERMISI_KONUMX = 4, DUSMANMERMISI_KONUMY = 5, DUSMANMERMISI_HIZ = 6;

    private int PATLAMAEFEKTİ_AKS = 0, PATLAMAEFEKTI_X = 1, PATLAMAEFEKTI_Y = 2;

    private int YARDIMPAKETI_TUR = 0, YARDIMPAKETI_X = 1, YARDIMPAKETI_Y = 2, YARDIMPAKETI_HIZ = 3;

    private int BOSS_TUR = 0, BOSS_AKS = 1, BOSS_X = 2, BOSS_Y = 3, BOSS_HIZ = 4, BOSS_DAVRANIS = 5;

    private Canvas canvas;
    private int yerbolgesifarki;

    // Oyuncu uçağı Parametreleri
    private Bitmap arkaplan;
    private Bitmap oyuncucagi[];
    private int ucaginDurumu, ucaginframesayisi;
    private int oyuncucagix, oyuncucagiy, dusmanucagix, dusmanucagiy;
    private int oyuncuucagican;
    //Muhimmat parametreleri
    private Bitmap muhimmatbari;
    private int muhimmatbarix, muhimmatbariy, muhimmatbariw, muhimmatbarih;
    private Rect muhimmatbarikaynak, muhimmatbarihedef;
    //Can
    private Bitmap canseviyesi, canvemuhimmatcercevesi;
    private Rect canseviyesiKaynak, canseviyesiHedef;
    private int canseviyesix, canseviyessiy, caninAzalmaMiktari;
    private int maximumcan, vurulmahakki, eklenecekcancarpani;
    private int canvemuhimmatx, canvemuhimmaty, muhimmatmiktari;
    private boolean muhimmatbitti;
    //Boss parameterleri
    Vector<Vector<Integer>> bossListesi;
    Vector<Integer> yeniBoss;
    private Bitmap bossAnimasyonlari[][];
    private int bossCesitleri;
    private int bossAnimasyonKareSayisi[];
    private int uretilenboss;
    private long bossatesibz, bossatesisz,bossatesikosul;
    private Math mat;

    private Bitmap puancercevesi;
    private int puancercevesix, puancercevesiy;

    private Bitmap yukariButon, asagiButon, atesButon;
    int yukariButonX, yukariButonY, asagiButonX, asagiButonY, atesButonX, atesButonY;

    private Bitmap yardimPaketResimleri[];
    private int yardimPaketiCesitleri;
    private Vector<Vector<Integer>> yardimPaketleri;
    private Vector<Integer> yeniYardımPaketi;

    private long yardimpaketi_bz, yardimpaketi_sz, yardimpaketi_kosul;
    private Random rastgelesayiuretici;

    private boolean yukariCik, asagiin;
    private Rect arkaplanKaynak, arkaplanHedef;
    private int kaydirmaMiktarı, kaydirmaHizi;
    private Bitmap ucakAnimasyonları[][];
    private int ucakCesitleri;
    private int animasyonKareSayisi[];
    Vector<Vector<Integer>> dusmanUcaklari;
    Vector<Integer> dusmanYeniUcak;
    //mermi nesnesi
    Vector<Vector<Integer>> oyuncuMermileri;
    Vector<Integer> oyuncuYeniMermi;
    Vector<Vector<Integer>> dusmanMermileri;
    Vector<Integer> dusmanYeniMermi;
    Vector<Vector<Integer>> patlamaEfektleri;
    Vector<Integer> yeniPatlamaEfekti;
    //For döngülerinin indexleri.
    private int duc_i, duec_i;
    private long baslangicZamani, suankiZaman;
    //Patlama Animasyonları
    private Bitmap patlamaAnimasyon[];
    private int patlamaAnimasyonSayisi;
    int patlamaKonumX, patlamaKonumY;
    int animasyonKareSayaci;
    boolean patlamaEfektiBaslasin;
    //Mermi Parametreleri
    private Bitmap mermiAnimasyon[];
    private int mermiAnimasyonSayisi;
    //Oyuncu Mermileri Parametreleri
    private Bitmap oyuncuMermiAnimasyonlari[][];
    private int oyuncuMermiCesitleri;
    private int oyuncuMermiAnimasyonKareSayisi[];

    Paint puanrengi, bossisimrengi;
    private int puan;
    private int ucaklarinPuanDegerleri[];
    //Oyun bitiş kontroleri
    boolean oyunbitti;
    boolean oyundevamediyor;
    private int framebekletici;
    private Bitmap puantablosu;
    private int puantablosuX, puantablosuY;
    private Bitmap ayarlarbutonu;
    private int ayarlarbutonX, ayarlarbutonY;
    private Bitmap duraklatmamenusu;
    private int duraklatmamenusuX, duraklatmamenusuY;
    private int sayac;
    private Rect bosscanikaynak, bosscanihedef;
    private Bitmap bosscani;
    private int bosscanix, bosscaniy, bosscaniw, bosscanih;



    public android.graphics.Rect getArkaplanKaynak() {
        return arkaplanKaynak;
    }

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        yerbolgesifarki = 110;
        sayac = 0;
        rastgelesayiuretici = new Random();
        oyunMuziginiHazirla();
        arkaPlanYukle();
        animasyonlariYukle();
        oyuncuUcagiYukle();
        dusmanUcagiYukle();
        kontrolButonlariYuklendi();
        puanBaslangicParametreleri();
        puanTablosuYukle();
        canSeviyesiYukle();
        muhimmatSeviyesiYukle();
        yardimPaketleriYuklemeIslemi();
        duraklatmaMenusuYukle();
        bosslariYukle();
        ayarlarButonuYukle();
        oyuncuMermileri = new Vector<Vector<Integer>>();
        dusmanMermileri = new Vector<Vector<Integer>>();
        patlamaEfektleri = new Vector<Vector<Integer>>();
        yukariCik = false;
        asagiin = false;
        oyuncuucagican = 50;
        oyundevamediyor = true;
        oyunbitti = false;
        framebekletici = 10;
    }

    private void bosslariYukle() {
        bossatesibz = System.currentTimeMillis();
        bossatesisz = System.currentTimeMillis();
        bossCaniYukle();
        bossatesikosul = 4000;
        uretilenboss = 0;
        bossListesi = new Vector<Vector<Integer>>();
        bossCesitleri = 2;
        bossAnimasyonKareSayisi = new int[bossCesitleri];
        bossAnimasyonKareSayisi[0] = 3;
        bossAnimasyonKareSayisi[1] = 3;
        bossAnimasyonlari = new Bitmap[bossCesitleri][3];
        for (int bscy_i = 0; bscy_i < bossCesitleri; bscy_i++){
            for (int bscy_j = 0; bscy_j < bossAnimasyonKareSayisi[bscy_i];bscy_j++) {
                bossAnimasyonlari[bscy_i][bscy_j] = Utils.loadImage(root,"boss " + (bscy_i + 1) + " 0" + (bscy_j) + ".png");
            }
        }

    }

    private void bossCaniYukle() {
        bosscanikaynak = new Rect();
        bosscanihedef = new Rect();
        bossisimrengi = new Paint();
        bossisimrengi.setTextSize(40);
        bossisimrengi.setColor(Color.BLACK);
        bossisimrengi.setTypeface(Typeface.DEFAULT_BOLD);
        bossisimrengi.setTextAlign(Paint.Align.CENTER);
        bosscani = Utils.loadImage(root, "can_bar.png");
        bosscanix = 500;
        bosscaniy = 20;
        bosscaniw = 1700;
        bosscanih = 60;
        bosscanikaynak.set(0, 0, bosscani.getWidth(),bosscani.getHeight());
    }

    private void muhimmatSeviyesiYukle() {
        muhimmatmiktari = 50;
        muhimmatbitti = false;
        muhimmatbarikaynak = new Rect();
        muhimmatbarihedef = new Rect();
        muhimmatbari = Utils.loadImage(root, "mermi_bar.png");
        muhimmatbarix = 30;
        muhimmatbariy = 85;
        muhimmatbariw = 360;
        muhimmatbarih = 115;
        muhimmatbarikaynak.set(0, 0,  muhimmatbari.getWidth(), muhimmatbari.getHeight());
    }

    private void ayarlarButonuYukle() {
        ayarlarbutonu = Utils.loadImage(root, "ayarlarbutton.png");
        ayarlarbutonX = 1920 - ayarlarbutonu.getWidth();
        ayarlarbutonY = 10 + puancercevesi.getHeight();

    }

    private void duraklatmaMenusuYukle() {
        duraklatmamenusu = Utils.loadImage(root, "pausemenu.png");
        duraklatmamenusuX = 1920 / 2 - duraklatmamenusu.getWidth() / 2;
        duraklatmamenusuY =  1080/ 2 - duraklatmamenusu.getHeight() / 2;
    }

    private void puanTablosuYukle() {
        puantablosu = Utils.loadImage(root, "oyunsonu3.png");
        puantablosuX = 1920 / 2 - puantablosu.getWidth() / 2;
        puantablosuY = 1080 / 2 - puantablosu.getHeight() / 2;
    }

    private void yardimPaketleriYuklemeIslemi() {
        yardimpaketi_bz = System.currentTimeMillis();
        yardimpaketi_sz = System.currentTimeMillis();
        yardimpaketi_kosul = 10000 + (1000 * rastgelesayiuretici.nextInt(30));
        yardimPaketiCesitleri = 2;
        yardimPaketResimleri = new Bitmap[yardimPaketiCesitleri];
        yardimPaketResimleri[0] = Utils.loadImage(root, "energy_icon.png");
        yardimPaketResimleri[1] = Utils.loadImage(root, "life_icon.png");
        yardimPaketleri = new Vector<Vector<Integer>>();
    }
    private void yeniYardimPaketiUret() {
        if(yardimpaketi_sz - yardimpaketi_bz > yardimpaketi_kosul) {
            yeniYardımPaketi = new Vector<Integer>();
            int secilenPaket = rastgelesayiuretici.nextInt(2);
            int yeniKonumY = rastgelesayiuretici.nextInt(yardimPaketResimleri[secilenPaket].getWidth());
            yeniYardımPaketi.add(secilenPaket); //TÜR
            yeniYardımPaketi.add(1080); //X KONUMU
            yeniYardımPaketi.add(yeniKonumY); //Y KONUMU
            yeniYardımPaketi.add(-10); //HIZ
            yardimPaketleri.add(yeniYardımPaketi);
            yardimpaketi_bz = System.currentTimeMillis();
            yardimpaketi_kosul = 10000 + (1000 * rastgelesayiuretici.nextInt(30));
        } else {
            yardimpaketi_sz = System.currentTimeMillis();

        }
    }

    private void canSeviyesiYukle() {
        canvemuhimmatcercevesi = Utils.loadImage(root,"bar.png");
        canvemuhimmatx = 0;
        canvemuhimmaty = 0;
        vurulmahakki = 50;
        eklenecekcancarpani = 20;
        canseviyesi = Utils.loadImage(root, "can_bar.png");
        maximumcan = 430;
        caninAzalmaMiktari = maximumcan;
        canseviyesix = 80;
        canseviyessiy = 30;
        canseviyesiKaynak = new Rect();
        canseviyesiHedef = new Rect();
        canseviyesiKaynak.set(0, 0, canseviyesi.getWidth(), canseviyesi.getHeight());
    }
    private void canSeviyesiGuncelle() {
        caninAzalmaMiktari -= maximumcan / vurulmahakki;
        if(caninAzalmaMiktari <= 0) {
            oyunbitti = true;
            oyundevamediyor = false;
        }
    }
    private void muhimmatSeviyesiGuncelle(){
        muhimmatbariw -= 330 / muhimmatmiktari;
        if(muhimmatbariw <= 0){
            muhimmatbitti = true;
        }else {
            muhimmatbitti = false;
        }

    }
    private void oyunMuziginiHazirla() {
        root.oyunmuzigi.prepare();
        root.oyunmuzigi.start();
    }

    private void kontrolButonlariYuklendi() {
        asagiButon = Utils.loadImage(root, "asagibuton.png");
        asagiButonX = 0;
        asagiButonY = 1080 - asagiButon.getHeight();
        yukariButon = Utils.loadImage(root, "yukaributon.png");
        yukariButonX = 0;
        yukariButonY = 1080 - yukariButon.getHeight() - asagiButon.getHeight();
        atesButon = Utils.loadImage(root, "atesbuton.png");
        atesButonX = 1920 - atesButon.getWidth();
        atesButonY = 1080 - atesButon.getHeight();
    }

    private void puanBaslangicParametreleri() {
        puancercevesi = Utils.loadImage(root, "puanpanel.png");
        puancercevesix = 1920 - puancercevesi.getWidth();
        puancercevesiy = 0;
        puan = 0; //Puan değişkeni başlangıç değeri.
        ucaklarinPuanDegerleri = new int[ucakCesitleri];
        ucaklarinPuanDegerleri[0] = 10;
        ucaklarinPuanDegerleri[1] = 20;
        ucaklarinPuanDegerleri[2] = 30;
        ucaklarinPuanDegerleri[3] = 60;
        puanrengi = new Paint();
        puanrengi.setTextSize(36);
        puanrengi.setTextAlign(Paint.Align.LEFT);
        puanrengi.setARGB(255, 0,255,0);
        puanrengi.setTypeface(Typeface.DEFAULT_BOLD);

    }

    private void animasyonlariYukle() {

        ucakAnimasyonlariYukle();
        patlamaAnimasyonlariYukle();
        mermiAnimasyonlariYukle();
        oyuncuMermiAnimasyonlariYukle();
    }
    private void mermiAnimasyonlariYukle() {
        mermiAnimasyonSayisi = 3;
        mermiAnimasyon = new Bitmap[mermiAnimasyonSayisi];
        for (int ma_i = 0; ma_i < mermiAnimasyonSayisi; ++ma_i) {
            mermiAnimasyon[ma_i] = Utils.loadImage(root, "rocket " + (ma_i +1) + ".png");
        }
    }
    private void patlamaAnimasyonlariYukle() {
        patlamaEfektiBaslasin = false;
        patlamaAnimasyonSayisi = 8;
        animasyonKareSayaci = 0;
        patlamaAnimasyon = new Bitmap[patlamaAnimasyonSayisi];
        for (int i = 0; i < patlamaAnimasyonSayisi ; ++i) {
            patlamaAnimasyon[i] = Utils.loadImage(root, "Explode" + (i+1) + ".png");
        }
    }
    private void yeniPatlamaEfektiUret(int patX, int patY) {
        yeniPatlamaEfekti = new Vector<Integer>();
        yeniPatlamaEfekti.add(0); //AKS
        yeniPatlamaEfekti.add(patX); // Patlama Konum X
        yeniPatlamaEfekti.add(patY); // Patlama Konum Y
        patlamaEfektleri.add(yeniPatlamaEfekti);
    }
    private void oyuncuMermiAnimasyonlariYukle() {
        oyuncuMermiCesitleri = 1;
        oyuncuMermiAnimasyonKareSayisi = new int[oyuncuMermiCesitleri];
        oyuncuMermiAnimasyonKareSayisi[0] = 4;
        oyuncuMermiAnimasyonlari = new Bitmap[oyuncuMermiCesitleri][4];
        for(int oma_i = 0; oma_i < oyuncuMermiCesitleri; ++oma_i) {
            for(int omaks_i = 0; omaks_i < oyuncuMermiAnimasyonKareSayisi[oma_i]; ++omaks_i)
                oyuncuMermiAnimasyonlari[oma_i][omaks_i] = Utils.loadImage(root, "Fire " + (omaks_i + 1) + ".png");
        }
    }

    private void ucakAnimasyonlariYukle() {
        ucakCesitleri = 4;
        animasyonKareSayisi = new int[ucakCesitleri];
        animasyonKareSayisi[0] = 1;
        animasyonKareSayisi[1] = 3;
        animasyonKareSayisi[2] = 3;
        animasyonKareSayisi[3] = 3;
        ucakAnimasyonları = new Bitmap[ucakCesitleri][3];
        for(int i = 0 ; i < ucakCesitleri ; ++i) {
            for(int j = 0; j < animasyonKareSayisi[i];++j) {
                ucakAnimasyonları[i][j] = Utils.loadImage(root, "enemy"+ (i+1) + "0" + (j+1) + ".png");
            }
        }
    }
    private void arkaPlanYukle() {
        kaydirmaMiktarı = 0;
        kaydirmaHizi = 20;
        arkaplan = Utils.loadImage(root, "BG1.png");
        arkaplanKaynak = new Rect();
        arkaplanHedef = new Rect();
        arkaplanKaynak.set(0, 0, 0 + arkaplan.getWidth(), 0 + arkaplan.getHeight());
        arkaplanHedef.set(0,0,arkaplan.getWidth(),arkaplan.getHeight());
    }
    private void oyuncuUcagiYukle() {
        ucaginDurumu = 0;
        ucaginframesayisi = 4;
        oyuncucagi = new Bitmap[ucaginframesayisi];
        oyuncucagi[0] = Utils.loadImage(root, "blue.png");
        oyuncucagi[1] = Utils.loadImage(root, "blue damage.png");
        oyuncucagi[2] = Utils.loadImage(root, "Red.png");
        oyuncucagi[3] = Utils.loadImage(root, "Red damage.png");
        oyuncucagix = 0;
        oyuncucagiy = 200;
    }
    
    private void dusmanUcagiYukle() {
        dusmanUcaklari = new Vector<Vector<Integer>>();
        baslangicZamani = System.currentTimeMillis();
        suankiZaman = System.currentTimeMillis();

    }

  

    public void update() {
        if(oyundevamediyor == false) {
        } else {
            arkaplaniKaydir();
            dusmanUcagiUret();
            yeniYardimPaketiUret();
            yardimPaketleriniIlerlet();
            dusmanUcaklariniIlerlet();
            oyuncuMermileriniIlerlet();
            dusmanMermileriniIlerlet();
            oyuncuUcagiHareketKontrolu();
            carpismaKontrolu();
            dusmanAtesEtsinMi();
            ucaginBaslangicHareketi();
            bossDavranislari();
        }
    }
    public void draw(Canvas canvas) {
        this.canvas = canvas;
        canvas.scale(getWidth() / 1920f, getHeight() / 1080f);
        arkaplaniCiz();
        canVeMuhimmatSeviyesiCiz();
        yardimPaketiCiz();
        oyuncuUcagiCiz();
        dusmanUcagiCiz();
        oyuncuMermileriniCiz();
        dusmanMermileriniCiz();
        bossCiz();
        bossCaniCiz();
        kontrolButonlariniCiz();
        puanCiz();
        patlamaEfektiCiz();
        ayarlarButonuCiz();
        duraklatmaMenusuCiz();
        oyunBitti();
    }

    private void bossCaniCiz() {
        for (int bcc_i = 0; bcc_i < bossListesi.size(); ++bcc_i){
            bosscanihedef.set(bosscanix, bosscaniy, bosscaniw, bosscanih);
            canvas.drawBitmap(bosscani, bosscanikaynak, bosscanihedef,null);
            canvas.drawText("F22", bosscanix + (bosscaniw - bosscanix) / 2, 45, bossisimrengi);
        }
    }

    private void bossCiz() {
        for (int bc_i = 0; bc_i < bossListesi.size(); bc_i++) {
            canvas.drawBitmap(bossAnimasyonlari
                            [bossListesi.get(bc_i).get(BOSS_TUR)]
                            [bossListesi.get(bc_i).get(BOSS_AKS)],
                    bossListesi.get(bc_i).get(BOSS_X),
                    bossListesi.get(bc_i).get(BOSS_Y),
                    null);
            if(bossListesi.get(bc_i).get(BOSS_AKS) <
                    bossAnimasyonKareSayisi[bossListesi.get(bc_i).get(BOSS_TUR)] - 1) {
                bossListesi.get(bc_i).set(BOSS_AKS,
                        bossListesi.get(bc_i).get(BOSS_AKS) + 1);
            } else {
                bossListesi.get(bc_i).set(BOSS_AKS, 0);
            }
        }
    }
    private void bossDavranislari() {
        for (int bd_i = 0; bd_i < bossListesi.size(); bd_i++) {
            if(bossListesi.get(bd_i).get(BOSS_DAVRANIS) == 0) {
                bossBaslangicHareketi(bd_i);
            }
            if(bossListesi.get(bd_i).get(BOSS_DAVRANIS) == 1) {
                bossHareketKontrolu();
            }
            if(bossListesi.get(bd_i).get(BOSS_DAVRANIS) == 2){
                bossAtesKontrolu(bd_i);
            }
        }
    }

    private void bossAtesKontrolu(int bd_i) {
        if (bossatesisz - bossatesibz > bossatesikosul){
            bossMermisiUret(bossListesi.get(bd_i).get(BOSS_X),
                    bossListesi.get(bd_i).get(BOSS_Y));
        }else{
            bossatesisz = System.currentTimeMillis();
        }
    }

    private void bossHareketKontrolu() {
        for(int bhk_i = 0; bhk_i < bossListesi.size(); bhk_i++)
        {
            int hangiyon =  bossAnimasyonlari[bossListesi.get(bhk_i).get(BOSS_TUR)][bossListesi.get(bhk_i).get(BOSS_AKS)].getHeight() / 2 + bossListesi.get(bhk_i).get(BOSS_Y) - oyuncucagiy;
            if (oyuncucagiy < bossListesi.get(bhk_i).get(BOSS_Y) + bossAnimasyonlari[bossListesi.get(bhk_i).get(BOSS_TUR)][bossListesi.get(bhk_i).get(BOSS_AKS)].getHeight() && oyuncucagiy +
                    oyuncucagi[ucaginDurumu].getHeight() > bossListesi.get(bhk_i).get(BOSS_Y)){
                bossListesi.get(bhk_i).set(BOSS_DAVRANIS, 2);
            }else{
                bossListesi.get(bhk_i).set(BOSS_DAVRANIS, 1);
            }

            if (Math.abs(bossListesi.get(bhk_i).get(BOSS_Y) - oyuncucagiy) >= 50) {
                if(hangiyon > 20 && bossListesi.get(bhk_i).get(BOSS_Y) > 0){
                    bossListesi.get(bhk_i).set(BOSS_Y, bossListesi.get(bhk_i).get(BOSS_Y) - bossListesi.get(bhk_i).get(BOSS_HIZ));
                }
                if (hangiyon < -50 && bossListesi.get(bhk_i).get(BOSS_Y) + bossAnimasyonlari[bossListesi.get(bhk_i).get(BOSS_TUR)][bossListesi.get(bhk_i).get(BOSS_AKS)].getHeight() < 1080) {
                    bossListesi.get(bhk_i).set(BOSS_Y, bossListesi.get(bhk_i).get(BOSS_Y) + bossListesi.get(bhk_i).get(BOSS_HIZ));
                }
            }
        }
    }

    private void bossBaslangicHareketi(int bd_i) {
        if(bossListesi.get(bd_i).get(BOSS_X) > 1920 - bossAnimasyonlari[bossListesi.get(bd_i).get(BOSS_TUR)][bossListesi.get(bd_i).get(BOSS_AKS)].getWidth()) {
            bossListesi.get(bd_i).set(BOSS_X, bossListesi.get(bd_i).get(BOSS_X) - (1920 - bossAnimasyonlari[bossListesi.get(bd_i).get(BOSS_TUR)][bossListesi.get(bd_i).get(BOSS_AKS)].getWidth()) / 72 );
        } else {
            bossListesi.get(bd_i).set(BOSS_DAVRANIS, 1);
        }
    }
    private void duraklatmaMenusuCiz() {
        if(oyundevamediyor == false && oyunbitti == false) {
            canvas.drawBitmap(duraklatmamenusu, duraklatmamenusuX, duraklatmamenusuY, null);
        }
    }
    private void ayarlarButonuCiz() {
        canvas.drawBitmap(ayarlarbutonu, ayarlarbutonX, ayarlarbutonY, null);

    }
    private void yardimPaketleriniIlerlet() {
        for(int ypi_i = 0; ypi_i < yardimPaketleri.size(); ++ ypi_i) {
            if(yardimPaketleri.get(ypi_i).get(YARDIMPAKETI_X) < - yardimPaketResimleri[yardimPaketleri.get(ypi_i).get(YARDIMPAKETI_TUR)].getWidth()) {
                yardimPaketleri.remove(ypi_i);
                break;
            } else {
                yardimPaketleri.get(ypi_i).set(YARDIMPAKETI_X, yardimPaketleri.get(ypi_i).get(YARDIMPAKETI_X) + yardimPaketleri.get(ypi_i).get(YARDIMPAKETI_HIZ));
            }
        }
    }

    private void yardimPaketiCiz() {
        for(int ypc_i = 0; ypc_i < yardimPaketleri.size(); ++ypc_i) {
            canvas.drawBitmap(yardimPaketResimleri[yardimPaketleri.get(ypc_i).get(YARDIMPAKETI_TUR)],yardimPaketleri.get(ypc_i).get(YARDIMPAKETI_X), yardimPaketleri.get(ypc_i).get(YARDIMPAKETI_Y),null);
        }
    }

    private void canVeMuhimmatSeviyesiCiz() {
        canseviyesiHedef.set(canseviyesix, canseviyessiy, canseviyesix + caninAzalmaMiktari, canseviyessiy + 30);
        muhimmatbarihedef.set(muhimmatbarix, muhimmatbariy, muhimmatbariw, muhimmatbarih);
        canvas.drawBitmap(canseviyesi, canseviyesiKaynak, canseviyesiHedef, null);
        canvas.drawBitmap(muhimmatbari, muhimmatbarikaynak, muhimmatbarihedef, null);
        canvas.drawBitmap(canvemuhimmatcercevesi, canvemuhimmatx, canvemuhimmaty, null);

    }

    private void ucaginBaslangicHareketi() {
        if(oyuncucagix < 1920 / 2  && ((50 - oyuncucagix) / 8) > 0) {
            oyuncucagix += (50 - oyuncucagix) / 8;
        }
    }
    private void kontrolButonlariniCiz() {
        yukariButonuCiz();
        asagiButonuCiz();
        atesButonuCiz();
    }

    private void atesButonuCiz() {
        canvas.drawBitmap(atesButon, atesButonX, atesButonY, null);
    }
    private  void asagiButonuCiz() {
        canvas.drawBitmap(asagiButon, asagiButonX, asagiButonY, null);
    }
    private void yukariButonuCiz() {
        canvas.drawBitmap(yukariButon, yukariButonX, yukariButonY, null);
    }

    private void oyunBitti() {
        if(oyunbitti == true && oyundevamediyor == false ) {
            canvas.drawBitmap(arkaplan, 0, 0 ,null);
            canvas.drawBitmap(puantablosu, puantablosuX, puantablosuY, null);
            canvas.drawText("Puan: " + puan, 900,600, puanrengi);
        }

    }
    private void puanCiz() {
        canvas.drawBitmap(puancercevesi, puancercevesix, puancercevesiy, null);
        canvas.drawText("" + puan, puancercevesix + 100, puancercevesiy + 53, puanrengi);
    }
    private void dusmanMermileriniCiz() {
        for(int dmc_i = 0; dmc_i < dusmanMermileri.size(); ++dmc_i) {
            int gx = dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_KONUMX) + mermiAnimasyon[dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_TUR)].getWidth() / 2;
            int gy = dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_KONUMY) + mermiAnimasyon[dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_TUR)].getHeight() / 2;
            canvas.save();
            canvas.rotate(180, gx, gy);
            canvas.drawBitmap(mermiAnimasyon[dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_AKS )], dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_KONUMX), dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_KONUMY), null);
            canvas.restore();
            if(dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_AKS) < mermiAnimasyonSayisi - 1) {
                dusmanMermileri.get(dmc_i).set(DUSMANMERMISI_AKS, dusmanMermileri.get(dmc_i).get(DUSMANMERMISI_AKS) + 1);
            } else {
                dusmanMermileri.get(dmc_i).set(DUSMANMERMISI_AKS, 0);
            }
        }
    }

    private void dusmanAtesEtsinMi() {
        for (int dae_i = 0; dae_i < dusmanUcaklari.size(); ++dae_i) {
            if(oyuncucagiy <= dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_KONUMY) + ucakAnimasyonları[dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_TUR)][dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_AKS)].getHeight() &&
                    oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() >= dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_KONUMY)) {
                if(dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_TEKRARATES) > 50) {
                    dusmanMermileriUret(dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_KONUMX), dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_KONUMY));
                    dusmanUcaklari.get(dae_i).set(DUSMANUCAGI_TEKRARATES, 0);
                    root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_ATES]);
                }  else {
                    dusmanUcaklari.get(dae_i).set(DUSMANUCAGI_TEKRARATES, dusmanUcaklari.get(dae_i).get(DUSMANUCAGI_TEKRARATES) + 1);
                }
            }
        }
    }
    private void oyuncuMermileriniCiz() {
        for(int omc_i = 0; omc_i < oyuncuMermileri.size(); ++omc_i) {
            canvas.drawBitmap(mermiAnimasyon[oyuncuMermileri.get(omc_i).get(OYUNCUMERMISI_AKS )], oyuncuMermileri.get(omc_i).get(OYUNCUMERMISI_KONUMX), oyuncuMermileri.get(omc_i).get(OYUNCUMERMISI_KONUMY), null);
            if(oyuncuMermileri.get(omc_i).get(OYUNCUMERMISI_AKS) < mermiAnimasyonSayisi - 1) {
                oyuncuMermileri.get(omc_i).set(OYUNCUMERMISI_AKS, oyuncuMermileri.get(omc_i).get(OYUNCUMERMISI_AKS) + 1);
            } else {
                oyuncuMermileri.get(omc_i).set(OYUNCUMERMISI_AKS, 0);
            }
        }
    }
    private void canseviyesiniArttır() {
        if(caninAzalmaMiktari < maximumcan - (maximumcan / vurulmahakki * eklenecekcancarpani)) {
            caninAzalmaMiktari += maximumcan / vurulmahakki * eklenecekcancarpani;
        } else {
            caninAzalmaMiktari = maximumcan;
        }
    }
    private void carpismaKontrolu() {
        oyuncuUcagiDusmanUcaginaCarptimi();
        oyuncuMermisiDusmanUcaginaCarptimi();
        dusmanMermisiOyuncuUcaginaCarptimi();
        mermilerCarpistimi();
        oyuncuUcagiYardimPaketineCarptimi();
        oyuncuMermisiBossaCarptiMi();
    }

    private void oyuncuMermisiBossaCarptiMi() {
        for(int ombcm_i = 0; ombcm_i < oyuncuMermileri.size(); ++ombcm_i) {
            for(int ombcm_j = 0; ombcm_j < bossListesi.size(); ++ombcm_j) {
                if(oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMX) <= bossListesi.get(ombcm_j).get(BOSS_X)
                        + bossAnimasyonlari[bossListesi.get(ombcm_j).get(BOSS_TUR)]
                        [bossListesi.get(ombcm_j).get(BOSS_AKS)].getWidth() &&
                        oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMX) + oyuncuMermiAnimasyonlari
                                [oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_TUR)][oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_AKS)]
                                .getWidth() >= bossListesi.get(ombcm_j).get(BOSS_X) &&
                        oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMY) <= bossListesi.get(ombcm_j).get(BOSS_Y) +
                                bossAnimasyonlari[bossListesi.get(ombcm_j).get(BOSS_TUR)]
                                [bossListesi.get(ombcm_j).get(BOSS_AKS)].getHeight() &&
                        oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMY) + oyuncuMermiAnimasyonlari
                                [oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_TUR)][oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_AKS)]
                                .getHeight() >= bossListesi.get(ombcm_j).get(BOSS_Y)) {
                    bossCaniGunceller(oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMİSİ_GUC), ombcm_j);
                    patlamaKonumX = oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMX);
                    patlamaKonumY = oyuncuMermileri.get(ombcm_i).get(OYUNCUMERMISI_KONUMY);
                    yeniPatlamaEfektiUret(patlamaKonumX, patlamaKonumY);
                    oyuncuMermileri.remove(ombcm_i);

                }
            }
        }
    }

    private void bossCaniGunceller(int guc, int ombcm_j) {
        bosscaniw -= 1200 / 100 * guc;
        //BOSS ÖLDÜ MÜ KONTROL ET
        if(bosscaniw - bosscanix < 0){
            bossListesi.remove(ombcm_j);
        }
    }

    private void oyuncuUcagiYardimPaketineCarptimi() {
        for(int ouypc_i = 0; ouypc_i < yardimPaketleri.size(); ++ouypc_i) {
            if(oyuncucagix < yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_X) + yardimPaketResimleri[yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_TUR)].getWidth() &&
                    oyuncucagix + oyuncucagi[ucaginDurumu].getWidth() > yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_X) && oyuncucagiy < yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_Y) +
                    yardimPaketResimleri[yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_TUR)].getHeight() && oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() > yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_Y)) {
                if(yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_TUR) == 1) {
                    canseviyesiniArttır();
                }
                if(yardimPaketleri.get(ouypc_i).get(YARDIMPAKETI_TUR) == 0) {
                    muhimmatSeviyesiniArttir();
                }
                root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_POWERUP]);
                yardimPaketleri.remove(ouypc_i);
                break;
            }
        }
    }

    private void muhimmatSeviyesiniArttir() {
        if(muhimmatbariw < (330 / 50 *60)) {
            muhimmatbariw += 330 / 50 * 40;
        } else {
            muhimmatbariw = 360;
        }

        if(muhimmatbariw <= 0) {
            muhimmatbitti = true;
        }else {
            muhimmatbitti = false;
        }
    }
    private void mermilerCarpistimi() {
        for (int mcm_om = 0; mcm_om < oyuncuMermileri.size();++ mcm_om) {
            for (int mcm_dm = 0; mcm_dm < dusmanMermileri.size();++ mcm_dm) {
                if( oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMX) < dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMX) + mermiAnimasyon[dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_AKS)].getWidth() &&
                        oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMX) + mermiAnimasyon[oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_AKS)].getWidth() > dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMX) &&
                        oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMY) < dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMY) + mermiAnimasyon[dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_AKS)].getHeight() &&
                        oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMY) + mermiAnimasyon[oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_AKS)].getHeight() > dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMY) ) {
                    patlamaKonumX = dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMX);
                    patlamaKonumY = dusmanMermileri.get(mcm_dm).get(DUSMANMERMISI_KONUMY);
                    yeniPatlamaEfektiUret(patlamaKonumX, patlamaKonumY);
                    dusmanMermileri.remove(mcm_dm);
                    patlamaKonumX = oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMX);
                    patlamaKonumY = oyuncuMermileri.get(mcm_om).get(OYUNCUMERMISI_KONUMY);
                    yeniPatlamaEfektiUret(patlamaKonumX, patlamaKonumY);
                    oyuncuMermileri.remove(mcm_om);
                    root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_PATLAMA]);
                    break;
                }
            }
        }
    }
    private void dusmanMermisiOyuncuUcaginaCarptimi() {
        for (int dmouc_i = 0; dmouc_i < dusmanMermileri.size(); ++ dmouc_i) {
            if (dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMX) < oyuncucagix + oyuncucagi[ucaginDurumu].getWidth() && dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMX) + mermiAnimasyon[dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_AKS)].getWidth() > oyuncucagix &&
                    dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMY) < oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() && dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMY) + mermiAnimasyon[dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_AKS)].getHeight() > oyuncucagiy) {
                // OYUN BİTTİ
                if(caninAzalmaMiktari > 0) {
                    oyuncuUcagiFrameKontrolu(true);
                    canSeviyesiGuncelle();
                    if(framebekletici > 10) framebekletici = 0;
                } else {
                    oyunbitti = true;
                    oyundevamediyor = false;

                }
                patlamaKonumX = oyuncucagix;
                patlamaKonumY = oyuncucagiy;
                yeniPatlamaEfektiUret(patlamaKonumX, patlamaKonumY);
                yeniPatlamaEfektiUret(dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMX), dusmanMermileri.get(dmouc_i).get(DUSMANMERMISI_KONUMY));
                dusmanMermileri.remove(dmouc_i);
                root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_PATLAMA]);
                break;

            }
        }
    }
    private void oyuncuMermisiDusmanUcaginaCarptimi() {
        for(int secilenMermi = 0; secilenMermi < oyuncuMermileri.size(); ++secilenMermi) {
            for(int secilenUcak = 0; secilenUcak < dusmanUcaklari.size(); ++secilenUcak) {
                if(oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_KONUMX) <= dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMX) + ucakAnimasyonları[dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_TUR)]
                        [dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_AKS)].getWidth() &&
                        oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_KONUMX) + oyuncuMermiAnimasyonlari [oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_TUR)][oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_AKS)] .getWidth()
                                >= dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMX) &&
                        oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_KONUMY) <= dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMY) + ucakAnimasyonları[dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_TUR)]
                                [dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_AKS)].getHeight() &&
                        oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_KONUMY) + oyuncuMermiAnimasyonlari [oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_TUR)][oyuncuMermileri.get(secilenMermi).get(OYUNCUMERMISI_AKS)].getHeight()
                                >= dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMY)) {
                    patlamaKonumX = dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMX);
                    patlamaKonumY = dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_KONUMY);
                    puanEkle(ucaklarinPuanDegerleri[dusmanUcaklari.get(secilenUcak).get(DUSMANUCAGI_TUR)]);
                    dusmanUcaklari.remove(secilenUcak);
                    yeniPatlamaEfektiUret(patlamaKonumX, patlamaKonumY);
                    oyuncuMermileri.remove(secilenMermi);
                    root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_PATLAMA]);

                    break;
                }
            }
        }
    }

    private void puanEkle(int eklenecekpuan) {
        puan += eklenecekpuan;
        if( puan > 30 && bossListesi.size() < 1){
            bossUret();
            uretilenboss +=1;
        }
    }
    private void bossUret() {
        yeniBoss = new Vector<Integer>();
        int bossTuru = rastgelesayiuretici.nextInt(2);
        yeniBoss.add(bossTuru); // BossTuru
        yeniBoss.add(0); // Boss AKS
        yeniBoss.add(1920 + bossAnimasyonlari[bossTuru][0].getWidth() * 2); // Konum X
        yeniBoss.add(540); // Konum Y
        yeniBoss.add(20); // HIZ
        yeniBoss.add(0); // BOss Dssavranıs
        bossListesi.add(yeniBoss);
    }
    private void oyuncuUcagiFrameKontrolu(boolean vuruldu) {
        if(caninAzalmaMiktari < 150) {
            if(vuruldu) {
                ucaginDurumu = 3;

            } else {
                ucaginDurumu = 2;

            }
        } else {
            ucaginDurumu = 1;

            if (vuruldu) {
                ucaginDurumu = 1;
            } else {
                ucaginDurumu = 0;
            }
        }
    }
    private void oyuncuUcagiDusmanUcaginaCarptimi() {
        for(int ouduc_i = 0; ouduc_i < dusmanUcaklari.size() ;ouduc_i++) {
            if(oyuncucagix <= dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMX) +  ucakAnimasyonları[dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_TUR)][dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_AKS)].getWidth() &&
                    oyuncucagix + oyuncucagi[ucaginDurumu].getWidth() >= dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMX) &&
                    oyuncucagiy <= dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMY) + ucakAnimasyonları[dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_TUR)][dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_AKS)].getHeight() &&
                    oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() >= dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMY)) {
                if(caninAzalmaMiktari > 0) {
                    canSeviyesiGuncelle();
                    oyuncuUcagiFrameKontrolu(true);
                    if(framebekletici > 10) framebekletici = 0;

                } else {
                    oyunbitti = true;
                    oyundevamediyor = false;
                }
                patlamaKonumX = dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMX);
                patlamaKonumY = dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_KONUMY);
                puanEkle(ucaklarinPuanDegerleri[dusmanUcaklari.get(ouduc_i).get(DUSMANUCAGI_TUR)]);
                dusmanUcaklari.remove(ouduc_i);
                patlamaEfektiBaslasin = true;
                break;
            }
        }
    }
    private void patlamaEfektiCiz() {
        for(int pec_i = 0; pec_i < patlamaEfektleri.size(); ++ pec_i) {
            canvas.drawBitmap(patlamaAnimasyon[patlamaEfektleri.get(pec_i).get(PATLAMAEFEKTİ_AKS)], patlamaEfektleri.get(pec_i).get(PATLAMAEFEKTI_X), patlamaEfektleri.get(pec_i).get(PATLAMAEFEKTI_Y), null);
            if(patlamaEfektleri.get(pec_i).get(PATLAMAEFEKTİ_AKS) < patlamaAnimasyonSayisi -1) {
                patlamaEfektleri.get(pec_i).set(PATLAMAEFEKTİ_AKS, patlamaEfektleri.get(pec_i).get(PATLAMAEFEKTİ_AKS) + 1);
            } else {
                patlamaEfektleri.remove(pec_i);
                break;
            }
        }
    }

    private void arkaplaniKaydir() {
        if(kaydirmaMiktarı <= 1920 - kaydirmaHizi) {
            kaydirmaMiktarı += kaydirmaHizi;
        } else {
            kaydirmaMiktarı = 0;
        }
    }
    private void oyuncuUcagiHareketKontrolu() {
        yukariKontrolu();
        asagiKontrolu();
    }
    private void yukariKontrolu() {
        if(yukariCik == true && oyuncucagiy > 0)
            oyuncucagiy -= 10;
    }
    private void asagiKontrolu() {
        if(asagiin == true && oyuncucagiy  < 1080 - oyuncucagi[ucaginDurumu].getHeight())
            oyuncucagiy += 10;
    }
    private void dusmanUcaklariniIlerlet() {
        for(duec_i = 0; duec_i < dusmanUcaklari.size(); ++duec_i) {
            if(dusmanUcaklari.get(duec_i).get(DUSMANUCAGI_KONUMX) > - ucakAnimasyonları[dusmanUcaklari.get(duec_i).get(DUSMANUCAGI_TUR)][dusmanUcaklari.get(duec_i).get(DUSMANUCAGI_AKS)].getWidth()) {
                dusmanUcaklari.get(duec_i).set(DUSMANUCAGI_KONUMX, dusmanUcaklari.get(duec_i).get(DUSMANUCAGI_KONUMX) + dusmanUcaklari.get(duec_i).get(DUSMANUCAGI_HIZ));
            } else {
                dusmanUcaklari.get(duec_i).set(DUSMANUCAGI_KONUMX, 1920);
            }
        }
    }
    private void oyuncuMermileriniIlerlet() {
        for (int omi_i = 0; omi_i < oyuncuMermileri.size(); ++omi_i) {
            if(oyuncuMermileri.get(omi_i).get(OYUNCUMERMISI_KONUMX) < 1920) {
                oyuncuMermileri.get(omi_i).set(OYUNCUMERMISI_KONUMX, oyuncuMermileri.get(omi_i).get(OYUNCUMERMISI_KONUMX) + oyuncuMermileri.get(omi_i).get(OYUNCUMERMISI_HIZ));
            } else {
                oyuncuMermileri.remove(omi_i);
                break;
            }
        }
    }
    private void dusmanMermileriniIlerlet() {
        for (int dmi_i = 0; dmi_i < dusmanMermileri.size(); ++ dmi_i) {
            if(dusmanMermileri.get(dmi_i).get(DUSMANMERMISI_KONUMX) > - mermiAnimasyon[dusmanMermileri.get(dmi_i).get(DUSMANMERMISI_TUR)].getWidth()) {
                dusmanMermileri.get(dmi_i).set(DUSMANMERMISI_KONUMX, dusmanMermileri.get(dmi_i).get(DUSMANMERMISI_KONUMX)+ dusmanMermileri.get(dmi_i).get(DUSMANMERMISI_HIZ));
            } else {
                dusmanMermileri.remove(dmi_i);
                break;
            }
        }
    }
    private void dusmanUcagiCiz() {
        for(duc_i = 0; duc_i < dusmanUcaklari.size(); ++duc_i) {
            ucagiCizdir();
            animasyonSayaciGuncellemesi();
        }
    }
    private void animasyonSayaciGuncellemesi() {
        if(dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_AKS) < animasyonKareSayisi[dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_TUR)] - 1) {
            dusmanUcaklari.get(duc_i).set(DUSMANUCAGI_AKS, dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_AKS) + 1);
        } else {
            dusmanUcaklari.get(duc_i).set(DUSMANUCAGI_AKS, 0);
        }
    }
    private void ucagiCizdir() {
        canvas.drawBitmap(ucakAnimasyonları[dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_TUR)][dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_AKS)], dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_KONUMX), dusmanUcaklari.get(duc_i).get(DUSMANUCAGI_KONUMY), null);

    }
    private void oyuncuUcagiCiz() {
        canvas.drawBitmap(oyuncucagi[ucaginDurumu], oyuncucagix, oyuncucagiy,null);
        if(framebekletici < 10) {
            framebekletici++;
        } else {
            oyuncuUcagiFrameKontrolu(false);
        }

    }
    private void arkaplaniCiz() {

        arkaplaniKaydiranBolum();
        arkaplaniTamamlayanBolum();
    }
    private void arkaplaniKaydiranBolum() {
        arkaplanKaynak.set(kaydirmaMiktarı,0, arkaplan.getWidth(), arkaplan.getHeight());
        arkaplanHedef.set(0, 0, 1920-kaydirmaMiktarı, 1080);
        canvas.drawBitmap(arkaplan, arkaplanKaynak, arkaplanHedef, null);
    }
    private void arkaplaniTamamlayanBolum() {
        arkaplanKaynak.set(0,0, kaydirmaMiktarı, arkaplan.getHeight());
        arkaplanHedef.set(1920-kaydirmaMiktarı, 0, 1920, 1080);
        canvas.drawBitmap(arkaplan, arkaplanKaynak, arkaplanHedef, null);
    }
    public void keyPressed(int key) {

    }
    public void keyReleased(int key) {

    }
    public boolean backPressed() {
        return true;
    }
    public void surfaceChanged(int width, int height) {

    }
    public void surfaceCreated() {

    }
    public void surfaceDestroyed() {
        root.oyunmuzigi.stop();
    }
    public void dusmanUcagiUret() {
        if((suankiZaman - baslangicZamani) < 2500) {
            suankiZaman = System.currentTimeMillis();
            return;
        }
        dusmanYeniUcak = new Vector<Integer>();
        int ucakTuru = (int)(Math.random() * ucakCesitleri);
        dusmanYeniUcak.add(ucakTuru); //Uçağın türü;
        dusmanYeniUcak.add(0); //Animasyon sayacı
        dusmanYeniUcak.add(1920); // X Konumu
        dusmanYeniUcak.add((int)(Math.random() * (1080 - ucakAnimasyonları[ucakTuru][0].getHeight()))); // Y Konumu
        dusmanYeniUcak.add(-10); //Uçağın Hızı
        dusmanYeniUcak.add(20); //Tekrar ateş süresi
        dusmanUcaklari.add(dusmanYeniUcak);
        baslangicZamani = System.currentTimeMillis();

    }
    private void oyuncuMermileriUret() {
        oyuncuYeniMermi = new Vector<Integer>();
        int oyuncuMermiTuru = 0;
        oyuncuYeniMermi.add(oyuncuMermiTuru); //Merminin Türü
        oyuncuYeniMermi.add(0); // Merminin aks'si
        oyuncuYeniMermi.add(oyuncucagix + oyuncucagi[ucaginDurumu].getWidth() / 6); // Dogma X
        oyuncuYeniMermi.add(oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() / 3); // Dogma Y
        oyuncuYeniMermi.add(oyuncucagix + oyuncucagi[ucaginDurumu].getWidth() / 6); // X Konumu
        oyuncuYeniMermi.add(oyuncucagiy + oyuncucagi[ucaginDurumu].getHeight() / 3); // Y Konumu
        oyuncuYeniMermi.add(30); // Hız
        oyuncuYeniMermi.add(5); //Merminin gücü
        oyuncuMermileri.add(oyuncuYeniMermi); // MERMİ LİSTEYE EKLENDİ

    }
    private void dusmanMermileriUret(int KonumX, int KonumY) {
        dusmanYeniMermi = new Vector<Integer>();
        int dusmanMermiTuru = 0;
        dusmanYeniMermi.add(dusmanMermiTuru); //Merminin Türü
        dusmanYeniMermi.add(0); // Merminin aks'si
        dusmanYeniMermi.add(KonumX - 100); // Dogma X
        dusmanYeniMermi.add(KonumY + 50); // Dogma Y
        dusmanYeniMermi.add(KonumX - 100); // X Konumu
        dusmanYeniMermi.add(KonumY + 50); // Y Konumu
        dusmanYeniMermi.add(-28); // Hız
        dusmanMermileri.add(dusmanYeniMermi); // MERMİ LİSTEYE EKLENDİ

    }
    private void bossMermisiUret(int KonumX, int KonumY) {
        for(int dbm_i = 0; dbm_i < 4;++dbm_i) {
            int y = bossAnimasyonlari[bossListesi.get(0).get(BOSS_TUR)][bossListesi.get(0).get(BOSS_AKS)].getHeight() / 4;
            dusmanYeniMermi = new Vector<Integer>();
            int dusmanMermiTuru = 0;
            dusmanYeniMermi.add(dusmanMermiTuru); //Merminin Türü
            dusmanYeniMermi.add(0); // Merminin aks'si
            dusmanYeniMermi.add(KonumX - 100); // Dogma X
            dusmanYeniMermi.add(KonumY + y * dbm_i + mermiAnimasyon[0].getHeight() / 2); // Dogma Y
            dusmanYeniMermi.add(KonumX - 100); // X Konumu
            dusmanYeniMermi.add(KonumY + y * dbm_i + mermiAnimasyon[0].getHeight() / 2); // Y Konumu
            dusmanYeniMermi.add(-30); // Hız
            dusmanMermileri.add(dusmanYeniMermi); // MERMİ LİSTEYE EKLENDİ
            bossatesibz = System.currentTimeMillis(); // Sayac Sıfırlandı
        }
        bossListesi.get(0).set(BOSS_DAVRANIS, 1);
    }

    public void touchDown(int x, int y, int id) {
        oyuncuUcagiYonKontrolu(x, y);
        oyuncuUcagiAtesKontrolu(x, y);
        oyunSonuKontrolleri(x, y);
        menuKontrolleri(x, y);
    }

    private void menuKontrolleri(int x, int y) {
        if(x > root.xOranla(ayarlarbutonX) && x < root.xOranla(ayarlarbutonX + ayarlarbutonu.getWidth()) &&
           y > root.yOranla(ayarlarbutonY) && y < root.yOranla(ayarlarbutonY + ayarlarbutonu.getHeight())) {
            oyundevamediyor = false;
        }

        if(x > root.xOranla(duraklatmamenusuX + 55) && x < root.xOranla(duraklatmamenusuX + 55 + 70) &&
           y > root.yOranla(duraklatmamenusuY + 75) && y < root.yOranla(duraklatmamenusuY + 75 + 65)) {
            if(oyunbitti == false ) oyundevamediyor = true;
        }
        // Yeniden başlatma
        if(x > root.xOranla(duraklatmamenusuX + 160) && x < root.xOranla(duraklatmamenusuX + 160 + 65) &&
           y > root.yOranla(duraklatmamenusuY + 75) && y < root.yOranla(duraklatmamenusuY + 75 + 65) && oyundevamediyor == false) {
            root.oyunmuzigi.stop();
            root.canvasManager.setCurrentCanvas(new GameCanvas(root));
        }
        //Menuye Dönme
        if(x > root.xOranla(duraklatmamenusuX + 270) && x < root.xOranla(duraklatmamenusuX + 270 + 65) &&
           y > root.yOranla(duraklatmamenusuY + 75) && y < root.yOranla(duraklatmamenusuY + 75 + 65) && oyundevamediyor == false) {
            root.oyunmuzigi.stop();
            root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
        }


        }

    private void oyunSonuKontrolleri(int x, int y) {
        if(oyunbitti == true && oyundevamediyor == false) {
            //Yeniden Baslatma durumuna tıklama
            if(x > root.xOranla(puantablosuX + 150) && x < root.xOranla(puantablosuX + 150 + 65) &&
               y > root.yOranla(puantablosuY + 480) && y < root.yOranla(puantablosuY + 480 + 60)) {
                root.oyunmuzigi.stop();
                root.canvasManager.setCurrentCanvas(new GameCanvas(root));
            }
            //menuye donme tıklama durumu
            if(x > root.xOranla(puantablosuX + 270) && x < root.xOranla(puantablosuX + 270 + 70) &&
               y > root.yOranla(puantablosuY + 480) && y < root.yOranla(puantablosuY + 480 + 60)) {
                root.oyunmuzigi.stop();
                root.canvasManager.setCurrentCanvas(new MenuCanvas(root));
            }
        }
    }

    private void oyuncuUcagiAtesKontrolu(int x, int y) {
        if( x > root.xOranla(atesButonX) && x < root.xOranla(atesButonX + atesButon.getWidth()) && y > root.yOranla(atesButonY) && y < root.yOranla(atesButonY + atesButon.getHeight()) && !muhimmatbitti) {
            oyuncuMermileriUret();
            muhimmatSeviyesiGuncelle();
            root.sesefektcalar.play(root.sesEfektListesi[root.SESEFEKTİ_ATES]);
        }
    }

    public void oyuncuUcagiYonKontrolu(int x, int y) {
        if( y > root.yOranla(yukariButonY) && y < root.yOranla(yukariButonY + yukariButon.getHeight()) && x > root.xOranla(yukariButonX) && x < root.xOranla(yukariButonX + yukariButon.getWidth())) {
            yukariCik = true;
        }
        if( y > root.yOranla(asagiButonY) && y < root.yOranla(asagiButonY + asagiButon.getHeight()) && x > root.xOranla(asagiButonX) && x < root.xOranla(asagiButonY + asagiButon.getWidth())){
            asagiin = true;
        }
    }
    public void touchMove(int x, int y, int id) {
       // touchDown(x, y, id);
    }
    public void touchUp(int x, int y, int id) {
        oyuncuUcagiYonKontrolReset();


    }
    public void oyuncuUcagiYonKontrolReset() {
        if( yukariCik == true){
            yukariCik = false;
        }
        if( asagiin == true){
            asagiin = false;
        }
    }
    public void pause() {
        root.oyunmuzigi.stop();
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
