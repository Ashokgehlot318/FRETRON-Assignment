# Specialized Castle Paths Finder

## Approach

The optimized approach uses memoization to avoid redundant path calculations. By utilizing a set to store visited paths, the program ensures that each unique path is only calculated once, thereby improving performance.

### Step-by-Step Approach with Memoization

1. **Initialize Data Structures**:
   - Initialize an empty list `allPaths` to store the unique paths for the castle.
   - Initialize an empty set `visited` to store the string representations of visited paths.

2. **Depth-First Search (DFS) Function**:
   - Create a DFS function `dfs` that takes the list of remaining soldiers and the current path as parameters.

3. **Generate a Unique Path Key**:
   - Generate a unique string representation of the current path using its coordinates. This can be achieved by converting the list of coordinates into a string.

4. **Check Visited Paths**:
   - Check if the generated path key exists in the `visited` set.
   - If the path has been visited, return from the DFS function to avoid redundant calculations.

5. **Check for Base Case**:
   - If there are no more soldiers left, add the current path to the list of unique paths `allPaths`.
   - Store the path key in the `visited` set to mark it as visited.

6. **Explore Valid Next Moves**:
   - Retrieve the last coordinate from the current path to determine the valid next moves based on the castle and soldier positions.
   - Iterate through the remaining soldiers to find valid next moves according to the specialized castle's rules.

7. **Recursively Explore Paths**:
   - If a valid move is found, create a new list of soldiers without the selected soldier, and a new path extended with the selected soldier.
   - Recursively call the `dfs` function with the updated list of soldiers and path to explore the new path.


## Time Complexity
The time complexity of the algorithm is O(N!), where N represents the number of soldiers. This is due to the factorial growth in the number of possible paths to explore.




