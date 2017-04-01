package Tetris;

public class Object
{
    int type;
    Point center;
    Point pos[];



    Object()
    {
        center = new Point();
        pos = new Point[4];
        pos[0] = new Point();
        pos[1] = new Point();
        pos[2] = new Point();
        pos[3] = new Point();
    }


}
