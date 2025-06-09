> [!NOTE]
> This project is academic coursework for UECS2453 Problem Solving with Data Structures and Algorithms module.

# Bin Packing Java
A Java implementation for solving the Bin Packing Problem using self-balancing binary search trees (AVL Trees). This project is structured for use with Eclipse and demonstrates efficient data structure usage and parcel data management for optimal packing.

| ![image](https://github.com/user-attachments/assets/75234e0c-f87a-43a7-95ea-64c5819ceefe) | ![image](https://github.com/user-attachments/assets/411fca65-0553-4b3f-b97b-46495070a7e9) |
|:---:|:---:|
| Algorithm Visualisation | Growth-rate Projection |
## What is the Bin Packing Problem?

The **Bin Packing Problem** is a classic optimization problem:
Given a set of items, each with a size or weight, the goal is to pack them into a minimum number of fixed-size bins without exceeding the bin capacity.
It is widely studied in computer science, logistics, and operations research due to its applicability in resource allocation, scheduling, and shipping.

## Features

- **Bin Packing Problem Algorithms**: Efficient organization of items into bins.
- **Custom Data Structures**: AVL Tree implementation to optimize search and insertion.
- **CSV Data Input**: Read and process parcel data from `parcel_data.csv`.
- **Modular Design**: Clear separation of models, IO, algorithms, and data structures.

## Repository Structure

```
.
├── src/
│   ├── app/
│   │   ├── avltree/      # AVLTree and AVLTreeNode classes
│   │   ├── io/           # Input/output utilities (CSV reading, etc.)
│   │   ├── main/         # Main execution logic
│   │   └── model/        # Data models (e.g., Parcel, Bin)
├── parcel_data.csv       # Sample parcel data (type, weight, fragile, destination)
├── .classpath            # Eclipse project configuration
├── .project              # Eclipse project description
├── .gitignore
└── .settings/
```

## Getting Started

### Prerequisites

- Java 8+ (JRE)
- Eclipse IDE (recommended for project setup)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/cscheah5/dsa-bin-packing.git
   ```

2. **Import into Eclipse:**
   - Open Eclipse.
   - Select `File` > `Import` > `Existing Projects into Workspace`.
   - Choose the cloned directory.

3. **Build the Project:**
   - The project is pre-configured for Eclipse.
   - Make sure your workspace uses Java 8+.

4. **Run the Application:**
   - Locate the main class under `src/app/main/`.
   - Right-click and select `Run As` > `Java Application`.

### Using the Application

- The app reads parcels from `parcel_data.csv` and organizes them for bin packing.
- Results/output will be shown in the console or saved to output files, depending on implementation.

## Data Format

The included `parcel_data.csv` uses the following columns:

| type       | weight | fragile | destination    |
|------------|--------|---------|----------------|
| Documents  | 1.6    | False   | Kuala Lumpur   |
| Furniture  | 49.7   | True    | Kota Kinabalu  |
| ...        | ...    | ...     | ...            |

## Implementation Highlights

- **AVL Tree**: Used for efficient searching, insertion, and deletion operations during bin packing.
  - See [`src/app/avltree/AVLTree.java`](src/app/avltree/AVLTree.java)
  - See [`src/app/avltree/AVLTreeNode.java`](src/app/avltree/AVLTreeNode.java)
- **CSV Parsing**: Reads parcel data from a CSV file.
- **Eclipse Project**: Ready-to-import with `.classpath` and `.project`.

## Contributors
<a href="https://github.com/cscheah5/dsa-bin-packing/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=cscheah5/dsa-bin-packing" />
</a>
