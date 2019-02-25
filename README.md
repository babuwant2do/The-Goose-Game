


# The Goose Game Kata
##Description
Goose game is a game where two or more players move pieces around a track by rolling a die. The aim of the game is to reach square number sixty-three before any of the other players and avoid obstacles. ([wikipedia](https://en.wikipedia.org/wiki/Game_of_the_Goose))

## Development

### prerequisites
Before build and run this project, following dependencies must be install and configure the on your machine:

1. [Java 8 or higher][]: Java 8 is used for this project. So, Java 8 or higher is must. Details installation instruction is [here.](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html#A1097257)
2. [Maven][]: Maven is used to build and manage the project. For install Maven you can follow [Installing Apache Maven](https://maven.apache.org/install.html)

3. This project can be import in the eclipse and run from there.

After installing Java and Maven, following command should be able to execute on the machine to install dependencies, compile, test and run the project.

### Instruction to Run

First download the source code and go to the root folder using command:

```js
    cd /<path to download folder>/GooseGame/
```
To install all the dependencied of the project execute: 
```js
    mvn clean install
```
To compile the peoject :
```js 
    mvn compile
```
To launch application's tests, run:
```js
    mvn test
```
To run(start) the game:  
```js
    mvn exec:java -Dexec.mainClass=com.wordpress.babuwant2do.goosegame.App
```

## What is special?

- The Game board can be configurable. Any kind of board can be made using **BoardBuilder** class with different kind , different number and different arrangement of **space**. **Builder pattern** and **Factory pattern** is used to easily configure on demand.
- A new kind of 'space' can be introduced very easily by extending 'Location' or any subcalss of it. 
- New type of Action can be introduce easily by Extending 'NodeDecorator'. **Decorator design pattern** is used to adapt with new type of action.
- Wrote few Custom exception to better understand and handle exception.
- User input are validated and users have flexiblity to insert input in different format like:
```js
   If user move is 4 ,2 user can type in any of the following form: 
   "move Pippo 4, 2"
   "move Pippo 4,2"
   "move Pippo 4 , 2"
   "   move    Pippo 4, 2"
   "   move    Pippo 4   , 2     "
   and so on... 
   ```
- This project is **testable** and write many test case to make sure every thing is works well.



## Additional Features
### 1. Help
Syatem will print all the available **Instruction**.
```js
   the user writes: "help"
   the system responds: < all available command >
```

### 2. Reset
At any point user can reset the board and start a game from the begining. After a player **Wins**, this command is mandatory to start a new game. 

```js
   the user writes: "reset"
   the system reset the game
```


### 2. Exit
To exit the game and close the application. 

```js
   the user writes: "exit"
   game terminate and the system exits.
```


## Basic Features

### 1. Add players
As a player, I want to add me to the game so that I can play.

**Scenarios:**
1. Add Player
   ```js
   If there is no participant
   the user writes: "add player Pippo"
   the system responds: "players: Pippo"
   the user writes: "add player Pluto"
   the system responds: "players: Pippo, Pluto"
   ```

2. Duplicated Player
   ```js
   If there is already a participant "Pippo"
   the user writes: "add player Pippo"
   the system responds: "Pippo: already existing player"
   ```

### 2. Move a player
As a player, I want to move the marker on the board to make the game progress

**Scenarios:**
1. Start
   ```js
   If there are two participants "Pippo" and "Pluto" on space "Start"
   the user writes: "move Pippo 4, 2"
   the system responds: "Pippo rolls 4, 2. Pippo moves from Start to 6"
   the user writes: "move Pluto 2, 2"
   the system responds: "Pluto rolls 2, 2. Pluto moves from Start to 4"
   the user writes: "move Pippo 2, 3"
   the system responds: "Pippo rolls 2, 3. Pippo moves from 6 to 11"
   ```

### 3. Win
As a player, I win the game if I land on space "63"

**Scenarios:**
1. Victory
   ```js
   If there is one participant "Pippo" on space "60"
   the user writes: "move Pippo 1, 2"
   the system responds: "Pippo rolls 1, 2. Pippo moves from 60 to 63. Pippo Wins!!"
   ```

2. Winning with the exact dice shooting
   ```js
   If there is one participant "Pippo" on space "60"
   the user writes: "move Pippo 3, 2"
   the system responds: "Pippo rolls 3, 2. Pippo moves from 60 to 63. Pippo bounces! Pippo returns to 61"
   ```
### 4. The game throws the dice
As a player, I want the game throws the dice for me to save effort

**Scenarios:**
1. Dice roll
   ```js
   If there is one participant "Pippo" on space "4"
   assuming that the dice get 1 and 2
   when the user writes: "move Pippo"
   the system responds: "Pippo rolls 1, 2. Pippo moves from 4 to 7"
   ```

### 5. Space "6" is "The Bridge"
As a player, when I get to the space "The Bridge", I jump to the space "12"

**Scenarios:**
1. Get to "The Bridge"
   ```js
   If there is one participant "Pippo" on space "4"
   assuming that the dice get 1 and 1
   when the user writes: "move Pippo"
   the system responds: "Pippo rolls 1, 1. Pippo moves from 4 to The Bridge. Pippo jumps to 12"
   ```

### 6. If you land on "The Goose", move again
As a player, when I get to a space with a picture of "The Goose", I move forward again by the sum of the two dice rolled before

The spaces 5, 9, 14, 18, 23, 27 have a picture of "The Goose"

**Scenarios:**
1. Single Jump
   ```js
   If there is one participant "Pippo" on space "3"
   assuming that the dice get 1 and 1
   when the user writes: "move Pippo"
   the system responds: "Pippo rolls 1, 1. Pippo moves from 3 to 5, The Goose. Pippo moves again and goes to 7"
   ```

2. Multiple Jump
   ```js
   If there is one participant "Pippo" on space "10"
   assuming that the dice get 2 and 2
   when the user writes: "move Pippo"
   the system responds: "Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"
   ```

### 7. Prank (Optional Step)
As a player, when I land on a space occupied by another player, I send him to my previous position so that the game can be more entertaining.

**Scenarios:**
1. Prank
   ```js
   If there are two participants "Pippo" and "Pluto" respectively on spaces "15" and "17"
   assuming that the dice get 1 and 1
   when the user writes: "move Pippo"
   the system responds: "Pippo rolls 1, 1. Pippo moves from 15 to 17. On 17 there is Pluto, who returns to 15"
   ```

