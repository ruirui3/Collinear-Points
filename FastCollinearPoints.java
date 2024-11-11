import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.Line;

public class FastCollinearPoints {

    private int numberOfSegments = 0;
    private ArrayList<LineSegment> lineList;


    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        
        int n = points.length;
        lineList = new ArrayList<>();
        for (int p = 0; p<n; p++) {

            Point pPoint = points[p];
            Arrays.sort(points, 0, n, pPoint.slopeOrder()); //sort based on slope order, ALLEGEDLY
            int counter = 0;
            double slope = points[p].slopeTo(points[p+1]); //starting EXPECTED slope, with p+1 being point of comparison q 
            for (int q = p+1; q<n; q++) { //all index of sorted array AFTER point p, hopefully in slope order, i think smallest slope to highest? (counterclockwise sort)


                if (slope == points[p].slopeTo(points[q])) {
                    counter++; //increment numbers of consecutive link
                } else {

                    if (counter>=4) {
                        lineList.add(new LineSegment(points[p], points[q])); //if there is 4 consecutive or more links, add to lineList list of segments
                        numberOfSegments++;
                    }

                    slope = points[p].slopeTo(points[q]);  //set slope equal to the new slope
                }

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
        for (int i = 0; i<lineList.size(); i++) {
            seg[i] = lineList.get(i);
        }
        return seg;
    }      
    
    
    
}
