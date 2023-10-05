## <p align="center">DEISI Jungle</p>

<p align="center">🦁🐘🦒 Welcome to the Animal Olympics Game! 🏁🏆</p>

![](deisi-jungle.jpg?raw=true "Deisi Jungle")

<p align="justify"> One day, in a savannah far (far) from here, the animals decided to find out who would be the best athlete among them. 
To do so, they defined a set of sporting events that would compete with each other - whoever won more events would be considered the King of the Jungle. 
Soon the news reached Tarzan, who did not want to stop participating in the events, convinced that he would easily win them. 
The first race is the track race. The animals will gather on a track and compete to determine which athlete is the best. </p>

<p align="justify"> Join the wild adventure in a Java-based game set in the heart of a distant savannah. 
In this exciting game, animals from all walks of life have come together to compete for the title of King of the Jungle. </p>

# UML diagram
![](diagrama.png?raw=true "UML diagram")

### Modeling choices

##### Classes
- <p align="justify">GameManager — responsible for managing the game.</p>
  <br/>
- <p align="justify">Player — Represents the class that encapsulates information and features specific to players in the game.
   Stores attributes relevant to the characterization of players and defines their behavior during the game.</p>
  <br/>
- <p align="justify">MovementResult — Represents the result of a move made by a player. It is characterized by a variable 
   "code" of type MovementResultCode and, optionally, by a variable "message" of type String, which presents a descriptive 
   message in the viewer.</p>
  <br/>
- <p align="justify">MovementResultCode — Enumeration that defines the possible result codes for a movement.
    Includes the values INVALID_MOVEMENT, NO_ENERGY, VALID_MOVEMENT and CAUGHT_FOOD, which correspond to different situations of the game, 
    such as movement errors, lack of energy, valid movement and interaction with food.</p>
  <br/>
- <p align="justify">InvalidInitialJungleException — Class responsible for throwing exceptions in situations 
  of invalid initial game map configuration.
  Especially related to the composition and position of elements such as players and food. 
  This exception is triggered when the initial conditions are not in accordance with the rules of the game.</p>
  <br/>

##### Abstract classes

  <p align="justify">Species bring variety to the game and give life to the different species that players can choose from.</p>

- <p align="justify">Species — with an essential role in the game, the Specie class represents all species present. 
            Contains the essential attributes to characterize the different species at stake, along with methods and functions 
            that process the specific behaviors of each species during the game, in order to ensure that each species is unique.</p>
  

  ##### Classes son (Species)

      > Elephant - contains the specific information about elephant species, methods/functions that process the relevant behaviors of the elephant species for the context of the game. 
      > Leao - contains the specific information about lion species, the methods/functions that process the relevant behaviors of the lion species for the context of the game.
      > Bird - contains the specific information about bird species, the methods/functions that process the relevant behaviors of the bird species for the game context.
      > Turtle - contains the specific information about turtle species, the methods/functions that process the relevant behaviors of the turtle species for the context of the game.
      > Tarzan - contains the specific information about Tarzan species, the methods/functions that process the relevant behaviours of the Tarzan specie for the game context.
      > Unicorn - contains the specific information about unicorn species, the methods/functions that process the relevant behaviors of the unicorn species for the context of the game.

##### Child classes (New Species)

| Species | Id  | Name    | Image                               | Initial energy | Energy consumption | Energy gain at rest | Speed |
|---------|-----|---------|-------------------------------------|----------------|--------------------|---------------------|-------|
| Giraffe | G   | Giraffe | ![](giraffe.png?raw=true "Giraffe") | 150            | 4                  | 3                   | 2..3  |

> Characteristics (New Specification):

1. **Feeding:**
   <p align="Justify">- Giraffes have long necks that easily reach leaves in trees, 
   its source of energy rich in fiber and nutrients. </p>


2. **Initial energy:**
   <p align="Justify">- Start with 150 power units due to their size. </p>


3. **Power consumption:**
   <p align="Justify">- Spend 4 units of energy during the race due to body size. </p>


4. **Energy gain:**
   <p align="Justify">- Gain 2 units of energy during rest, saving while digesting.. </p>


5. **Minimum speed 2 and maximum speed 3:**
   <p align="Justify">- Although not the fastest,
   use height and behavior strategies. </p>


