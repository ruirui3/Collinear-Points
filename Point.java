import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    
    private int x;
    private int y;

    // constructs the point (x, y)
    public Point(int x1, int y2) {
        x = x1;
        y = y2;
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y > that.y) {
            return 1;
        }
        if (y < that.y) {
            return -1;
        }
        if (x < that.x) {
            return -1;
        }
        if (x > that.x) {
            return 1;
        }
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        
        if (compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (y-that.y == 0) {
            return 0;
        }
        if (x-that.x == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (y-that.y)/(x-that.x);
    
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
        
    }    

    // draws the line segment from this point to that point                       
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }   

    // string representation              
    public String toString() {

    } 
}