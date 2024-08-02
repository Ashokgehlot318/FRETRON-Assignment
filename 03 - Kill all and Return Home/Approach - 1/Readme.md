# Specialized Castle

## Approach

1. **Input Handling**:
   - **Read Number of Soldiers**: Prompt the user to enter the number of soldiers on the board.
   - **Read Soldier Coordinates**: Collect the coordinates for each soldier.
   - **Read Castle Coordinates**: Collect the starting position of the specialized castle.

2. **Setup**:
   - **Create `Coordinate` Class**: Represents a coordinate on the chessboard.
   - **Store Soldiers**: Store the coordinates of the soldiers in a list.
   - **Store Castle Position**: Store the starting position of the castle.

3. **Pathfinding**:
   - **Brute Force Search**:
     - **Base Case**: If all soldiers have been killed and the castle returns to the starting position, store the path.
     - **Recursive Case**: For each soldier that the castle can kill (moving forward), update the path and recursively find the next valid moves.
     - **Constraints**: The castle only moves forward along the same column and turns left after each kill.




