# Library Management System

## Overview

The Library Management System is a Java-based application that allows users to manage a collection of books and e-books. This project implements basic functionalities such as adding, removing, searching, and updating media items in the library. The system uses Object-Oriented Programming principles to manage different types of media items.

## Features

- Add, remove, search, and update books and e-books.
- Store book objects in a list.
- Log actions performed on the library for easy debugging and tracking.

## Classes and Functionality
- MediaItem
- Represents a generic media item in the library.
- Attributes: title, author, category.
## Book
- Inherits from MediaItem.
- Additional attribute: isbn.
## EBook
- Inherits from MediaItem.
- Additional attribute: downloadUrl.
## Library
- Manages the collection of MediaItem objects.
- Methods: addItem, removeItem, searchItemsByTitle, updateItem.
## LibraryManagement
- Main class to run the application.
- Provides a menu for users to interact with the library.
## Logging
- Uses Java's built-in logging framework.
- Logs actions such as adding, removing, searching, and updating items.
