# Three Card Poker

A JavaFX FXML implementation of Three Card Poker casino game.

## Project Description

This project made is a graphical implementation of the popular casino game Three Card Poker. Players can play against a dealer, place various types of bets, and enjoy animations and visual feedback during gameplay. Was made for our CS 342 software design class at UIC. 

## Features

- Interactive gameplay for two players
- Multiple betting options:
  - Ante bet
  - Pair Plus bet
  - Play bet
- Professional card animations
- Real-time play log
- Detailed hand evaluation
- Running total of winnings
- Multiple screen transitions
- Clean, casino-style interface

## Game Rules

- Each player must place an Ante bet to play
- Optional Pair Plus side bet available
- Each player receives three cards
- Players can either:
  - Play (by matching Ante bet)
  - Fold (forfeit their bets)

### Pair Plus Payouts:

- Straight Flush: 40:1
- Three of a Kind: 30:1
- Straight: 6:1
- Flush: 3:1
- Pair: 1:1

## Technical Requirements

- Java 11 or higher
- JavaFX SDK
- Maven
## Installation & Running

1. Clone the repository
2. Ensure JavaFX SDK is properly configured
3. Build the project using Maven:
   ```shell
   mvn clean install
4. Run the application
   ```shell
   mvn javafx:run
## Game Tutorial (How to play)

### Starting the Game
- Start the game from the welcome screen.

### Placing Bets
- Place Ante bet (required) and Pair Plus bet (optional).

### Dealing the Cards
- Click 'Deal' to receive cards.

### Choosing an Action
- Choose to either:
  - Click 'Play' to continue (costs additional bet equal to Ante).
  - Click 'Fold' to forfeit current bets.

### Viewing Results
- Watch dealer reveal cards and see results.
- Winnings are automatically calculated and updated.

### New Round
- New round begins after a 5-second pause.

## Controls

- **Deal Button**: Starts a new hand.
- **Play Button**: Continue with hand (matches Ante bet).
- **Fold Button**: Surrender hand and forfeit bets.
- **Fresh Start**: Reset game and clear all bets/winnings.
- **New Look**: Change game appearance.

## Future Enhancements
- Multiplayer networking capability
- Sound effects
- Statistics tracking
- Player profiles
- Additional betting options
## Authors
Thomas Webber and Shane Abraham

