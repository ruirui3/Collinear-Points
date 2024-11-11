import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int segments = 0;
    private ArrayList<LineSegment> lineSegment;
    private LineSegment[] listedLines;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        
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
}
