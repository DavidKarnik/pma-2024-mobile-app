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
//        populateDatabase();
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
        String description1 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Mauris tincidunt sem sed arcu. Nullam justo enim, consectetuer nec, ullamcorper ac, vestibulum in, elit. Quisque porta. Phasellus rhoncus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Duis viverra diam non justo. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Nulla est. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonummy ac, erat. Mauris suscipit, ligula sit amet pharetra semper, nibh ante cursus purus, vel sagittis velit mauris vel metus. Integer vulputate sem a nibh rutrum consequat. Curabitur sagittis hendrerit ante.\n" + "Praesent id justo in neque elementum ultrices. Sed elit dui, pellentesque a, faucibus vel, interdum nec, diam. Praesent vitae arcu tempor neque lacinia pretium. Duis condimentum augue id magna semper rutrum. Etiam posuere lacus quis dolor. Mauris metus. Donec quis nibh at felis congue commodo. Etiam bibendum elit eget erat. Mauris elementum mauris vitae tortor. Integer malesuada. Curabitur sagittis hendrerit ante. Ut tempus purus at lorem. Fusce consectetuer risus a nunc. In convallis. Suspendisse nisl. Nullam faucibus mi quis velit. Maecenas aliquet accumsan leo.";
        String description2 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque sapien. Integer malesuada. Vivamus ac leo pretium faucibus. Donec vitae arcu. In laoreet, magna id viverra tincidunt, sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Duis condimentum augue id magna semper rutrum. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonummy ac, erat. Nullam feugiat, turpis at pulvinar vulputate, erat libero tristique tellus, nec bibendum odio risus sit amet ante. Praesent dapibus. Proin mattis lacinia justo. Integer pellentesque quam vel velit. Morbi scelerisque luctus velit. Nulla pulvinar eleifend sem.\n" + "Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Nulla non arcu lacinia neque faucibus fringilla. Integer in sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent in mauris eu tortor porttitor accumsan. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Nullam eget nisl. Fusce aliquam vestibulum ipsum. Donec vitae arcu. Mauris elementum mauris vitae tortor.";
        String description3 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean id metus id velit ullamcorper pulvinar. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat. Quisque tincidunt scelerisque libero. Integer rutrum, orci vestibulum ullamcorper ultricies, lacus quam ultricies odio, vitae placerat pede sem sit amet enim. Duis condimentum augue id magna semper rutrum. Etiam ligula pede, sagittis quis, interdum ultricies, scelerisque eu. Praesent id justo in neque elementum ultrices. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Phasellus et lorem id felis nonummy placerat. Sed elit dui, pellentesque a, faucibus vel, interdum nec, diam. Duis pulvinar.";
        String description4 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed convallis magna eu sem. Maecenas sollicitudin. Donec iaculis gravida nulla. Etiam posuere lacus quis dolor. Aliquam erat volutpat. Fusce tellus. Phasellus et lorem id felis nonummy placerat. In rutrum. Etiam ligula pede, sagittis quis, interdum ultricies, scelerisque eu. Maecenas ipsum velit, consectetuer eu lobortis ut, dictum at dui. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat. Curabitur sagittis hendrerit ante.";
        String shortDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.";
        tiles.add(new Tile("Tile 1", shortDescription, description1, 5));
        tiles.add(new Tile("Tile 2", shortDescription, description2, 3));
        tiles.add(new Tile("Tile 3", shortDescription, description3, 7));
        tiles.add(new Tile("Tile 4", shortDescription, description4, 2));
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
}
