#Example of a layered architecture
There are three layeres in this solution, in the <code>src</code> directory. Each subdirectory in the <code>src</code> directory represents a layer. Additionally, the <code>driver</code> subdirectory contains the class with the main method, the driver for this application, which creates and initializes each layer and glues them together.

In a layered architecture, each layer is allowed to use only the layer immediatelly below it.
