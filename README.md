# PROG209 – Basic Interactive GUI (Java Swing)

Individual assignment for **PROG209 Event Driven Programming**, Limkokwing University of Creative Technology, Sierra Leone.
Student: **Sahr** · Examiner: Amandus Benjamin Coker

## What it does
- Opens an 800 x 800 `JFrame` titled **"Java Swing Assignment"** with a custom application icon (`ImageIcon`).
- A `JPanel` holds a `JLabel` reading *"Welcome to Java Swing First Individual Assignment by Sahr"* with an image above it.
- **Change Text** – replaces the label text with what you type (Enter also works).
- **Change Background** – opens a colour picker and changes the panel background.
- **Reset** – restores the original text and colour.
- The application exits when the close button is clicked.

## Inheritance and constructor
- `BaseWindow` (parent class) – its constructor instantiates the `JFrame frame` and sets title, size and close operation.
- `SwingAssignment extends BaseWindow` (child class) – its constructor calls `super("Java Swing Assignment", 800, 800)` and then builds the panel, label and buttons.

## Project structure
```
src/
  BaseWindow.java        parent class
  SwingAssignment.java   main class (child)
  KEMOH.png               application icon
wireframe/               Figma wireframe export
screenshots/             screenshots of the running app
```

## How to run
**IntelliJ IDEA:** open the folder, mark `src` as Sources Root, run `SwingAssignment.main()`.
**VS Code:** install "Extension Pack for Java", open the folder, open `SwingAssignment.java`, click **Run**.
**Terminal:**
```
javac -d out src/*.java
cp src/KEMOH.png out/
java -cp out SwingAssignment
```

## Requirements
JDK 17 or newer.
