import java.util.*;


class VendingMachine{

    int twentyFiveCoin;
    int fiftyCoin;
    int hundredCoin;

    boolean inputCoin(int coin){

        if(coin == 25){
            twentyFiveCoin++;
            return true;
        }

        if(coin == 50){
            fiftyCoin++;
            return true;
        }

        if(coin == 100){
            hundredCoin++;
            return true;
        }


        return false;
    }

    int pushCoffeeButton(){
        int totalCash = fiftyCoin*50 + twentyFiveCoin*25 + hundredCoin*100; // Total cash inserted into the machine
        int change = totalCash-225; // Will be used to calculate if there's enough change in the machine
        int coinsChange = change; // It's what will be returned to the customer upon purchase

        if(totalCash<225){
            System.out.println("Not enough cash");
            return totalCash;
        }

        serveCoffee();

        if(change>0){
            while(hundredCoin>0 && change-100>=0){
                change-=100;
                hundredCoin--;
            }
            while(fiftyCoin>0 && change-50>=0){
                change-=50;
                fiftyCoin--;
            }
            while(twentyFiveCoin>0 && change-25>=0){
                change-=25;
                twentyFiveCoin--;
            }
        }
        if(change==0)
            return coinsChange;

        else{
            System.out.println("Not enough change in the machine");
            return -1;
        }

    }

    int pushTeaButton(){
        int totalCash = fiftyCoin*50 + twentyFiveCoin*25 + hundredCoin*100; // Total cash inserted into the machine
        int change = totalCash-350; // Will be used to calculate if there's enough change in the machine
        int coinsChange = change; // It's what will be returned to the customer upon purchase

        if(totalCash<350){
            System.out.println("Not enough cash");
            return totalCash;
        }

        serveTea();

        if(change>0){
            while(hundredCoin>0 && change-100>=0){
                change-=100;
                hundredCoin--;
            }
            while(fiftyCoin>0 && change-50>=0){
                change-=50;
                fiftyCoin--;
            }
            while(twentyFiveCoin>0 && change-25>=0){
                change-=25;
                twentyFiveCoin--;
            }
        }

        if(change==0)
            return coinsChange;

        else{
            System.out.println("Not enough change in the machine");
            return -1;
        }
    }

    void serveCoffee(){
        System.out.println("Coffee served.");
    }

    void serveTea(){
        System.out.println("Tea served.");
    }
}



class OptionsMenu{
    VendingMachine machine = new VendingMachine();

    void mainMenu(){
        System.out.println("Welcome to the vending machine. Available products:");
        System.out.println("1. Coffee $2.25");
        System.out.println("2. Tea $3.50\n");

        coinsMenu();
    }

    void coinsMenu(){
        Scanner scn = new Scanner(System.in);

        System.out.println("Please deposit coins (only 25, 50 and 100 cents coins allowed), enter 0 to stop");
        while(true){
            System.out.print("Input: ");
            int coin = scn.nextInt();

            if(coin==0)
                break;

            if(!machine.inputCoin(coin))
                System.out.println("Invalid coin, try again");
        }

        int totalCash = machine.fiftyCoin*50 + machine.twentyFiveCoin*25 + machine.hundredCoin*100;
        System.out.println("\nTotal cash: " + totalCash + " cents");

        productsMenu();
    }

    void productsMenu(){
        Scanner scn = new Scanner(System.in);

        System.out.println("Please select a product (enter number):");
        System.out.println("1. Coffee $2.25");
        System.out.println("2. Tea $3.50");
        System.out.print("Input: ");

        int opt = scn.nextInt();

        int change = 0;

        switch(opt){
            case 1:
                change = machine.pushCoffeeButton();
                if(change!=-1)
                    System.out.println("Your change is " + change + " cents");
                break;
            case 2:
                change = machine.pushTeaButton();
                if(change!=-1)
                    System.out.println("Your change is " + change + " cents");
                break;
            default:
                System.out.println("Invalid input");
                productsMenu();
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OptionsMenu optionsMenu = new OptionsMenu();
        optionsMenu.mainMenu();
        System.out.println("Goodbye.");
    }
}