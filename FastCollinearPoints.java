/**
 * @author Rui Zhao attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 */

//The hardest part about this project was definitely figuring out the logic and the pattern of the sorting array. By recalibrating my thinking and drawing 
//logical diagrams out, I was finally able to navigate my problem. The main issue was with the Point[] lists I am using, often getting mixed up with one another
//which resulted in false points being connected to one another. Also the index was a common problem because I kept getting indexoutofbounds errors, but it was fixed after 
//utilizing the Debug feature. 


import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private int numberOfSegments = 0;
    private ArrayList<LineSegment> lineList;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        
        //check corner cases
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < i; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Point[] naturalOrder = points.clone();
        Arrays.sort(naturalOrder);
        int n = points.length;
        lineList = new ArrayList<>();

        for (int p = 0; p < n - 3; p++) { //check if this could be a problem if size -> changed p classfication

            Point[] dupeOfNaturalOrder = naturalOrder.clone();

            Arrays.sort(dupeOfNaturalOrder, dupeOfNaturalOrder[p].slopeOrder());

            int first = 1;

            for (int last = 2; last < dupeOfNaturalOrder.length; last++) {

                while (last < n && Double.compare(dupeOfNaturalOrder[0].slopeTo(dupeOfNaturalOrder[first]), dupeOfNaturalOrder[first].slopeTo(dupeOfNaturalOrder[last])) == 0)  { //check max amount of consecutive. 
                    
                    last++; //note: it effects the loop occurence

                }

                if (last - first >= 3 && dupeOfNaturalOrder[0].compareTo(dupeOfNaturalOrder[first]) < 0) {// last - first exemplifies the counter. default is pos 2 - pos 1, which is 1, therefore initial counter is 1. compare checks if there was a line before
                    lineList.add(new LineSegment(naturalOrder[p], dupeOfNaturalOrder[last - 1]));
                    
                }
                first = last; //find next
            }
            
        }
        numberOfSegments = lineList.size();
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] seg = new LineSegment[lineList.size()];
        for (int i = 0; i < lineList.size(); i++) {
            seg[i] = lineList.get(i);
        }
        return seg;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("collinear/rs1423.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-500, 32768);
        StdDraw.setYscale(-500, 32768);
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
