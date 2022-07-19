/**
 * Full name: Kevin Le
 * Student ID: 2406054
 * Chapman email: kevle@chapman.edu
 * Course number and section: CPSC 231-04
 * Mastery Project 1: Part 1 - Harmonics
*/

/*
Description: A program to compute harmonic numbers using static methods in the Harmonic class.
             One uses iteration and one uses recursion.
*/

/**
 * The Harmonic class claculates Harmonics using Iteration and Recursion methods.
 * @author Kevin Le
 * @version 1.0
 * @see Harmonic
*/

public class Harmonic {
    /**
     * Calculate Harmonics using Iteration, returns a double
     * @param n
     * @return harmonicSum double representation of Harmonic number
    */
    public static double calcIterativeHarmonic(int n) {
        double harmonicSum = 0;
        // adds 1/i until reaching n
        for (int i = 1; i <= n; ++i) {
            harmonicSum += (1/(double)i);
        }
        return harmonicSum;
    }
    
    /**
     * Calculate Harmonics using Recursion, returns a double
     * @param n
     * @return 1 if n == 1 and (1/(double)n) + calcRecursiveHarmonic(n-1) otherwise
    */
    public static double calcRecursiveHarmonic(int n) {
        if (n == 1) {
            return 1;
        }
        // returns 1/n, calls this method again with (n-1), and adds in descending order until n == 1
        return (1/(double)n) + calcRecursiveHarmonic(n-1);
    }

    public static void main(String[] args) {
        System.out.println();

        // Prints out the first 20 harmonic numbers using Iteration
        System.out.println("First 20 Harmonic Numbers using Iteration:");
        for (int i = 1; i < 21; ++i) {
            System.out.println(calcIterativeHarmonic(i));
        }

        System.out.println();

        // Prints out the first 20 harmonic numbers using Recursion
        System.out.println("First 20 Harmonic Numbers using Recursion:");
        for (int j = 1; j < 21; ++j) {
            System.out.println(calcRecursiveHarmonic(j));
        }
        System.out.println();

    }
}
