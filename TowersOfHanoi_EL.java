import java.util.ArrayList;
/**
 * Class for the Towers of Hanoi game. The class includes instance variables for the number of rings in the puzzle and an ArrayList to hold all of the rings in the form of Ring_EL objects
 * The class also has methods to draw the towers for the game and to solve the game.
 * The game has methods to draw the solving of the game in real time by showing the rings in updated positons until the game is solved
 * The class also has an instance variable to determine the pause between showing the next move in solving the puzzle
 * The class also calculatesm, and solves the puzzle in, the minimum number of moves to solve the puzzle
 *
 * @Elebau
 * @4/28/23
 */
public class TowersOfHanoi_EL
{
    //instance variable for the number of rings in the puzzle
    private int numRings;
    //instance variable for the pause between moves shown
    private int pause;
    //ArrayList that contains all of the ring objects in the puzzle
    private ArrayList<Ring_EL> rings;
    //Width (horizontal radius) of ring
    private double width;
    //instance variable that tracks the number of moves done while the puzzle is solved
    private int counter;
    
    //Constructor for the class, takes parameters for the number of rings in the puzzle and the pause between moves
    public TowersOfHanoi_EL(int rs, int t){
        //assigns parameters to corresponding instance variables
        numRings=rs;
        pause=t;
        //instantiates ArrayList
        rings = new ArrayList<Ring_EL>();
        //sets initial width value
        width=rs*9;
        //initializes counter for the number of moves to zero
        counter=0;
        
        //for loop that instantiates and adds numRings number of ring objects to the rings ArrayList
        for (int i = numRings+1; i>1; i--){
            //creates initial vertical position
            double y=numRings+2-i;
            //creates width of each ring (horizontal radius), so rx is half the width of the ring
            double rx = (double)3*i/4;
            //all rings are 1 unit tall, ry is the vertical radius of the ring
            double ry = 0.5;
            //instantiates and adds new ring to the rings ArrayList with random values for the red, green, and blue values for the the final three parameters
            rings.add(new Ring_EL(width/4, y, rx, ry, (int)(Math.random()*255+1), (int)(Math.random()*255+1), (int)(Math.random()*255+1)));
        }
        
        //sets bounds of the window to be drawn in
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, width/2);
    }

    //draws the setup aside from the rings
    public void draw(){
        //draws the three towers for the puzzle
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledRectangle(width/4,0,numRings,0.5);
        StdDraw.filledRectangle(width/4,numRings/2,0.5,numRings+2);
        StdDraw.filledRectangle(width/2,0,numRings,0.5);
        StdDraw.filledRectangle(width/2,numRings/2,0.5,numRings+2);
        StdDraw.filledRectangle(3*width/4,0,numRings,0.5);
        StdDraw.filledRectangle(3*width/4,numRings/2,0.5,numRings+2);

        //displays the minimum number of moves for the game and the counter for how many moves have been done
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(width/4,width/2-2, "Minimum moves: "+this.leastMoves(numRings));
        StdDraw.text(1.5*width/2,width/2-2, "Moves done: "+counter);
    }
    
    //draws all rings in the rings ArrayList
    public void drawRings(){
        for (int i = 0; i<rings.size(); i++){
            rings.get(i).drawRing();
        }
    }
    
    //public method with no parameters to be called in driver
    //calls private method solution and gives it the proper parameters
    //sets tower three as the target, tower one as the source of the rings, and tower two as the unUsed tower
    public void solve(){
        this.solution(numRings, 1,3,2);
    }
    
    //recursive method to solve the puzzle for any number of rings
    //the three towers can be classified for any move as the source tower (where the ring starts), 
    //the target tower (where the ring is placed), and the unused tower (the tower that is not used in the move)
 
    private void solution(int r, int source, int target, int unUsed){
        StdDraw.enableDoubleBuffering();
        //to understand the nature of this recursive method, we can trace how to solve the puzzle for increasing number of rings
        
        //for one ring, we move the rings from the source tower to the target tower
        if(r==1){
            rings.get(rings.size()-1).setP(target);
            rings.get(rings.size()-r).setY(this.newHeight(target));
            //we redraw the rings
            this.update();
        }
        else{
            //for two rings, we must first move the smaller ring to the unused tower
            //to accomplish this, we call the solution method for r-1 and switch our initial target with our initial unused
            //this will trigger the if statement above and move the top ring to the unused tower
            this.solution(r-1, source, unUsed, target);

            //Having moved the smaller ring out of the way, we can now move the larger ring to the target tower
            rings.get(rings.size()-r).setP(target);
            rings.get(rings.size()-r).setY(this.newHeight(target));

            //we redraw the rings with the updated position
            this.update();

            //Not to solve the puzzle we have to move the smaller rings from the tower that was unused for moving the larger ring to the target tower
            //we accomplish this by calling the method once more but setting the unused tower as the source and the target as the target once more
            this.solution(r-1, unUsed, target, source);
        }
        //Applying this set of steps, calling the method on r-1 rings and switching the unused and target towers
        //then moving the largest ring to the target tower
        //and then calling the method one more time on r-1 rings with the unused as the source and the target as the target
    }

    //updates the counter and the drawing after a move has been made in the solution method
    private void update(){
        counter++;
        StdDraw.show();
        StdDraw.clear(StdDraw.WHITE);
        this.draw();
        this.drawRings();
        StdDraw.pause(this.pause);
        StdDraw.show();  
    }

    //calculates the new height at which the ring should be drawn at based on how many rings are already on the tower that the ring of interest is being moved to 
    private int newHeight(int post){
        int count=0;
        for (int i = 0; i<rings.size(); i++){
            if(post==rings.get(i).getP()) count++;
        }
        return count;
    }

    //calculates the fewest number of moves to solve the puzzle for r rings recursively
    //the formula for this is 1+2*(minimum number of moves for r-1 rings)
    //it takes one move to solve the puzzle when r=1
    private int leastMoves(int r){
        if(r==1) return 1;
        else return 1+2*leastMoves(r-1);
    }
}

