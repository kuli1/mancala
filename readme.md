**Mancala Game by Lukasz**

My general approach is to deliver good model that will let to play the mancala game. 
Other components like events or application service , spring boot are only to show the idea how it can be extended. 

1) Create simple model of the mancala game using TDD. I didn't focus here on the way how it's implemented just wanted to have green tests.
2) Challange the model with basic use cases 
3) Refactor the model and keep tests passing. I refactored the model completly to make it nicer to read and reduce some unused code. 
4) Root Aggreegate is a Game, it should have all the rules and expose methods to play the game.
5) Factory is reponsible to create new game according to the rules.
6) Events are created here to do simple cqrs , read model can be used later to deliver game data like stats or others.
7) In current implementation Game aggregate is delivering all the information about the game. Data is hold in te memory.
8) GameService is an application service that is exposed as API for other services like REST or other UIs.
9) GameService is quite poor in terms of DTOs that it can return.. I didn't spend time to work on this part. 
In the production it should be much more extended .
10) 