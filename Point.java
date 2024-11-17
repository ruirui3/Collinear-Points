import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

/**
 * @author Rui ZHao attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 */

//nothing hard about this, other than the fact that there are +0.0s, -inf, and +inf. I guess 0 != +0.0. Also I think the slopeOrder compare function was hard because it was a new logic I had to use. 


public class Point implements Comparable<Point> {
    
    private int x;
    private int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y > that.y) {
            return 1;
        } 
        if (this.y < that.y) {
            return -1;
        } 

        if (this.x > that.x) {
            return 1;
        } 
        if (this.x < that.x) {
            return -1;
        } 

        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        
        if (compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.y-that.y == 0) {
            return +0.0d;
        }
        if (this.x-that.x == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (double)(this.y-that.y)/(this.x-that.x);
    
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point point1, Point point2) {
            double slope1 = slopeTo(point1);
            double slope2 = slopeTo(point2);
            return Double.compare(slope1, slope2);
        }
    }

    

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }    

    // draws the line segment from this point to that point                       
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }   

    // string representation              
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    } 
}