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

import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicChangeMakerTest {
    
    public DynamicChangeMakerTest() {
    }

    @Test
    public void greedyFailure() {
        DynamicChangeMaker instance = new DynamicChangeMaker();
        
        int [] denoms = {1, 5, 10, 21, 25};
        int changeDue = 63;
        
        String result = instance.makeChange(denoms, changeDue);
        String expectedResult = "21, 21, 21";
        
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void standardInput() {
        DynamicChangeMaker instance = new DynamicChangeMaker();
        
        int [] denoms = {1, 5, 10, 25};
        int changeDue = 42;
        
        String result = instance.makeChange(denoms, changeDue);
        String expectedResult = "25, 10, 5, 1, 1";
        
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void denomsOutOfOrder() {
        DynamicChangeMaker instance = new DynamicChangeMaker();
        
        int [] denoms = {};
        int changeDue = 0;
        
        String result = instance.makeChange(denoms, changeDue);
        String expectedResult = "Coin denominations must be in increasing order. For example: 1, 5, 10; not 1, 10, 5.";
        
        assertEquals(expectedResult, result);
    }
    
    @Test(expected = IllegalArgumentException.class)  public void emptyDenom() throws IllegalArgumentException{
        DynamicChangeMaker instance = new DynamicChangeMaker();
        
        int [] denom = {};
        
        instance.makeChange(denom, 42);
        
    }
    
}
