/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_game;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;

/**
 *
 * @author Ayxan
 */
public class Project_game {
    static List<Field> fields = new ArrayList<>();
    static List<Player> players = new ArrayList<>();
    static List<Integer> diceRolls = new ArrayList<>();
    static Map map;

    public static void main(String[] args) {
        try {
            run("input1.txt");
            playGame();
        } catch (InvalidInputException e) {
            System.err.println("incorrect input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("some unexcpected error: " + e.getMessage());
        }
    }

    public static void run(String filename) throws InvalidInputException, EmptyFileException, FileNotFoundException, NumberFormatException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            if (!br.ready())
            {
                throw new EmptyFileException("empty file");
            }
            
            int field_numbers = Integer.parseInt(checkNumber(br.readLine().trim()));


            for (int i = 0; i < field_numbers; i++) {
                String[] field_info = br.readLine().split(" ");
                if (field_info.length < 1) throw new InvalidInputException("format field error");

                String field_type = field_info[0];

                switch (field_type) {
                    case "Property":
                        fields.add(new PropertyField(i));
                        break;
                    case "Service":
                        if (field_info.length != 2) throw new InvalidInputException("invalid input");
                        int serviceCost = Integer.parseInt(checkNumber(field_info[1]));
                        fields.add(new ServiceField(i, serviceCost));
                        break;
                    case "Lucky":
                        if (field_info.length != 2) throw new InvalidInputException("invalid input");
                        int luckyReward = Integer.parseInt(checkNumber(field_info[1]));
                        fields.add(new LuckyField(i, luckyReward));
                        break;
                    default:
                        throw new InvalidInputException("non existing field");
                }
            }
            map = new Map(fields);

            int playerCount = Integer.parseInt(checkNumber(br.readLine().trim()));

            for (int i = 0; i < playerCount; i++) {
                String[] playerData = br.readLine().split(" ");
                if (playerData.length != 2) throw new InvalidInputException("error player format");

                String playerName = playerData[0];
                String strategy = playerData[1];

                switch (strategy) {
                    case "Greedy":
                        players.add(new GreedyPlayer(playerName));
                        break;
                    case "Careful":
                        players.add(new CarefulPlayer(playerName));
                        break;
                    case "Tactical":
                        players.add(new TacticalPlayer(playerName));
                        break;
                    default:
                        throw new InvalidInputException("non existing category");
                }
            }
            int diceCount = Integer.parseInt(checkNumber(br.readLine().trim()));

            String[] diceRollsData = br.readLine().split(" ");
            for (int i = 0; i < diceCount; i++)
            {
                int diceRoll = Integer.parseInt(diceRollsData[i]);
                try {   
                    if (diceRoll > 6 || diceRoll < 0) throw new InvalidInputException("number out of dice roll number");
                    diceRolls.add(diceRoll);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("out of index");
                }
            }

            br.close();

        } catch (FileNotFoundException e) {
            throw new InvalidInputException("File not found: " + e.getMessage());
        }  catch (IOException e) {
            throw new InvalidInputException("reading error: " + e.getMessage());
        }
        
    }
    private static String checkNumber(String input) throws InvalidInputException {
        try{
            int value = Integer.parseInt(input);
            if (value < 0) {
                throw new InvalidInputException("negative value");
            }
            return input;
        } catch (NumberFormatException e){
            throw new InvalidInputException("incorrect unexcpected value");
        }
            
    }

    public static void playGame() {
    System.out.println("Starting the game!");
    int rollIndex = 0;

    while (players.size() > 1)
    {
        for (Player player : new ArrayList<>(players))
        {
            if (players.size() > 1)
            {
                if (rollIndex > diceRolls.size())
                {
                    System.out.println("nobody wins, there are still players");
                    return;
                }

                int roll = diceRolls.get(rollIndex);
                rollIndex += 1;

                System.out.println(player.getName() + " got " + roll);
                player.movePosition(roll, map.getSize());

                Field field = map.getField(player.getPos());
                field.stepOn(player);

                if (player.noMoney())
                {
                    System.out.println(player.getName() + " has no more money.");
                    players.remove(player);
                }
            }
        }
    }
    Player winner = players.get(0);
    System.out.println("Winner: " + winner.getName());
    System.out.println("Balance: " + winner.getBalance());
    System.out.println("Properties Owned: " + winner.getOwnedFields().size());
    }
}