package com.j0ach1mmall3.jlib.methods;

import java.util.Arrays;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Random {
    private static final java.util.Random RANDOM = new java.util.Random();

    /**
     * Generates a random int between min and max
     * @param min The minimum value
     * @param max The maximum value
     * @return The random int
     */
    public static int getInt(int min, int max){
		return RANDOM.nextInt(max- min) + min;
	}

    /**
     * Generates a random int between 0 and max
     * @param max The maximum value
     * @return The random int
     */
    public static int getInt(int max){
		return getInt(0, max);
	}

    /**
     * Generates a random int
     * @return The random int
     */
	public static int getInt(){
		return RANDOM.nextInt();
	}

    /**
     * Generates a random boolean
     * @return The random boolean
     */
    public static boolean getBoolean(){
		return RANDOM.nextBoolean();
	}

    /**
     * Generates a random double, without negative doubles
     * @return The random double
     */
	public static double getDouble(){
		return getDouble(false);
	}

    /**
     * Generates a random double
     * @param negative Should negative doubles be allowed?
     * @return The random double
     */
    public static double getDouble(boolean negative) {
        if(negative && getBoolean()) return -1D * RANDOM.nextDouble();
        return RANDOM.nextDouble();
    }

    /**
     * Generates a random String
     * @param length The length of the String
     * @param capital Should capital letters be allowed?
     * @param number Should numbers be allowed?
     * @return The random String
     */
    public static String getString(int length, boolean capital, boolean number) {
        List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
        if(capital) chars.addAll(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
        if(number) chars.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.get(getInt(chars.size())));
        }
        return sb.toString();
    }
}
