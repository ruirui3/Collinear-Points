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

        for (int p = 0; p < n - 3; p++) {

            Point[] dupeOfNaturalOrder = naturalOrder.clone();

            Arrays.sort(dupeOfNaturalOrder, dupeOfNaturalOrder[p].slopeOrder());

            
            
            int first = 1;

            for (int last = 2; last < dupeOfNaturalOrder.length; last++) {

                while (last < n && Double.compare(dupeOfNaturalOrder[0].slopeTo(dupeOfNaturalOrder[first]), dupeOfNaturalOrder[first].slopeTo(dupeOfNaturalOrder[last])) == 0) {

                    last++;

                }

                if (last - first >= 3 && dupeOfNaturalOrder[0].compareTo(dupeOfNaturalOrder[first]) < 0) {// implement code for duplicate lines ASFDIUH
                    lineList.add(new LineSegment(naturalOrder[p], dupeOfNaturalOrder[last - 1]));
                    numberOfSegments++;
                }
                first = last;
            }

        }

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
