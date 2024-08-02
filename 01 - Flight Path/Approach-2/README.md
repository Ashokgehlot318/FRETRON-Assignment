# Optimized Flight Path Adjustment

## Approach

1. **Initialization**:
   - **Flight Paths**: Define a list of flight paths, each consisting of a series of points.
   - **Spatial Grid**: Use a grid to optimize the search for intersecting segments.

2. **Intersection Detection**:
   - **Line Segment Intersection**: Implement an algorithm to check if two line segments intersect.
   - **Grid-Based Optimization**: Use a spatial grid to reduce the number of segment comparisons by checking only nearby segments.

3. **Path Adjustment**:
   - **Basic Adjustment**: Shift the coordinates of the segment following the intersection to resolve conflicts.


   - **Class `Point`**: Represents a point with x and y coordinates.
- **Class `LineSegment`**: Represents a line segment between two points and contains methods to check for intersections.
  - **Method `intersects`**: Determines if two line segments intersect.
  - **Method `orientation`**: Calculates the orientation of three points.
  - **Method `onSegment`**: Checks if a point lies on a line segment.
- **Class `Grid`**: Represents a spatial grid for efficient intersection detection.
  - **Method `addSegment`**: Adds a line segment to the grid.
  - **Method `getNearbySegments`**: Retrieves line segments in nearby grid cells.
- **Method `adjustPaths`**: Main method that detects and resolves path intersections using the grid.


## Time Complexity

- **Intersection Detection**: The time complexity is **O(n * k)**, where `n` is the number of line segments and `k` is the number of nearby segments checked for each segment. The spatial grid optimization reduces the number of comparisons significantly compared to a naive approach.
- **Path Adjustment**: The time complexity is **O(m)**, where `m` is the number of points in the path being adjusted.

## Space Complexity

- **Space Complexity**: The space complexity is **O(n)**, where `n` is the number of line segments stored in the grid. This includes the space required to store the segments and the grid structure.
