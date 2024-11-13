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
    private Point[] points2;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        

        if (points == null) {
            throw new IllegalArgumentException();
        }

        points2 = points.clone();

        for (int i = 0; i<points2.length; i++) {
            if (points2[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j<i; j++) {
                if (points2[i].compareTo(points2[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Arrays.sort(points2);



        lineSegment = new ArrayList<LineSegment>();
        for (int i = 0; i<points2.length-3; i++) {
            for (int j = i+1; j<points2.length-2; j++) {
                for (int k = j+1; k<points2.length-1; k++) {
                    for (int m = k+1; m<points2.length; m++) {

                        if (points2[i].slopeTo(points2[j]) == points2[j].slopeTo(points2[k]) && points2[j].slopeTo(points2[k]) == points2[k].slopeTo(points2[m])) {
                            
                            lineSegment.add(new LineSegment(points2[i], points2[m])); // list a possible slope connection
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
        In in = new In("collinear/input8.txt");
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
