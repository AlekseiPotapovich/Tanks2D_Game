package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.util.ArrayList;

import static main.Constants.*;




public class Main {
    private static boolean isExitRequested = false, isRestartRequest = false;
    private boolean isShooting = false;
    private boolean isShooting2 = false;
    private int x, y;
    //private int x2 = 25, y2 = 17, bufX, bufY, reloading;
    //private int bulletX, bulletY;
    private final ArrayList<Integer> bulletCoordinates = new ArrayList<>();
    private int direction = 4;
    private int degree = 1, degree2 = 1, bulletDegree;
    private static boolean have_to_decrease = true;
    private static boolean botTurn = false;
    private static boolean isFirstAlive = true;// isSecondAlive = true, isThirdAlive = true;

    TankBot bot1 = new TankBot(40,22);
    TankBot bot2 = new TankBot(22,5);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::run);

    }
    public void run(){
        //GUI.init();
        //generate_new_obj();
        for(int i = 0; i < COUNT_OF_BOTS * 2; i++){
            bulletCoordinates.add(i,0);
        }




        while (!isExitRequested){
            GUI.init();
            x = 1; y = 1;
            isFirstAlive = true;// isSecondAlive = true; isThirdAlive = true;
            bot1.setIsAlive(true);
            bot2.setIsAlive(true);

            isRestartRequest = false;

        while(!isRestartRequest ){

            input();
            move();

            if(isFirstAlive){
                GUI.setState(x,y,1);
                GUI.roted(x, y, degree, "tank+");
            }

            if(bot1.chekIsAlive())
                //GUI.setState(bot1.getX(), bot1.getY(), 2);
                bot1.view();
            if(bot2.chekIsAlive())
                GUI.setState(bot2.getX(), bot2.getY(), 2);
                //bot2.view();

            shootingFirst();

            //isShooting2 = prepareForShoot();
            isShooting2 = bot1.prepareForShoot(x, y);
            shootingForBot(bot1);

            isShooting2 = bot2.prepareForShoot(x, y);
            shootingForBot(bot2);


            if (botTurn && bot1.chekIsAlive()){
                botTurn = bot1.move();
                if(bot2.chekIsAlive())
                    botTurn = bot2.move();

                //bot1.move();

            }
            if(!bot1.chekIsAlive() && botTurn && bot2.chekIsAlive())
                botTurn = bot2.move();
//            if(botTurn && bot2.chekIsAlive())
//                botTurn = bot2.move();



            //GUI.setState(x, y,1);
            //GUI.roted(x, y, degree, "tank+");
            GUI.draw();
            GUI.update(have_to_decrease);

            if(!isFirstAlive){
                bot1.setIsAlive(false);
                bot2.setIsAlive(false);
                //isSecondAlive = false;
                //isThirdAlive = false;

                GUI.text("GAME OVER");

            }
        }
        }
    }
    private void shootingForBot(TankBot bot){

        bulletCoordinates.set(2, bot.getX());
        bulletCoordinates.set(3, bot.getY());

        while (isShooting2 && bot.chekIsAlive()){
            GUI.roted(bot.getX(), bot.getY(), bot.getDegree(), "tank2+");
            bulletDegree = bot.getDegree();
            isShooting2 = shoot(bulletDegree, bulletCoordinates,2);
            if(bot1.chekIsAlive())
                bot1.view();
            if(bot2.chekIsAlive())
                bot2.view();
            //GUI.setState(bot.getX(), bot.getY(),2);

            if(isFirstAlive){
                input();
                shootingFirst();
                move();
                GUI.setState(x,y,1);
                GUI.roted(x, y, degree, "tank+");
                GUI.draw();
            }
            GUI.draw();
            //secondTurn = false;

        }
    }
    private void shootingFirst(){
        bulletCoordinates.set(0,x);
        bulletCoordinates.set(1,y);
        bulletDegree = degree;

        while (isShooting && isFirstAlive){
            isShooting = shoot(bulletDegree, bulletCoordinates,1);
            input();
            move();
            GUI.setState(x,y,1);
            GUI.roted(x, y, degree, "tank+");
            if(bot1.chekIsAlive())
                bot1.view();
                //GUI.setState(bot1.getX(), bot1.getY(),2);
            if(bot2.chekIsAlive())
                bot2.view();
        }
    }

    private boolean shoot(int bulletDegree, ArrayList<Integer> bulletCoordinates, int turn){

        int bulletX;
        int bulletY;
        if(turn == 1){
            bulletX = bulletCoordinates.get(0);
            bulletY = bulletCoordinates.get(1);
        }else{
            bulletX = bulletCoordinates.get(2);
            bulletY = bulletCoordinates.get(3);
        }


        switch (bulletDegree) {
            case 1:
                bulletY++;
                break;
            case 90:
                bulletX++;
                break;
            case 180:
                bulletY--;
                break;
            case 270:
                bulletX--;
                break;
        }

        if(bulletX < 0 || bulletX >= CELLS_COUNT_X || bulletY < 0 || bulletY >= CELLS_COUNT_Y) {
            //isShooting = false;
            return false;
        }

        boolean isShooting = GUI.shooting(bulletX, bulletY);
        GUI.setState(bulletX , bulletY, -2);
        GUI.roted(bulletX,bulletY, bulletDegree, "bullet+");

        if(bulletX == bot1.getX() && bulletY == bot1.getY())
            bot1.setIsAlive(false);
            //isSecondAlive = false;
        if(bulletX == bot2.getX() && bulletY == bot2.getY())
            bot2.setIsAlive(false);
            //isThirdAlive = false;
        if(bulletX == x && bulletY == y)
            isFirstAlive = false;
        GUI.draw();
        GUI.update(have_to_decrease);
        //GUI.setState(x,y,1);
        GUI.setState(bulletX  , bulletY, 0);
        if(turn == 1){
            bulletCoordinates.set(0, bulletX);
            bulletCoordinates.set(1, bulletY);
        }else{
            bulletCoordinates.set(2, bulletX);
            bulletCoordinates.set(3, bulletY);
        }

        return isShooting;
    }

    private  void move() {
        //have_to_decrease = true;
        int bX = x;
        int bY = y;


        switch(direction){
            case 0:
                y++;
                degree = 1;
                direction = 4;
                botTurn = true;
                break;
            case 1:
                x++;
                degree = 90;
                direction = 4;
                botTurn = true;
                break;
            case 2:
                y--;
                degree = 180;
                direction = 4;
                botTurn = true;
                break;
            case 3:
                x--;
                degree = 270;
                direction = 4;
                botTurn = true;

                break;
            case 4:
                break;
        }



        if(x<0 || x>=CELLS_COUNT_X || y<0 || y>=CELLS_COUNT_Y){
            x = bX; y = bY;
            //System.exit(1);
        }

        int next_cell_state = GUI.getState(x,y);
        if(next_cell_state < 0){
            x = bX;
            y = bY;
        }
    }


    private void input(){
        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                switch(Keyboard.getEventKey()) {
                    case Keyboard.KEY_ESCAPE:
                        isRestartRequest = true;
                        break;
                    case Keyboard.KEY_UP:
                        if(direction!=2) direction=0;
                        break;
                    case Keyboard.KEY_RIGHT:
                        if(direction!=3) direction=1;
                        break;
                    case Keyboard.KEY_DOWN:
                        if(direction!=0) direction=2;
                        break;
                    case Keyboard.KEY_LEFT:
                        if(direction!=1) direction=3;
                        break;
                    case Keyboard.KEY_SPACE:
                        //cell = GUI.shoot(x, y, degree);
                        isShooting = true;
                        break;

                }
            }
        }


        //isRestartRequest = Display.isCloseRequested();
        isExitRequested =  Display.isCloseRequested();
        if(isExitRequested)
            isRestartRequest = true;
    }

}
