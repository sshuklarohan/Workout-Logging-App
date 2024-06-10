# Workout-Logging-App



## About my project
This application will track the progression of multiple exercises in the gym. I want to have a system that allows
the user to add any exercise they want and track their overall progression overtime by keeping track of their weight 
used along with reps completed. A user should be able to log their sets and reps and the program would be able to 
display all their previous sets of the same exercise.A user should also be able to remove the log of a set if they
possibly inputted the wrong information. This project is interesting to me because I have been looking for an 
application like this for the gym where I can log all my exercises and track how I progressively get stronger. 
This application would be used by anyone who goes to the gym and wants a simple way to track their progress. 





## User stories

- As a user, I want to be able to add an Exercise that I want to track
- As a user, I want to be able to add a set to any of my exercises
- As a user, I want to be able to see a table of previous sets of an exercise
- As a user, I want to be able to remove a set from an exercise
- As a user, when I select the quit option from the application menu, I want to be reminded to save my recorded sets 
and exercises to file and have the option to do so or not. 
- As a user, when I start the application, I want to be given the option to load my FitTrackApp list from file.



*Possible additions*
- A graph showing how overall volume(weight * reps) has increased from set to set
- A one rep max calculator, takes your most recent set and provides your estimated one rep max


## Instructions for grader
- To add an exercise, click 'add' on the menu bar, then click add exercise then input the name of the exercise you
want to add.
- To add a set to an exercise, open an exercise using the sidebar, then click the green plus icon, and input your weight
and reps performed. Once you clock enter your set will be added to your exercise. 
- To remove a set click the red "x" button next to the corresponding set
- To sort the sets by 1 rep maxes, open the menu next to the sort by button, select "One rep max" and then
click the sort by button
- To save the state of my application you use the "save/load" option on the menu bar and select save
- To load the state of my application you use the "save/load" option on the menu bar and select load
- To locate my visual component go to the "home" tab

## Phase 4: task 
Thu Apr 13 14:25:19 PDT 2023
Exercise added
Thu Apr 13 14:25:19 PDT 2023
Exercise added
Thu Apr 13 14:25:19 PDT 2023
Exercise added
Thu Apr 13 14:25:27 PDT 2023
Set Added
Thu Apr 13 14:25:33 PDT 2023
Set Added
Thu Apr 13 14:25:34 PDT 2023
Set removed
Thu Apr 13 14:25:41 PDT 2023
Exercise added
Thu Apr 13 14:25:45 PDT 2023
Saved Exercises to ./data/exercises.json





## Phase 4: task 3
One change that I would make if I had more time would be to design the Exercises class using the singleton pattern.
Currently, there is only one call to "new Exercises", and whenever I need access to Exercises in another class I pass
it as a parameter. Using the singleton pattern I wouldn't have to worry about passing it as a parameter, I could just
have access to all my exercises in every class. Additionally, I wouldn't have to worry about any new instances of 
"Exercises". 


