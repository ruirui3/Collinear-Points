import java.util.Arrays;

public class BruteCollinearPoints {

    private int segments = 0;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        
        Arrays.sort(points);

    }  
    
    // the number of line segments
    public int numberOfSegments() {
        return segments;
    }    
    
    // the line segments
    public LineSegment[] segments() {
        
    }  
}
