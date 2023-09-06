package com.example.se233casestudy4.model;

import com.example.se233casestudy4.view.Platform;

public class DrawingLoop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform) {
        this.platform = platform;
        frameRate = 30;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void checkDrawCollisions(Characters character, Boy boy) {
        character.checkReachGameWall();
        character.checkReachHighest();
        character.checkReachFloor();

        boy.checkReachGameWall();
        boy.checkReachHighest();
        boy.checkReachFloor();
    }
    private void paint(Characters character, Boy boy) {
        character.repaint();
        boy.repaint();
    }
    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getCharacter(), platform.getBoy());
            paint(platform.getCharacter(), platform.getBoy());
            time = System.currentTimeMillis() - time;

            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
