package com.example.se233casestudy4.model;

import com.example.se233casestudy4.Launcher;
import com.example.se233casestudy4.view.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Boy extends Pane {
  private static final Logger logger = LogManager.getLogger(Boy.class);
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image boyImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 5;
    int yMaxVelocity = 17;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;

    public Boy(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.boyImg = new Image(Launcher.class.getResourceAsStream("assets/boySheet.png"));
        this.imageView = new AnimatedSprite(boyImg, 3,3,1,offsetX,offsetY,28, 15);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }
       public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int)getWidth();
        }
    }

    public void jump() {
        if(canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }
    public void checkReachHighest() {
        if(isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public void setImageView(AnimatedSprite imageView) {
        this.imageView = imageView;
    }

    public void setLeftKey(KeyCode leftKey) {
        this.leftKey = leftKey;
    }
     public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
 }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public void setUpKey(KeyCode upKey) {
        this.upKey = upKey;
    }

    public void moveY() {
        setTranslateY(y);
        if(isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        } else if(isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }
    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }
    public void repaint() {
        moveX();
        moveY();
    }
    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }

    public Image getBoyImg() {
        return boyImg;
    }

    public void setBoyImg(Image boyImg) {
        this.boyImg = boyImg;
    }

    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }
    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void moveX() {
        setTranslateX(x);
        if(isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }

    }

}
