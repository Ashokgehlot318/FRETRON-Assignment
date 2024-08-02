import java.util.ArrayList;
import java.util.List;

public class FlightPathAdjustment {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    static class LineSegment {
        Point p1, p2;

        LineSegment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        boolean intersects(LineSegment other) {
            int o1 = orientation(this.p1, this.p2, other.p1);
            int o2 = orientation(this.p1, this.p2, other.p2);
            int o3 = orientation(other.p1, other.p2, this.p1);
            int o4 = orientation(other.p1, other.p2, this.p2);

            if (o1 != o2 && o3 != o4) {
                return true;
            }

            if (o1 == 0 && onSegment(this.p1, this.p2, other.p1)) return true;
            if (o2 == 0 && onSegment(this.p1, this.p2, other.p2)) return true;
            if (o3 == 0 && onSegment(other.p1, other.p2, this.p1)) return true;
            if (o4 == 0 && onSegment(other.p1, other.p2, this.p2)) return true;

            return false;
        }

        private int orientation(Point p, Point q, Point r) {
            int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
            if (val == 0) return 0;
            return (val > 0) ? 1 : 2;
        }

        private boolean onSegment(Point p, Point q, Point r) {
            return (r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x) &&
                    r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y));
        }
    }

    public static void main(String[] args) {
        List<Point[]> flightPaths = new ArrayList<>();
        flightPaths.add(new Point[] { new Point(1, 1), new Point(2, 2), new Point(3, 3) });
        flightPaths.add(new Point[] { new Point(1, 1), new Point(2, 4), new Point(3, 2) });
        flightPaths.add(new Point[] { new Point(1, 1), new Point(4, 2), new Point(3, 4) });

        adjustPaths(flightPaths);

        // Output the adjusted paths
        for (int i = 0; i < flightPaths.size(); i++) {
            System.out.println("Flight Path " + (i + 1) + ": ");
            for (Point p : flightPaths.get(i)) {
                System.out.println(p);
            }
        }
    }

    private static void adjustPaths(List<Point[]> flightPaths) {
        boolean intersectionFound;
        do {
            intersectionFound = false;
            for (int i = 0; i < flightPaths.size(); i++) {
                for (int j = i + 1; j < flightPaths.size(); j++) {
                    if (checkPathIntersections(flightPaths.get(i), flightPaths.get(j))) {
                        intersectionFound = true;
                        resolveIntersections(flightPaths.get(i), flightPaths.get(j));
                    }
                }
            }
        } while (intersectionFound);
    }

    private static boolean checkPathIntersections(Point[] path1, Point[] path2) {
        for (int i = 0; i < path1.length - 1; i++) {
            LineSegment segment1 = new LineSegment(path1[i], path1[i + 1]);
            for (int j = 0; j < path2.length - 1; j++) {
                LineSegment segment2 = new LineSegment(path2[j], path2[j + 1]);
                if (segment1.intersects(segment2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void resolveIntersections(Point[] path1, Point[] path2) {
        // Basic approach: Move the coordinates of the second path slightly
        // to avoid intersections. This is a placeholder for more sophisticated
        // logic.

        for (int i = 0; i < path2.length; i++) {
            path2[i].x += 1; // Shift x-coordinate
            path2[i].y += 1; // Shift y-coordinate
        }
    }
}
