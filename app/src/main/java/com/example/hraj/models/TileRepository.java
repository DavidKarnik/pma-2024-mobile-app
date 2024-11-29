package com.example.hraj.models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.hraj.utils.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TileRepository {

    private static TileRepository instance;
    private TileDao tileDao;
    private ExecutorService executorService;
    //    Each Handler instance is associated with a single thread
    //    Handler umožňuje komunikaci mezi různými vlákny
    private Handler mainHandler;

    public TileRepository(Context appContext) {
        AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        tileDao = appDatabase.tileDao();

        // Executor pro asynchronní operace
        executorService = Executors.newSingleThreadExecutor();
        // Handler pro práci na hlavním vlákně
        // Looper je mechanizmus, který umožňuje vláknu opakovaně čekat na a zpracovávat zprávy nebo úlohy.
        // Každé vlákno může mít vlastní Looper, který vytváří tzv. „zprávový cyklus“ (message loop), což
        // je cyklus, který čeká na příchozí zprávy (úlohy) a pak je zpracovává.
        mainHandler = new Handler(Looper.getMainLooper());

        // Naplnění databáze dlaždicemi
        populateDatabase();
    }

    // Singleton metoda pro získání instance
    public static synchronized TileRepository getInstance(Context context) {
        if (instance == null) {
            instance = new TileRepository(context.getApplicationContext());
        }
        return instance;
    }

    private void populateDatabase() {
//        Tile[] defaultTiles = {
//                new Tile("Tile 1", "Krátký popis", "Dlouhý popis dlaždice 1", 5),
//                new Tile("Tile 2", "Krátký popis", "Dlouhý popis dlaždice 2", 3),
//                new Tile("Tile 3", "Krátký popis", "Dlouhý popis dlaždice 3", 7),
//                new Tile("Tile 4", "Krátký popis", "Dlouhý popis dlaždice 4", 2)
//        };
        List<Tile> staticTiles = loadTilesStatic();
        for (Tile tile : staticTiles) {
            executorService.execute(() -> tileDao.insertTile(tile));
        }
    }

    public void loadTilesAsync(Callback<List<Tile>> callback) {
        executorService.execute(() -> {
            // Načti data z databáze
            List<Tile> tiles = tileDao.getAllTiles();
            // Vrať data pomocí callbacku na hlavním vlákně
            mainHandler.post(() -> callback.onComplete(tiles));
        });
    }

    public interface Callback<T> {
        void onComplete(T result);
    }

    public List<Tile> loadTilesStatic() {
        List<Tile> tiles = new ArrayList<>();
        String description1 = "Jedno dítě je liška a má několik minut na schování v předem vymezeném prostoru. Ostatní děti mají za úkol najít lišku, přičemž mohou používat stopovací nápovědy (například barevné papírky, šipky nebo provázky), které liška nechává za sebou. Kdo lišku najde, stává se liškou v dalším kole.";
        String description2 = "Jeden hráč je král a říká příkazy, například „Král říká: dotkněte se nohou“ nebo „Poskočte třikrát“. Ostatní hráči musí poslouchat, ale pokud příkaz nezačne „Král říká“, hráč, který příkaz splní, vypadává. Cílem je zůstat ve hře co nejdéle.";
        String description3 = "Jeden hráč stojí zády ke skupině a počítá do deseti. Ostatní se snaží během této doby dostat co nejblíže k němu. Když se vedoucí otočí, všichni musí zůstat nehybní jako sochy. Kdo se pohne, musí se vrátit na start. Vítězí ten, kdo se jako první dotkne vedoucího.";
        String description4 = "Hráči se rozdělí do dvou týmů a chytnou se lana. Uprostřed je značka (například šátek), kterou musí jeden tým přetáhnout za čáru na své straně. Hra posiluje týmovou spolupráci a vyžaduje fyzickou sílu. Můžete ji hrát na louce nebo v parku.";
        String description5 = "Hráč, který je „baba“, honí ostatní děti a snaží se jich dotknout, aby předal „babu“ dál. V případě varianty ledová, chycený hráč zůstává nehybný, dokud ho jiný hráč neosvobodí dotykem. U varianty ohnivá musí chycený hráč splnit úkol, aby se mohl vrátit do hry.";
        String description6 = "Jeden hráč se schová a během úkrytu zanechává stopy, například nakreslené šipky na zem nebo položení malých předmětů (kamínků, barevných papírků). Ostatní hráči musí stopy sledovat a najít skrytého hráče. Hru lze hrát v lese, na zahradě nebo v parku.";
        tiles.add(new Tile("Hon na lišku", "Skupina dětí hledá „lišku“, která se schovává v určené oblasti.", description1, 10));
        tiles.add(new Tile("Král říká", "Hráči plní příkazy „krále“, ale pouze pokud před nimi zazní „Král říká“.", description2, 4));
        tiles.add(new Tile("Sochy", "Hráči se musí zastavit v pohybu, když se otočí vedoucí hry.", description3, 7));
        tiles.add(new Tile("Přetahovaná", "Dva týmy se snaží přetáhnout lano na svou stranu.", description4, 6));
        tiles.add(new Tile("Na babu", "Jeden hráč je „baba“ a snaží se dotknout ostatních, aby je chytil.", description5, 6));
        tiles.add(new Tile("Skrývačky s indiciemi", "Jeden hráč se schová a zanechá za sebou stopy, které ostatní sledují.", description6, 8));
        return tiles;
    }

    public void insertTileAsync(Tile tile, Callback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                tileDao.insertTile(tile);
                callback.onComplete(true); // Úspěch
            } catch (Exception e) {
                callback.onComplete(false); // Selhání
            }
        });
    }

    public void deleteTileByIdAsync(int tileId, Callback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                tileDao.deleteTileById(tileId);
                mainHandler.post(() -> callback.onComplete(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onComplete(false));
            }
        });
    }

    public void updateTileAsync(Tile tile, Callback<Boolean> callback) {
        executorService.execute(() -> {
            try {
                tileDao.update(tile);
                mainHandler.post(() -> callback.onComplete(true));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onComplete(false));
            }
        });
    }

}
