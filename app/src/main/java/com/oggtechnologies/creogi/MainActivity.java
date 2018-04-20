package com.oggtechnologies.creogi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.oggtechnologies.creogi.entities.*;
import com.oggtechnologies.creogi.worldGeneration.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    GameView gameView;
    Random random = new Random();
    Matrix matrix = new Matrix();
    public Toast mytoast;

    private static int gameViewWidth, gameViewHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    class GameView extends SurfaceView implements Runnable {

        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        Canvas canvas;
        Paint paint;
        int fps, ups;

        Boolean firstFrame = true;
        ArrayList<Entity> entities = new ArrayList<>();
        TileMap tileMap = new TileMap(1024, 1024);
        Player player = new Player(3.5f, 20);

        // If this is not null draw the open inventory to the screen and let onclick control that
        Inventory currentOpenInventory = null;
        // If this is true draw the game menu on the screen and let onclick control that
        Boolean displayMenu = false;


        public GameView(Context context) {
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
            entities.add(player);
            GlobalGameData.setPlayer(player);
            GlobalGameData.setTileMap(tileMap);
            //todo Let the user set the seed when creating the world
            GlobalGameData.setSeed(13371337);

            boolean generateWorld = false;
            if (generateWorld) {
                WorldGenerator worldGenerator = new WorldGenerator();
                worldGenerator.generateWorld();
            }

            UserOptions.setZoomLevel(8);

            playing = true;

        }

        @Override
        public void run() {
            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();
            final double ns = 1000000000.0 / 60.0;
            double delta = 0;
            int frames = 0;
            int updates = 0;
            while (playing) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                //runs 60 times each second
                while (delta >= 1) {
                    update();
                    GlobalGameData.incrementUpdate();
                    updates++;
                    delta--;
                }
                draw();
                frames++;

                //runs 1 time each second
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println(updates + " ups, " + frames + " fps");
                    fps = frames;
                    ups = updates;
                    updates = 0;
                    frames = 0;
                }
            }


        }

        public void update() {
            for (Entity e : entities) {
                e.update();
            }

        }

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                if (firstFrame) {
                    gameViewWidth = canvas.getWidth();
                    gameViewHeight = canvas.getHeight();
                    Textures.setContext(getContext());
                    firstFrame = false;
                }

                canvas.drawColor(Color.argb(255, 26, 140, 182));
                tileMap.drawTiles(canvas, paint);
                player.draw(canvas, paint);
                player.drawGUI(canvas, paint);

                //Displays the UPS and FPS
                paint.setColor(Color.BLACK);
                paint.setTextSize(45);
                canvas.drawText("UPS:" + ups + "  FPS:" + fps, 20, 40, paint);
                canvas.drawText("X:" + player.getX() + "  Y:" + player.getY(), 20, 80, paint);

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        private void initiateTextures() {

        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (displayMenu) {
                // The game menu is open. Touch events handle that
                // TODO make a menu with ontouch function and call it from here
            } else if (currentOpenInventory != null) {
                // An inventory is displayed. Touch events handle that
                currentOpenInventory.onTouch(motionEvent);
            } else {
                // No inventory or menu is open, just the main game.
                // Touch events control the player
                player.onTouch(motionEvent);
            }
            return true;
        }

    }

    public static int getGameViewWidth() {
        return gameViewWidth;
    }

    public static int getGameViewHeight() {
        return gameViewHeight;
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }

}