6. **Do not feed on mushrooms:**
   <p align="Justify">- Because it is an animal with a diet specialized in leaves of trees, shrubs and herbs, 
   giraffes are not affected by the food magic mushrooms. Whenever a player "giraffe" moves to 
   a house containing mushrooms, its energy is not affected by its food, and the player’s food history 
   should not be updated. </p>

<br/>

  <p align="Justify">Along the terrain will be scattered various foods of different types. When a
  player runs into a house with a food, he is required to ingest it. This can be good or
  bad. </p>

- <p align="Justify">Food - essential to the game, represents all types of food available.
  Contains the attributes to characterize the foods in play, as well as the methods and functions 
  that process the relevant behaviors of each food. Such as abstract class Specie, 
  the code avoids redundancies, providing a solid foundation of the concepts of inheritance and polymorphism. </p>

  ##### Classes child (Food)

      > Water — contains the specific information about water, methods/functions that process the behaviors relevant 
             to the context of the game water.
               ○ Ingested by carnivores or herbivores, increases to energy in 15 units.
               ○ Ingested by omnivores, increases to energy in 20%.

      > Herb — contains the specific information about the herb, methods/functions that process the relevant behaviors 
             from weed to the context of the game.
               ○ Ingested by herbivores or omnivores, increases to energy in 20 units.
               ○ Ingested by carnivores, reduced to energy in 20 units.

      >   BunchesOfBananas — contains specific information on banana bunches, methods/functions processing 
             the relevant behaviors of the clusters of bananas for the context of the game.
               ○ Can be ingested by any animal.
               ○ Increases energy by 40 units.
               ○ There are only 3 bananas in the bunch. The animals that fall into a house with this
                 food consumes one of these bananas. When bananas run out, the
                 food ceases to produce effect. That is, from the 4th time any animal
                 comes to a house containing this food, it is no longer affected.

               ○ How to eat many bananas causes gastric difficulties, if the same player
                 consuming more than one banana, the 2nd and 3rd bananas draw energy instead of
                 give, in the same proportion (40U).

      >   Meat — contains meat-specific information, methods/functions that process behaviour 
             relevant to the context of the game.
               ○ If ingested by carnivores (ex: Leo) or omnivores (ex: Tarzan), increases the
                 energy in 50 units.
               ○ Herbivores ignore this food, so nothing happens to them.
               ○ It deteriorates as time passes. It is only edible in the first 12
                 plays. From there it is toxic - if ingested, reduces the energy of the
                 animal.

      >   MagicMushroom — contains the specific information on magic mushrooms, methods/functions that process 
            the relevant behaviors of magic mushrooms for the context of the game.
               ○ All animals can eat.
               ○ As they are magical, their behavior varies from mushroom to mushroom and
                 from play to play.
               ○ Each mushroom has associated a number (N) between 10 and 50, which is generated
                 randomly in its creation.
               ○ If they eat the mushroom in even plays, the animals increase by N% to
                 your energy.
               ○ If you eat the mushroom in odd turns, it becomes poisonous and
                 reduce their energy by N%.
  

### Justification of the modelling choices

> Avoid duplication of code:
> 
  <p align="justify"> By applying inheritance and polymorphism, the focus was to avoid code redundancies, 
    to facilitate implementation, readability and understanding of game logic. 
    By sharing common behaviors across the base classes, such as Specialty and Food, each child class 
    was enabled to have specific and unique behaviors.</p>

> Define common characteristics and maintain distinct behaviours: 
> 
  <p align="justify"> Inheritance provided the basis for a centralized structure of characteristics shared among all species and foods. 
    This allowed the flexible incorporation of unique behaviors for each species and food. This approach
    made it easier to understand game logic and implement new features without affecting other parts of the code.</p>

# Demo video (pt-pt)
- A functional demo of the game can be seen [here](https://youtu.be/iSjHJs5aGWg).

## The game screen
<p align="justify"> 🐾 Challenge your friends, set new records, and experience the thrill of the Animal Olympics. 
Dive into the adventure today and prove to be the best athlete in the jungle! </p>

![alt_text](The_jungle.png?raw=true "Deisi Jungle")

## How to Play:

- Clone this repository on your local machine.
- Run the game in a compatible Java environment.

## Technical details:

- Java version: Java 17
- Version of Kotlin: 1.7