package main;

import static main.Constants.CELL_SIZE;


public class Cell {

    private int x;
    private int y;
    private int state;

    public Cell (int x, int y, int state){
        this.x = x;
        this.y = y;
        this.state = state;
    }



    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHeight(){
        return CELL_SIZE;
    }

    public int getWidth(){
        return CELL_SIZE;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }


    public void update(boolean have_to_decrease){
        if(have_to_decrease && this.state == 2){
            this.state--;
        }
        if (have_to_decrease && this.state==1){
            this.state--;
        }

    }


    public Sprite getSprite(){
        switch (this.state){
            case 1:
                return Sprite.BODY;
            case 2:
                return Sprite.BODY2;
            case -2:
                return Sprite.BULLET;
            case -1:
                return Sprite.BLOCK;
            case -3:
                return Sprite.BLACK;
            default:
                return null;
        }


    }
}
