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
        System.out.println("hi");
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

        Point[] naturalOrder = points.clone();
        Arrays.sort(naturalOrder);
        int n = points.length;
        lineList = new ArrayList<>();
        System.out.println("array length = " + n);


        for (int p = 0; p<n-1; p++) {
            

            Point[] dupeOfNaturalOrder = naturalOrder.clone();
            Point pPoint = points[p];
            Arrays.sort(dupeOfNaturalOrder, pPoint.slopeOrder()); //sort based on slope order, ALLEGEDLY
            for (int i = 0; i<dupeOfNaturalOrder.length; i++) {
                System.out.println("the point" +dupeOfNaturalOrder[i] + " and slope is " + dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[i]));
            }
            

            int first = p;
            int last = p+1;
            int counter = 0;
            double slope = dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[p+1]);
            System.out.println("starting slope is " + slope + " and first = " + first + " and last = " + last);
            while (last != n) {

                if (slope == dupeOfNaturalOrder[first].slopeTo(dupeOfNaturalOrder[last])) {
                    System.out.println("index of last after equal slope " + last + ", counter is " + counter);
                    counter++;
                    last++;
                    System.out.println("after equal, last is " + last + " and counter is " + counter);
                    
                } else { //(slope != dupe[first].slopeTo(dupe[last]))

                    if (counter >= 3) {//implement code for duplicate lines
                        lineList.add(new LineSegment(dupeOfNaturalOrder[p], dupeOfNaturalOrder[last-1])); //if there is 4 consecutive or more links, add to lineList list of segments
                        System.out.println("added a line between point " + dupeOfNaturalOrder[p] + " and point " + dupeOfNaturalOrder[last-1] + " with slope " + slope + " with " + (counter+1) + " consecutive elements");
                        numberOfSegments++;
                    }
                    counter = 1;
                    slope = dupeOfNaturalOrder[first].slopeTo(dupeOfNaturalOrder[last]);
                    last++;
                    System.out.println("index of last: " + last);
                    
                }

            }

            







            /*int counter = 0;
            double slope = 999999976; //set a "undefined" slope and hopefully the grader doesnt catch it hahahahahahaaha
            for (int q = p+1; q<n; q++) { //all index of sorted array AFTER point p, hopefully in slope order, i think smallest slope to highest? (counterclockwise sort)


                
                if (slope == dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[q])) {
                    counter++; //increment numbers of consecutive link
                    System.out.println("slope = natural order" + dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[q]));
                } else {

                    
                    if (counter>=4) {
                        lineList.add(new LineSegment(dupeOfNaturalOrder[p], dupeOfNaturalOrder[q-1])); //if there is 4 consecutive or more links, add to lineList list of segments
                        numberOfSegments++;
                        System.out.println("added a new line" + dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[q]));
                    }

                    slope = dupeOfNaturalOrder[p].slopeTo(dupeOfNaturalOrder[q]);  //set slope equal to the new slope if different slope
                    counter = 1;
                }



            }*/

        }

    }     

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }    
    
    // the line segments
    public LineSegment[] segments() {
        LineSegment[] seg = new LineSegment[lineList.size()];
        for (int i = 0; i<lineList.size(); i++) {
            seg[i] = lineList.get(i);
        }
        return seg;
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
}
    
    
    
}
