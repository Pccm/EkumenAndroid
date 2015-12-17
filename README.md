# EkumenAndroid
------------------Explication---------------
This App Contain 2 buttons, 1 button is to start the  turtle's walk to draw a star of 8 points, 
and the other button is to pause the walk.

Th turtle is a simulation in PC "turtlesim".

-----------------How to work-------------------
To Run this App, You must be the next:
1)Download and generate the .apk file.
2)Install this App in your cellphone.
3)Open a new terminal and execute : >> roscore
4)Open a new terminal and execute the turtlesim
  -execute the command:  >> source /opt/ros/indigo/setup.bash
  -execute the command:  >> rosrun turtlesim turtlesim_node 
5)Open a new terminal and execute the program "talk": 
  -go to the ROS workpace : >> cd ~/catkin_ws
  -execute the command:  >> source ./devel/setup.bash
  -execute the command:  >> rosrun [your-package] talker
6)Open your App Android and put the IP of your PC.
7)Ok!, your are ready to control the draw turtle with the app.

Note: The repository of "talk" : https://github.com/Pccm/Ekumen

Thanks.

