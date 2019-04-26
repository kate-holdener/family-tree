# family-tree
Example of how software architecture impacts maintainability and code reuse in a project
## Overview
This project implments a fairly simple "family tree" database, which allows the user to:
* Enter his/her name along with the parent's name
* Look up relatives

We'll look at different ways to structure and package the code and how this impacts the overall project quality.

There are several directories at the root of the project, each representing a different approach to architect the solution.
* <code>monolithic</code> - all the code is in one directory, uses Java SWING api as the user interface, and CSV file to load/store the data. For a simple thorw away project, monolithic architecture works fine. However, if you plan on maintaining, extending, and reusing your code, monolithic architecture is a bad idea.
