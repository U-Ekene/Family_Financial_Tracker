# Family Financial Tracker

A Java-based application designed to help track and organize family financial transactions.  
The project focuses on reading transaction data from CSV/XLSX files, categorizing expenses automatically, and validating core logic through unit tests.

## ✨ Technologies
- Java  
- JavaFX (UI structure and styling)  
- Apache POI (Excel file handling)  
- JUnit 5 (unit testing)

## 🚀 Features
- Transaction model for structured income and expense tracking
- CSV and Excel transaction import support
- Automatic category assignment based on transaction descriptions
- Configurable categories via text file
- JavaFX-based project structure with separation of model, datastore, view, and styles
- Unit tests covering transaction logic, datastore behavior, and data parsing

## 🧠 The Process
This project was developed to practice object-oriented design, data parsing, and test-driven development in Java.  
The codebase is organized into clear layers (model, datastore, view) and emphasizes readability, modularity, and validation through automated tests. Special attention was given to handling real-world data formats such as CSV and Excel files.

## 📁 Project Structure
- src/main/java – application source code  
- src/test/java – unit tests for core logic  
- test_transactions.xlsx – sample Excel file used for testing transaction import logic  

## 📝 Notes
Sample data files are included with the project to match the existing file-path loading logic and allow the application and tests to run without additional configuration.
