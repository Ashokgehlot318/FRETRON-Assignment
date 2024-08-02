import java.util.*;

public class OptimizedFlightPathAdjustment {

    // Class representing a point in 2D space
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

    // Class representing a line segment defined by two points
    static class LineSegment {
        Point p1, p2;

        LineSegment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        // Method to check if this line segment intersects with another line segment
        boolean intersects(LineSegment other) {
            int o1 = orientation(this.p1, this.p2, other.p1);
            int o2 = orientation(this.p1, this.p2, other.p2);
            int o3 = orientation(other.p1, other.p2, this.p1);
            int o4 = orientation(other.p1, other.p2, this.p2);

            // General case: segments intersect
            if (o1 != o2 && o3 != o4) return true;

            // Special cases: check if points are on the segments
            if (o1 == 0 && onSegment(this.p1, this.p2, other.p1)) return true;
            if (o2 == 0 && onSegment(this.p1, this.p2, other.p2)) return true;
            if (o3 == 0 && onSegment(other.p1, other.p2, this.p1)) return true;
            if (o4 == 0 && onSegment(other.p1, other.p2, this.p2)) return true;

            return false;
        }

        // Method to determine the orientation of an ordered triplet (p, q, r)
        private int orientation(Point p, Point q, Point r) {
            int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
            return (val > 0) ? 1 : (val < 0) ? 2 : 0;
        }

        // Method to check if a point r lies on the line segment pq
        private boolean onSegment(Point p, Point q, Point r) {
            return (r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x) &&
                    r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y));
        }
    }

    // Class representing a spatial grid to optimize segment intersection checks
    static class Grid {
        int cellSize;
        Map<Point, List<LineSegment>> gridMap = new HashMap<>();

        Grid(int cellSize) {
            this.cellSize = cellSize;
        }

        // Add a line segment to the grid by adding both its endpoints
        void addSegment(LineSegment segment) {
            addPoint(segment.p1, segment);
            addPoint(segment.p2, segment);
        }

        // Add a line segment to the grid at the cell containing the point p
        private void addPoint(Point p, LineSegment segment) {
            int cellX = p.x / cellSize;
            int cellY = p.y / cellSize;
            Point cellKey = new Point(cellX, cellY);
            gridMap.computeIfAbsent(cellKey, k -> new ArrayList<>()).add(segment);
        }

        // Retrieve all line segments in cells near the given point p
        List<LineSegment> getNearbySegments(Point p) {
            int cellX = p.x / cellSize;
            int cellY = p.y / cellSize;
            List<LineSegment> nearbySegments = new ArrayList<>();

            // Check the cell containing p and its neighboring cells
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    Point cellKey = new Point(cellX + dx, cellY + dy);
                    List<LineSegment> segments = gridMap.get(cellKey);
                    if (segments != null) {
                        nearbySegments.addAll(segments);
                    }
                }
            }
            return nearbySegments;
        }
    }

    public static void main(String[] args) {
        List<Point[]> flightPaths = new ArrayList<>();
        flightPaths.add(new Point[]{new Point(1, 1), new Point(2, 2), new Point(3, 3)});
        flightPaths.add(new Point[]{new Point(1, 1), new Point(2, 4), new Point(3, 2)});
        flightPaths.add(new Point[]{new Point(1, 1), new Point(4, 2), new Point(3, 4)});

        Grid grid = new Grid(10); // Define cell size for the grid

        // Add all segments of flight paths to the grid
        for (Point[] path : flightPaths) {
            for (int i = 0; i < path.length - 1; i++) {
                LineSegment segment = new LineSegment(path[i], path[i + 1]);
                grid.addSegment(segment);
            }
        }

        // Adjust paths to resolve intersections
        adjustPaths(grid, flightPaths);

        // Output the adjusted paths
        for (int i = 0; i < flightPaths.size(); i++) {
            System.out.println("Flight Path " + (i + 1) + ": ");
            for (Point p : flightPaths.get(i)) {
                System.out.println(p);
            }
        }
    }

    // Main logic to adjust flight paths to resolve intersections
    private static void adjustPaths(Grid grid, List<Point[]> flightPaths) {
        boolean intersectionFound;
        do {
            intersectionFound = false;
            for (Point[] path : flightPaths) {
                for (int i = 0; i < path.length - 1; i++) {
                    LineSegment segment = new LineSegment(path[i], path[i + 1]);
                    List<LineSegment> nearbySegments = grid.getNearbySegments(path[i]);
                    for (LineSegment other : nearbySegments) {
                        if (segment.intersects(other)) {
                            intersectionFound = true;
                            resolveIntersections(path, i);
                        }
                    }
                }
            }
        } while (intersectionFound);
    }

    // Basic approach to resolve intersections by shifting coordinates
    private static void resolveIntersections(Point[] path, int index) {
        // Shift the coordinates of the next segment slightly to resolve intersection
        if (index + 1 < path.length) {
            path[index + 1].x += 1; // Shift x-coordinate
            path[index + 1].y += 1; // Shift y-coordinate
        }
    }
}
