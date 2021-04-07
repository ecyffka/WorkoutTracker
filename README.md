# Workout Tracker!

## (Track both strength workouts and runs.)

- This is an application for people who exercise. **(Meaning me. It's for me.)**
- It will allow users to track both runs (date, distance, route, notes) and 
strength workouts (name, date, rounds, notes, and a list of exercises.\
** rounds: rounds through a circuit. I like circuits, therefore everyone
likes circuits.\
** exerise: an exercise consists of a name and any equipment needed
- This project is of interest to me because I exercise just about every day. I tend to 
 just scribble my workouts down on scraps of paper, which later vanish into the abyss. It would be nice to 
have an application to help track my workouts to help me remember what I did in the past, what worked, and what didn't.
The run log aspect is more just for record keeping, and, in a later phase of this project, 
to be able to see stats for the week/month/year. I'm a pretty casual runner (ie. don't care about
time, pace, splits, etc.), but tracking things gives me a little dopamine kick, so that's a nice aspect as well.

## User Stories
- As a user, I would like to be able to input a circuit (name, date, rounds, list of exercises).
- As a user, I would like to be able to assign each exercise in my circuit a name and any equipment used.
- As a user, I would like to be able to log a run (date, distance, route, and notes).
- As a user, I would like to be able to add notes to my workout (run or circuit) after it has been logged.
- As a user, I would like a print out summary of the workout (run or circuit) I just logged.
- As a user, I would like to be able to search for workouts by name or date.
- As a user, I would like to be able to save my workout log to file.
- As a user, I would like to be able to retrieve my saved workout log.

## Phase 4: Task 2
Chosen task: Include a type hierarchy in your code.
- Classes Circuit and Run extend abstract class Workout. Both override abstract methods printWorkout()
and toJson() in unique ways, appropriate to their functionality.

## Phase 4: Task 3
Proposed design changes:
- Have the GUI-related Panel classes (CircuitPanel, LookupPanel, RunPanel) implement a Panel interface with
 methods setVertical(), setHorizontal(), and setFields(). \
 ** LookupPanel and RunPanel both initialize all their fields within the constructor because they're less 
 complicated than CircuitPanel, and they fit within the 25 line limit. For clarity and consistency between 
 Panel classes, I'd like to break those steps out into setFields() methods.
- Split SavePanel and LoadPanel out into their own classes. Again, because they were fairly simple and fit
within line limits, I left them in the GUI class, but as they have their own functions distinct from the GUI 
as a whole, they should have their own classes. (Because they're simple classes and don't require group 
formatting or a lot of field set up, however, they would not implement the Panel interface.)
- Exception handling for all inputs to make sure date types and formatting of input is correct.