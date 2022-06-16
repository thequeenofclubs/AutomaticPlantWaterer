# Project Report - Automatic Plant Waterer
#### Gabe Gonsalves
##### April 10, 2022
###### Coded on macOS in Java

## Introduction
For the EECS 1021 Minor Project, the task was to make an automated plant-watering machine that monitors the moisture level in the soil and determines whether or not the plant is in need of watering. 


## Context
What is the plant-watering machine? The plant watering machine is a device that automates the watering of a houseplant using the grove board along with ``Firmata4j`` to determine the moisture content in the plant's soil. 

Why am I making the device? Many people who grow plants in their homes often find that they are unable to take care of them, or that they cannot master the watering schedule because they don't know how fast their plants consume water. Many houseplants die because of overwatering. This device will solve that problem, since it has a high-accuracy sensor that can determine how much water is in the soil, and therefore, how often to water the plant. Some applications of this device might be for people away on vacation. This device could be useful for plants such as orchids, that are typically difficult to grow since their soil needs to be completely dry before the plant gets watered. Due to the grove board's adjustable nature, the user of the device can adjust the level at which the soil is watered to accommodate for a plant's individual needs.

## Technical Requirements
- Detect the Moisture level in the soil
- Use OO-Logic to determine whether or not the soil is wet or dry, and in need of watering. 


### Flow-Chart of Decision Logic
![flowchart](/assets/Flowchart.svg)

## List of Components
- ``1x`` Seed Studio Grove Arduino Board
- ``2x`` Grove Interconnect Cable
- ``1x`` Plant
- ``1x`` Water Pump
- ``1x`` Silicone Tube
- ``1x`` MOSFET Board
- ``1x`` 9V Battery

### A Picture of the Setup
![img](/assets/IMG_6209.jpeg)


## Procedure

### Gathering the Materials
Since many of the materials were re-used from EECS 1011 last year, I only had to purchase a new USB cable and some replacement tubing (the old tubing had turned yellow and completely rigid). 

### Building The System
To build the system, I first set up the plant on top of the box that you can see in the image above. I got some water and connected the tube to the pump, and then set up the pump in the glass of water. Next, I connected the pump to the MOSFET board and then connected the MOSFET Board to the Battery. Finally, I connected both the moisture sensor and the MOSFET to the grove board and I was ready to begin programming.

### Writing The Code
Before beginning to write the code, I made a flowchart that would show the logic that I needed to have in order to make the project work. Then, once I had made the flow chart, I used my object-oriented programming knowledge to write code that would measure, process and interpret the moisture sensor values, and then enable or disable the pump based on the processed value. I used ``TimerTask`` to repeat the code on a 380ms interval and ``Firmata4j`` to communicate with the Grove board. Once the code was written and the System was built, it was time to test.

## Testing
To test the system, I first ran the code. I discovered that the moisture sensor delivered values between 500 and 900, contrary to my expectations which were that the sensor would return values between 0 and 1023. In addition, my expectation was that higher values meant more moisture. In actuality, the values that were closer to 900 represented a moisture value that was more dry than a value closer to 500. To respond to this, I modified my code so that it processed values in the new expected range. I then prepared the OLED display and was able to see the values on the screen. Another issue I discovered during my testing was that the pump would turn on when the soil was wet, and turn off when it was dry. To solve this, I edited my code so that the conditional statements were inverted. 

## Learning Outcomes
1. I demonstrated the ability to test and debug a given program and reason about its correctness by producing the code for this project, testing it and modifying it where necessary, such that it performs the task assigned.
2. I built an application that met the given requirement, since it implements both scheduled and event-driven actions and is successfully able to water the plant when the soil runs dry.
3. This application reacts to button presses and the moisture sensor by controlling the pump motor and pumping water into the plant when either requested by the user or necessitated by the sensor.
4. The program I made here was solved and written on a computer using the IntelliJ IDE and ``Firmata4J``.


