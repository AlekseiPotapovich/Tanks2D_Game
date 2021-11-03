package main;

import static main.Constants.CELLS_COUNT_X;
import static main.Constants.CELLS_COUNT_Y;

public class TankBot {
    private int x,y, degree;
    private int bufX, bufY;
    private boolean isAlive;
    public TankBot(int x, int y){
        this.x = x;
        this.y = y;
        this.isAlive = true;
    }
    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    public boolean chekIsAlive(){
        return isAlive;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDegree(){
        return degree;
    }
    public void view(){
        GUI.setState(x, y,2);
    }
    public boolean prepareForShoot(int xFirst, int yFirst){
//        for(int i = 0; i < CELLS_COUNT_Y; i++)
//            if(((GUI.getState(i, y) == 1) || (GUI.getState(x, i) == 1)) ){// && reloading == 1) {
//                if(y > yFirst)
//                    degree = 180;
//                else if(y < yFirst)
//                    degree = 1;
//                if(x > xFirst)
//                    degree = 270;
//                else if(x < xFirst)
//                    degree = 90;
//                //reloading = 0;
//                return true;
//            }
        for(int i = 0; i < CELLS_COUNT_X; i++)
            for(int j = 0; j < CELLS_COUNT_Y; j++)
                if(((GUI.getState(i, y) == 1) || (GUI.getState(x, j) == 1)) ){
                    if(y > yFirst)
                        degree = 180;
                    else if(y < yFirst)
                        degree = 1;
                    if(x > xFirst)
                        degree = 270;
                    else if(x < xFirst)
                        degree = 90;
                    //reloading = 0;
                    return true;
                }
        return false;

    }
    public boolean move(){
        if((x > 0 && x < CELLS_COUNT_X-1) && (y > 0 && y < CELLS_COUNT_Y-1)){

            bufX = x;
            bufY = y;
            //reloading = 1;
            int step = 1 + (int) (Math.random() * 4);

            switch (step){
                case 1:
                    degree = 1;
                    GUI.roted(x,y,degree, "tank2+");

                    if((GUI.getState(x, y + 1) == -1)){
                        //shoot(degree2,1,2);
                    }
                    y++;
                    break;
                case 2:
                    degree = 90;
                    GUI.roted(x,y,degree, "tank2+");
                    //shoot(degree2,1);
                    x++;
                    break;
                case 3:
                    degree = 180;
                    GUI.roted(x,y,degree, "tank2+");
                    //shoot(degree2,1);
                    y--;
                    break;
                case 4:
                    degree = 270;
                    GUI.roted(x,y,degree, "tank2+");
                    //shoot(degree2,1);
                    x--;
                    break;
            }
            if(isAlive)
                GUI.setState(x,y,2);
//            if(isFirstAlive)
//                GUI.setState(x,y,1);
//            if(isSecondAlive)
//                GUI.setState(x,y,2);

        }else {
            x = bufX;
            y = bufY;
        }
        return false;
    }
}
