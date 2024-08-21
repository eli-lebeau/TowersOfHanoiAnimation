import java.util.Scanner;
/**
 * Driver for the TowersOfHanoi_EL class
 * Takes inputs from the user using the Scanner package to determine the number of rings in the puzzle and the pause time between showing moves
 * The driver calls all necessary moves to show the solving of the puzzle in the minimum number of moves
 * by instantiating a TowersOfHanoi_EL object and calling the methods necessary from that class
 * The class displays a message when the puzzle has been completely solved.
 *
 * @Elebeau
 * @4/28/23
 */
public class TowersOfHanoiDriver_EL
{
    public static void main (String[] args){
        //ensures that the drawing will always begin with a clear screen
        StdDraw.clear();
        
        Scanner scan = new Scanner(System.in);
        
        //prompts the user for the number of ringa and prefered pause time between moves
        System.out.println("How many rings would you like to have in the puzzle?");
        int rings = scan.nextInt();
        System.out.println("How seconds would you like to pause between moves?");     
        double time = scan.nextDouble();
        
        //puts time in terms of milliseconfs
        time*=1000;
        
        //instantiates a TowersOfHanoi_EL object
        TowersOfHanoi_EL tower = new TowersOfHanoi_EL(rings, (int)time);
        //draws the puzzle
        tower.draw();
        tower.drawRings();
        
        //pauses before first move
        StdDraw.pause((int)time);
        
        //solves puzzle and shows moves
        tower.solve();
        
        //prints message that the puzzle is done
        System.out.println("Done!");
        scan.close();
    }
}
