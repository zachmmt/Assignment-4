/**
 * Authors: Ryan Hansen & Zach Miller
 * Date: 4/24/18
 * Overview:    This project tests a class, DynamicChangeMaker.java
 *              that solves the least change problem through
 *              dynamic programming. DynamicChangeMaker.java's only
 *              method is makeChange(), which takes as parameters
 *              an integer array of coin denominations and an integer
 *              amount of change due. It returns a String that is
 *              a list of the coins to be given back.
 */

package src;

public class DynamicChangeMaker {
    
    public DynamicChangeMaker(){
        
    }
    
    public String makeChange(int [] denoms, int changeDue){
        
        //Handle test 4: emptyDenom()
        if(denoms.length == 0){ 
            throw new IllegalArgumentException("You can't use an empty string");
        }
        
        //Handle test 3: denomsOutOfOrder()
        for(int i : denoms){
            if(i+1 < denoms.length){
                if(denoms[i] > denoms[i+1]){
                    return "Coin denominations must be in increasing order. For example: 1, 5, 10; not 1, 10, 5.";
                }
            }
        }
        
        return "";
    }
}
