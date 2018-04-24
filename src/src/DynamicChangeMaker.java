/**
 * Authors: Ryan Hansen & Zach Miller
 * Date: 4/24/18
 * Overview:    This project tests a class, DynamicChangeMaker.java
 *              that solves the least change problem through
 *              dynamic programming. DynamicChangeMaker.java's most important
 *              method is makeChange(), which takes as parameters
 *              an integer array of coin denominations and an integer
 *              amount of change due. It returns an integer that is
 *              the input for another method, calculateCoins. This method
 *              returns a list of the coins by value to be given back.
 */

package src;

import java.util.ArrayList;
import java.util.Arrays;

public class DynamicChangeMaker {
    private ArrayList<Integer> coinNumArr;
    private ArrayList<Integer> coinBreakdown;
    private int [] savedDenoms;
    private int highestCoinNum;
    
    public DynamicChangeMaker(){
        coinBreakdown = new ArrayList();
        coinBreakdown.add(0);
        coinNumArr = new ArrayList();
        coinNumArr.add(0);
        highestCoinNum = 0;
    }
    
    public String makeChange(int [] denoms, int changeDue){
        //Handle test 3: denomsOutOfOrder()
        for(int i=0; i<denoms.length-1; i++){
            if(denoms[i] > denoms[i+1]){
                return "Coin denominations must be in increasing order. For example: 1, 5, 10; not 1, 10, 5.";
            }
        }
        
        //Handle test 4: emptyDenom()
        if(denoms.length == 0){ 
            throw new IllegalArgumentException("You can't use an empty string");
        }

        //reset coinNum if denominations changed
        if(!Arrays.equals(savedDenoms, denoms)){
            savedDenoms = denoms;
            coinNumArr = new ArrayList();
            coinNumArr.add(0);
            highestCoinNum = 0;
        }
        
        //Setup reduction array
        int reduce[] = new int [denoms.length];
        int newAmt;
        int denomIndex = 0;
        //Check if we already know the answer
        if(highestCoinNum >= changeDue){ //Already calculated and stored
            return this.calculateCoins(coinNumArr.get(changeDue), changeDue);
        }else{ //Time to calculate
            while(highestCoinNum < changeDue){          //Still not up to changeDue (don't have calculation)
//                System.out.println("highestCoinNum: " + highestCoinNum + "; changeDue: " + changeDue);
                for(int i=denoms.length-1; i>=0; i--){       //Test each denomination "back"
//                    System.out.println("    i is: " + i + "; denoms[i] is: " + denoms[i]);
                    int lookBack = highestCoinNum+1-denoms[i]; //Make sure not looking out of bounds
//                    System.out.println("    lookBack is: " + lookBack);
                    if(lookBack < 0){
                        reduce[i] = Integer.MAX_VALUE;
                    }else{ //calculate denom reduction, test if best later
                        reduce[i] = coinNumArr.get(highestCoinNum+1-denoms[i]) + 1;
                    }
                    
                }//end for loop, step up on best denomination reduction
                newAmt = Integer.MAX_VALUE;
                for(int i=0; i<denoms.length; i++){
                    if(reduce[i] < newAmt){
                        newAmt = reduce[i];
                        denomIndex = i;
                    }
                }
        
                coinBreakdown.add(highestCoinNum+1, denoms[denomIndex]);
                //Update array list and note new upper bound
                coinNumArr.add(highestCoinNum+1, newAmt);
                highestCoinNum++;
//                System.out.println("            c: " + highestCoinNum + "; C[c]: " + coinNumArr.get(highestCoinNum));
                
            }
            //System.out.println();
            //System.out.println();
            //this.printCArray();
            return this.calculateCoins(coinNumArr.get(changeDue), changeDue);
        }
    }
    
    private String calculateCoins(int amtCoins, int changeDue){
        String output = "";
        int[] coinarray = new int[amtCoins];
        int temp;
        int counter;
        
        //generate coin array
        for(int i = 0; i < amtCoins; i++){
            coinarray[i] = coinBreakdown.get(changeDue);
            changeDue = changeDue - coinBreakdown.get(changeDue);
        }
        
        //sort coin array
        for(int i = 0; i < amtCoins; i++){
            temp   = Integer.MAX_VALUE;
            counter = 0;
            for(int j = i; j < amtCoins; j++){
                if(coinarray[j] <= temp){
                    temp = coinarray[j];
                    counter=j;
                }
            }
            coinarray[counter] = coinarray[i];
            coinarray[i] = temp;
        }
        
        //generate output in correct format (Greatest to least)
        for(int i = amtCoins-1; i > -1; i--){
            output = output + coinarray[i];
            if(i > 0){
                output = output + ", ";
            }
        }
        
        return "" + output;
    }
    
    private void printCArray(){
        System.out.println("Printing C:");
            for(int i=0; i<=highestCoinNum; i++){
                System.out.println("c: " + i + "; C[c]: " + coinNumArr.get(i));
            }
    }
}
