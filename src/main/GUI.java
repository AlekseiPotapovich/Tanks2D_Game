package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.Random;

import static main.Constants.*;
import static org.lwjgl.opengl.GL11.*;



public class GUI {

    private static Cell[][] cells;
    public static void restart(){

    }


    public static void init(){
        initializeOpenGL();

        cells = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];

        Random rnd = new Random();

        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,rnd.nextInt(100)<INITIAL_SPAWN_CHANCE?-1:0);
            }
        }
    }



    public static void update(boolean have_to_decrease) {
        updateOpenGL();

        for(Cell[] line:cells){
            for(Cell cell:line){
                cell.update(have_to_decrease);
            }
        }
    }


    public static void draw(){

        glClear(GL_COLOR_BUFFER_BIT);

        for(Cell[] line:cells){
            for(Cell cell:line){
                drawElement(cell);
            }
        }
    }
    public static int roted(int x, int y, int degree, String name){
        Cell cell = cells[x][y];
        if(cell.getSprite() == null)
            return 0;
        cell.getSprite().setTexture(name + degree);

        return 0;

    }
    public static boolean shooting(int x, int y){
        int next_cell_state = GUI.getState(x,y);
        if(next_cell_state == -1 || next_cell_state == 1 || next_cell_state == 2){
            GUI.setState(x,y,0);
            return false;
        }

        return true;
    }


    public static void text(String text){
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0,0,0,0);

        cells = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];

        Random rnd = new Random();

        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE,0);
            }
        }

        //G
        for(int j = 14; j < 18; j++){
            cells[5][j]=new Cell(5*CELL_SIZE, j*CELL_SIZE, -3);
        }

        cells[6][18]=new Cell(6*CELL_SIZE, 18*CELL_SIZE, -3);
        cells[7][18]=new Cell(7*CELL_SIZE, 18*CELL_SIZE, -3);
        cells[8][17]=new Cell(8*CELL_SIZE, 17*CELL_SIZE, -3);
        cells[6][13]=new Cell(6*CELL_SIZE, 13*CELL_SIZE, -3);
        cells[7][13]=new Cell(7*CELL_SIZE, 13*CELL_SIZE, -3);
        cells[8][14]=new Cell(8*CELL_SIZE, 14*CELL_SIZE, -3);
        cells[8][15]=new Cell(8*CELL_SIZE, 15*CELL_SIZE, -3);
        cells[7][15]=new Cell(7*CELL_SIZE, 15*CELL_SIZE, -3);

        //A
        for(int j = 13; j < 17; j++){
            cells[10][j]=new Cell(10*CELL_SIZE, j*CELL_SIZE, -3);
        }
        cells[11][17]=new Cell(11*CELL_SIZE, 17*CELL_SIZE, -3);
        cells[12][18]=new Cell(12*CELL_SIZE, 18*CELL_SIZE, -3);
        cells[13][17]=new Cell(13*CELL_SIZE, 17*CELL_SIZE, -3);
        for(int j = 13; j < 17; j++){
            cells[14][j]=new Cell(14*CELL_SIZE, j*CELL_SIZE, -3);
        }
        for(int i = 11; i < 14; i++){
            cells[i][14]=new Cell(i*CELL_SIZE, 14*CELL_SIZE, -3);
        }

        //M
        for(int j = 13; j < 19; j++){
            cells[16][j]=new Cell(16*CELL_SIZE, j*CELL_SIZE, -3);
        }
        cells[17][17]=new Cell(17*CELL_SIZE, 17*CELL_SIZE, -3);
        cells[18][16]=new Cell(18*CELL_SIZE, 16*CELL_SIZE, -3);
        cells[19][17]=new Cell(19*CELL_SIZE, 17*CELL_SIZE, -3);
        for(int j = 13; j < 19; j++){
            cells[20][j]=new Cell(20*CELL_SIZE, j*CELL_SIZE, -3);
        }

        //E
        for(int j = 13; j < 19; j++){
            cells[22][j]=new Cell(22*CELL_SIZE, j*CELL_SIZE, -3);
        }
        for(int i = 22; i < 26; i++){
            cells[i][18]=new Cell(i*CELL_SIZE, 18*CELL_SIZE, -3);
            cells[i][15]=new Cell(i*CELL_SIZE, 15*CELL_SIZE, -3);
            cells[i][13]=new Cell(i*CELL_SIZE, 13*CELL_SIZE, -3);
        }

        //O
        for(int j = 6; j < 10; j++)
            cells[5][j]=new Cell(5*CELL_SIZE, j*CELL_SIZE, -3);
        cells[6][10]=new Cell(6*CELL_SIZE, 10*CELL_SIZE, -3);
        cells[7][10]=new Cell(7*CELL_SIZE, 10*CELL_SIZE, -3);
        cells[6][5]=new Cell(6*CELL_SIZE, 5*CELL_SIZE, -3);
        cells[7][5]=new Cell(7*CELL_SIZE, 5*CELL_SIZE, -3);
        for(int j = 6; j < 10; j++)
            cells[8][j]=new Cell(8*CELL_SIZE, j*CELL_SIZE, -3);

        //V
        for(int j = 7; j < 11; j++){
            cells[10][j]=new Cell(10*CELL_SIZE, j*CELL_SIZE, -3);
        }
        cells[11][6]=new Cell(11*CELL_SIZE, 6*CELL_SIZE, -3);
        cells[12][5]=new Cell(12*CELL_SIZE, 5*CELL_SIZE, -3);
        cells[13][6]=new Cell(13*CELL_SIZE, 6*CELL_SIZE, -3);
        for(int j = 7; j < 11; j++){
            cells[14][j]=new Cell(14*CELL_SIZE, j*CELL_SIZE, -3);
        }
        //E
        for(int j = 5; j < 11; j++){
            cells[16][j]=new Cell(16*CELL_SIZE, j*CELL_SIZE, -3);
        }
        for(int i = 17; i < 20; i++){
            cells[i][10]=new Cell(i*CELL_SIZE, 10*CELL_SIZE, -3);
            cells[i][7]=new Cell(i*CELL_SIZE, 7*CELL_SIZE, -3);
            cells[i][5]=new Cell(i*CELL_SIZE, 5*CELL_SIZE, -3);
        }
        //R
        for(int j = 5; j < 11; j++){
            cells[21][j]=new Cell(21*CELL_SIZE, j*CELL_SIZE, -3);
        }
        for(int i = 22; i < 26; i++){
            cells[i][10]=new Cell(i*CELL_SIZE, 10*CELL_SIZE, -3);
            cells[i][8]=new Cell(i*CELL_SIZE, 8*CELL_SIZE, -3);
        }
        cells[25][9]=new Cell(25*CELL_SIZE, 9*CELL_SIZE, -3);

        cells[22][7]=new Cell(22*CELL_SIZE, 7*CELL_SIZE, -3);
        cells[23][6]=new Cell(23*CELL_SIZE, 6*CELL_SIZE, -3);
        cells[24][5]=new Cell(24*CELL_SIZE, 5*CELL_SIZE, -3);
        cells[25][5]=new Cell(25*CELL_SIZE, 5*CELL_SIZE, -3);


    }

    private static void drawElement(Cell elem){
        if(elem.getSprite()==null) return;

        elem.getSprite().getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(elem.getX(),elem.getY()+elem.getHeight());
        glTexCoord2f(1,0);
        glVertex2f(elem.getX()+elem.getWidth(),elem.getY()+elem.getHeight());
        glTexCoord2f(1,1);
        glVertex2f(elem.getX()+elem.getWidth(), elem.getY());
        glTexCoord2f(0,1);
        glVertex2f(elem.getX(), elem.getY());
        glEnd();
    }

    public static int getState(int x, int y){
        return cells[x][y].getState();
    }

    public static void setState(int x, int y, int state){
        cells[x][y].setState(state);
    }


    private static void updateOpenGL() {
        Display.update();
        Display.sync(FPS);
    }

    private static void initializeOpenGL(){
        try {

            if(Display.getTitle().equals("Game")) {

                Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));


                Display.setTitle(SCREEN_NAME);
                Display.setLocation(50, 50);



                Display.create();
            }

        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,SCREEN_WIDTH,0,SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);


        glEnable(GL_TEXTURE_2D);


        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(0,0,0,0);
    }
}
