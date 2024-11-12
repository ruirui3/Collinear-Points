import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

//note to self: implement corner cases

public class BruteCollinearPoints {

    private int segments = 0;
    private ArrayList<LineSegment> lineSegment;
    private LineSegment[] listedLines;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i<points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j<i; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Arrays.sort(points);



        lineSegment = new ArrayList<LineSegment>();
        for (int i = 0; i<points.length-3; i++) {
            for (int j = i+1; j<points.length-2; j++) {
                for (int k = j+1; k<points.length-1; k++) {
                    for (int m = k+1; m<points.length; m++) {

                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) && points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {
                            
                            lineSegment.add(new LineSegment(points[i], points[m])); // list a possible slope connection
                            segments++;
                        }

                    }
                }
            }
        }
    }  
    
    // the number of line segments
    public int numberOfSegments() {
        return segments;
    }    
    
    // the line segments
    public LineSegment[] segments() {

        listedLines = new LineSegment[lineSegment.size()];
        for (int i = 0; i<lineSegment.size(); i++) {
            listedLines[i] = lineSegment.get(i);
        }
        return listedLines;


    }  

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
