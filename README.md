# 2D Array Operations (Java GUI)

A lightweight Java AWT/Swing desktop app to create and explore two‑dimensional integer arrays. It provides interactive menus to generate arrays, compute statistics, search values, and perform matrix operations with a visual grid display.

## Features
- Create a random 2D array with custom rows/cols and value range
- Statistics: minimum, maximum, average, standard deviation
- Operations: search for a value, add, subtract, and multiply arrays
- Visual output: draws arrays in a grid with labels and results
- Resizable window; redraws on resize

## How It Works
- UI shell: `Array2DOperations` (Frame with menus, drawing surface)
- Core logic: `TwoDArray` (array creation, stats, search, math)
- Rendering: values and outlines drawn using `Graphics`
- Input: dialogs (`JOptionPane`) prompt for sizes, ranges, and search keys

## Requirements
- JDK 8+ (AWT/Swing included)
- Windows/macOS/Linux

## Quick Start (Windows PowerShell)
```powershell
cd "g:\My Drive\GITHUB\java\java_array_2d_operations_gh"

# Compile
javac Array2DOperations.java TwoDArray.java

# Run (opens a desktop window)
java Array2DOperations
```

## Usage Guide
- File → About: Shows a summary of features
- Two Dimensional Array → Create New Array: Configure rows, columns, and value range, then generate
- Statistics → (Min/Max/Avg/Std Dev): Computes and displays results below the grid
- Operations → Array Search: Prompts for a key and shows location if found
- Operations → Array Add/Subtract: Prompts for a second array and displays result
- Operations → Array Multiply: Prompts for a second array and displays `A × B`

Notes:
- Matrix multiply requires compatible sizes: `A[rows × k] × B[k × cols]`
- Values are integers; average and standard deviation are rounded for display

## Project Structure
- `Array2DOperations.java`: Window, menus, event handling, painting
- `TwoDArray.java`: Creation, statistics, search, add/subtract/multiply helpers

## Common Questions
- Window doesn’t update after resize? The app listens to resize events and repaints automatically.
- Where are inputs entered? Via dialogs (rows, columns, low/high values, search keys).
- Why is multiply disabled sometimes? If dimensions are incompatible, an error is thrown to prevent invalid math.

## Next Steps
- Add input validation loops (reject invalid/empty values)
- Export arrays/results to CSV
- Color-code cells by magnitude or highlight search hits
- Keyboard shortcuts for frequent actions

## License
This example is provided as-is for educational and demonstration purposes.
