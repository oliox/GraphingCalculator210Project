# My Personal Project

## Graphing Calculator

- My application will be a graphing calculator. The user will be able to enter equations in the form y=mx+b, where m
is an integer or decimal >0 and b is an integer >= 0, and the program will display them in a graph. This would be usesful to anyone
taking grade 9/10 math who wants to see the graphical representation of their functions.

- This project is of interest to me because it seems challenging yet approachable. Problems such as how to parse an 
equation into a form usable by the code and visually representing and drawing the graph all seem possible yet 
challenging.

##User Stories

- As a user, I want to be able to add multiple equations into the graph.

- As a user, I want to be able to clear the graph of all equations.

- As a user, I want to see the graphical representation of all functions entered on the same graph.

- As a user, I want incorrectly formatted inputs to be ignored.

- As a user, I want to be able to exit the program with a command.

- As a user, I want to be able to save my equations to a file

- As a user, I want to be able to be able to load my equations from file 

- As a user, I want to be able to click an equation line on the graph and see key points highlighted.

- As a user, after seeing key points, I want to be able to click one to see it's coordinate location.

##Phase 4: Task 2
I implemented a type hierarchy for my button classes. There is an AbstractButton classes which features fields which all
 buttons need, and an abstract addListener method indicating that all buttons must feature a Listener for when they are 
 pressed.
 
Included classes: AbstractButton, LoadButton, SaveButton, ClearButton.

##Phase 4: Task 3
- One thing I would do to improve my project is try to reduce coupling with all the buttons. Each one exists within the 
GraphDrawer, and also has a field of the GraphDrawer, just so that they can call a single function when their button is
pressed. I believe using Javas observer pattern and calling update when the buttons are pressed could decrease the 
coupling.

- As an extension to the point above, most components added to the main graph panel also have a field of the 
graphDrawer. This creates a bi-directional association, and so I should probably refactor to enforce the reflexive relationship. 
 Rather than passing the graphDrawer as an argument in the constructor, setter methods should be added to both which 
 call the setter of the corresponding class.

- It would probably be preferable for GraphGraphicsPane to have a field for GraphDrawer and then access the graph 
through its getGraph() method. This keep the graphDrawer as essentially the only point of access to graph.

Save/loading code modeled off of JsonSerializationDemo.