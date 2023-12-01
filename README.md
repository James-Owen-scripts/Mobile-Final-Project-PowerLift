A Project Report on

“PowerLift Coach” – A powerlifting training application.
# Introduction
The PowerLift Coach app coaches’ users through a 16-week period to prepare for a powerlifting meet as well as building muscle and strength conditioning. The application builds, and tracks user specific workouts based on a 5 workout per week basis (Monday, Wednesday, Thursday, Saturday, and Sunday). This application uses the block training model to slowly increase the user’s strength over a 16-week period. Each block is 4 weeks. The block are two hypertrophy blocks, one strength block, and one peaking block.
# Features
The app has the following features:
## User Registration and Login:
This allows users to create an account as well as log into an existing account. This will require the users email and password.
## User Profile:
This shows the users name, average body weight, average readiness score, current block, current week, and total workouts completed.
## Fitness level calculator:
The app allows the user to enter a 1 or 10 rep max or both of a specific exercise. The app uses this data to calculate a suggested weight they should use over 4 sets of the specific exercise. This feature is optional for users and doesn’t change their ability to user the app however it does enhance experience.
## User readiness and current weight:
This asks for the user’s current body weight on this day and the user’s readiness for the workout. For the readiness the application will ask for a rating out of 5 for each of the following:

- Tiredness
- Soreness
- Motivation
- Diet

This takes a score out of 20 to be stored in the user’s average readiness.
## Workout:
This has the user’s workout for the day. This has the main compound exorcise if applicable at the top with a top set for the day and the applicable back off sets that all contain the calculated suggested weight range and reps based off the users 1 or 10 rep max (squat bench and deadlift). There is 4-6 accessory exorcises as well. The user can swap out any exercises on the list. These exorcises have a calculated weight if applicable as well. 

To calculate the weight the user will lift the app uses this formula: 

**1RM = w ÷ [(1.0278) – (0.0278 x r)].**

**Where “1RM” is 1 rep max and “w” is weight in pounds and “r” is reps with that weight. To give the user a suggested value that corresponds to the typical weights in a commercial gym the number will be rounded down to the nearest integer pound increment divisible by 5.**

**The other principle that is used is the RPE model (Rate of Perceived Exhaustion). This is a rating out of 10 where 10 is maximum effort and 1 is no effort at all. This model is used for each block to slowly increase the amount of weight the user is lifting by increasing the projected RPE of each exercise. Squat, Bench, and Deadlift are decided based off the RPE model over the 4 weeks of each block. Each block starts at RPE 8.5 top set and works up to a RPE 10 top set at the end of the 4 weeks.** 
## Day tracker:
This allows the user to visually see which workouts they have completed as well as workouts they still need to complete for that week. This also has the current block the user is in and what the rep range for the top set of each block will be.
## Notifications:
The app notifies the user they need to complete a workout if they haven’t already.
# Final Thoughts
## Technology Stack:
The app uses Android Studio, Java, and XML.
## Challenges:
The challenges for this application were implementing the algorithm for a block style training approach that fluctuates based on the user’s input for their 1 or 10 rep max. The other issue is this app will attempt to implement a complicated algorithm for fitness that may be confusing for users. Simplifying this and making this app less intimidating to new was vital.
## Conclusion:
“PowerLift Coach” is a personalized app that helps users prepare for a powerlifting meet as well as build strength and conditioning. This app will help users stay on point on their training as well as not overwork themselves while working towards a meet. This app will help users obtain training goals and act as a coach by simplifying their training to maximize progress.
## Future Enhancements:
Some features that future enhancements will include are:

- Remembering users between uses of application.
- Implementing a data base for user data.
- Implementing variant set numbers.
- Implementing google sign in and remembering users based off device so no need for continuous sign in upon launching application.
