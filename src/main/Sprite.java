package main;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum Sprite {
    BODY("tank+1"),BODY2("tank2+1"), CHERRIES("cherries"),
        BLOCK("block"), BULLET("bullet+1"), BLACK("black");

    private Texture texture;

    Sprite(String textureName){
        setTexture(textureName);
    }

    public void setTexture(String name){
        try {
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+name+".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Texture getTexture(){
        return this.texture;
    }
}