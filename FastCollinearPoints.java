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
        // System.out.println("hi");
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
        // System.out.println("array length = " + n);
        System.out.println("the natural order below this line");
        for (int i = 0; i < naturalOrder.length; i++) {
            System.out.print(naturalOrder[i] + ", ");
        }
        System.out.println();

        for (int p = 0; p < n - 1; p++) {

            Point[] dupeOfNaturalOrder = naturalOrder.clone();
            
            Arrays.sort(dupeOfNaturalOrder, dupeOfNaturalOrder[p].slopeOrder()); // sort based on slope order, ALLEGEDLY

            System.out.println("final slope order: \n");
            for (int i = 0; i < dupeOfNaturalOrder.length; i++) {
                System.out.print(dupeOfNaturalOrder[i] + ", ");
            }
            System.out.println();

            System.out.println("expect slope to be -Infinity: " + naturalOrder[p].slopeTo(dupeOfNaturalOrder[0]));

            for (int i = 0; i < dupeOfNaturalOrder.length; i++) {
                System.out.println("the point" + dupeOfNaturalOrder[i] + " and slope is "
                        + naturalOrder[p].slopeTo(dupeOfNaturalOrder[i]));
            }

            int first = p + 1;


            for (int last = p + 2; last < dupeOfNaturalOrder.length; last++) {
                // double slope = naturalOrder[p].slopeTo(dupeOfNaturalOrder[p + 1]);
                // System.out.println("starting slope is " + slope + " and first = " + first + "
                // and last = " + last);
                while (last < n && naturalOrder[p].slopeTo(dupeOfNaturalOrder[first]) == dupeOfNaturalOrder[first]
                        .slopeTo(dupeOfNaturalOrder[last])) {

                    // System.out.println("index of last after equal slope " + last + ", counter is
                    // " + counter);
                    last++;
                    // System.out.println("after equal, last is " + last + " and counter is " +
                    // counter);
                }
                // (slope != dupe[first].slopeTo(dupe[last]))
                
                if (last - first >= 3) {// implement code for duplicate lines
                    lineList.add(new LineSegment(naturalOrder[p], dupeOfNaturalOrder[last - 1]));
                    numberOfSegments++;
                }
                first = last;
            }
            // System.out.println("index of last: " + last);

        }

        // System.out.println("loop for p = " + p + " done");

        

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
        In in = new In("collinear/input20.txt");
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
