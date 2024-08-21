
/**
 * ADT for the rings used in the Towers of Hanoi puzzle. The class contains instance variables for the size, position, and color of the ring.
 * The class contains methods to access instance variables and a method to draw the ring.
 *
 * @Elebeau
 * @4/28/2023
 */
public class Ring_EL
{
    //horizontal position of the ring
    private double x;
    //vertical position of the ring
    private double y;
    //horizontal radius of the ring
    private double rx;
    //vertical radius of the ring
    private double ry;
    //integer values to determine the color of the ring using the rgb scale from 0 to 255
    private int red;
    private int green;
    private int blue;
    //integer value to determine which of the three posts (towers) that the ring is currently located on
    private int post;
    
    //Constructor, take parameters for the horizontal position, vertical position, vertical radius, horizontal radius, and rgb color values.
    //Intializes the instance variables with their corresponding parameters
    public Ring_EL(double X, double Y, double RX, double RY, int r, int g, int b){
        x=X;
        y=Y;
        rx=RX;
        ry=RY;
        red=r;
        green=g;
        blue=b;
        //initalizes the post to 1
        post=1;
    }
    //draws the ring as a rectangle using the values stored in the instance variables
    public void drawRing(){
        StdDraw.setPenColor(this.red, this.green, this.blue);
        StdDraw.filledRectangle(this.x*this.post,this.y,this.rx,this.ry);
    }
    //returns the horizontal position of the ring
    public double getX(){
        return x;
    }
    //returns the vertical position of the ring
    public double getY(){
        return y;
    }
    //returns the horizontal radius of the ring
    public double getRX(){
        return rx;
    }
    //returns the vertical radius of the ring
    public double getRY(){
        return ry;
    }
    //sets the post instance variable to reflect which tower/post the ring is currently on
    public void setP(int p){
        post=p;
    }
    //sets the vertical position of the ring
    public void setY(double height){
        y=height;
    }
    //returns which post the ring is on
    public int getP(){
        return post;
    }
}
