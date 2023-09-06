package com.example.se233casestudy4.view;

import com.example.se233casestudy4.Launcher;
import com.example.se233casestudy4.model.Boy;
import com.example.se233casestudy4.model.Characters;
import com.example.se233casestudy4.model.Keys;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Characters character;
    private Boy boy;
    private Image platformImg;
    private Keys keys;
    public Platform() {
        keys = new Keys();
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character = new Characters(30,30,0,0,KeyCode.A,KeyCode.D,KeyCode.W);
        boy = new Boy(700,30,0,0,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP);
        getChildren().addAll(backgroundImg,character,boy);
    }
    public Characters getCharacter()
    {
        return character;
    }

    public Boy getBoy() {
        return boy;
    }

    public Keys getKeys() {
        return keys;
    }
}
