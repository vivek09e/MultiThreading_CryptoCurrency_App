package com.assignment2.menufunctionlities;
import com.assignment2.exceptions.CoinNotFoundException;
import com.assignment2.exceptions.NotInRangeException;
import com.assignment2.exceptions.TraderNotFoundException;

import java.util.*;

/**
 * Contains the Menu Part of the Program i.e. the 5 functionalities required.
 */
public class MenuDriven extends Thread{
    /**
     * Print the Menu along with the choice option.
     */
    public static void menu(){
        System.out.println("Enter the Choice among the following: ");
        System.out.println("1. Give the name or code of a coin, retrieve all its details." +
                "\n2. Display top 50 coins in the market based on price." +
                "\n3. Show portfolio of a Trader" +
                "\n4. Show the total profit or loss they have made trading in the crypto market" +
                "\n5. Show top 5 and bottom 5 traders based on their profit/loss."+
                "\n6. Exit!");
    }

    /**
     * Perform the user input functionalities on the basis of user's choice.
     */
    public static void choice() {
        Scanner sc= new Scanner(System.in);
        String name="";
        boolean status=true;
        while(status){
            menu();
            System.out.print("Enter the Choice: ");
            String choice=sc.nextLine();
            switch (choice){
                case "1":
                    try {
                        System.out.println(MenuFunctions.searchCoin());
                    }catch (CoinNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Top 50 Coins! ");
                        MenuFunctions.displayTopNCoins(50);
                    }catch (NotInRangeException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    try{
                        System.out.println("Enter the Full Name of Trader:");
                        name=sc.nextLine();
                        MenuFunctions.displayPortfolio(name.toUpperCase());
                    }catch (TraderNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    try{
                        System.out.println("Enter the Full Name of Trader:");
                        name=sc.nextLine();
                        MenuFunctions.displayProfitLossOfTrader(name.toUpperCase());
                    }catch (TraderNotFoundException e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case "5":
                    try{
                        MenuFunctions.topNTrader(5);
                    }catch (NotInRangeException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "6":
                    status=false;
                    break;

                default:
                    System.out.println("Wrong input try again! ");
                    break;
            }

        }
        System.out.print("Program Ended!!");
    }

    /**
     * Helps to implement the menu functionalities run on the thread.
     */
    @Override
    public void run() {
        choice();
    }

}
