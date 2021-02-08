import processing.core.PApplet;

public class GameOfLife extends PApplet{
    private static GameOfLife app;

    public static void main (String[] args){
        PApplet.main("GameOFLife");
    }

    public GameOfLife(){
        app = this;
    }

    @Override
    public void settings(){
        super.settings();
        size(1000, 500);
    }

    @Override
    public void setup(){
        super.setup();
        //instantiating Cell objects
    }

    @Override
    public void draw(){
        super.draw();
        //displaying Cell objects
    }

    @Override
    public void mouseClicked(){
        super.mouseClicked();
        //having Cell objects handle clicks
    }

    @Override
    public void keyPressed(){
        super.keyPressed();
        //pausing and restarting Cell evolution
    }

    public static GameOfLife getApp(){
        return app;
    }
}
