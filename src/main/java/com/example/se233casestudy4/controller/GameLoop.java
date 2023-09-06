package com.example.se233casestudy4.controller;

import com.example.se233casestudy4.model.Boy;
import com.example.se233casestudy4.model.Characters;
import com.example.se233casestudy4.view.Platform;

public class GameLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public GameLoop(Platform platform) {
        this.platform = platform;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }

    private void update(Characters character, Boy boy) {
        if (platform.getKeys().isPressed(character.getLeftKey())) {
            character.setScaleX(-1);
            character.moveLeft();
            platform.getCharacter().trace();
        }
        if (platform.getKeys().isPressed(boy.getLeftKey())) {
            boy.setScaleX(-1);
            boy.moveLeft();
            platform.getBoy().trace();
        }
        if (platform.getKeys().isPressed(character.getRightKey())) {
            character.setScaleX(1);
            character.moveRight();
            platform.getCharacter().trace();
        }
        if (platform.getKeys().isPressed(boy.getRightKey())) {
            boy.setScaleX(1);
            boy.moveRight();
            platform.getBoy().trace();
        }
        if (platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
            character.getImageView().tick();
            character.trace();
        }
        if (platform.getKeys().isPressed(boy.getLeftKey()) || platform.getKeys().isPressed(boy.getRightKey())) {
            boy.getImageView().tick();
            boy.trace();
        }

        if (platform.getKeys().isPressed(character.getUpKey())) {
                character.jump();
        }
        if (platform.getKeys().isPressed(boy.getUpKey())) {
                boy.jump();
        }
        if (!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey())) {
                character.stop();
        }
        if (!platform.getKeys().isPressed(boy.getLeftKey()) && !platform.getKeys().isPressed(boy.getRightKey())) {
                boy.stop();
        }

        }


        @Override
        public void run () {
            while (running) {
                float time = System.currentTimeMillis();
                update(platform.getCharacter(), platform.getBoy());
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

