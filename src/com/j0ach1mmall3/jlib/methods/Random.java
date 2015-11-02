package com.j0ach1mmall3.jlib.methods;

import java.util.Arrays;
import java.util.List;

public class Random {
	private static final java.util.Random RANDOM = new java.util.Random();
	@SuppressWarnings("SameParameterValue")
    public static int getInt(int min, int max){
		return RANDOM.nextInt(max- min) + min;
	}
	
	public static int getInt(int max){
		return getInt(0, max);
	}
	
	public static int getInt(){
		return RANDOM.nextInt();
	}
	
	public static boolean getBoolean(){
		return RANDOM.nextBoolean();
	}
	
	public static double getDouble(){
		return getDouble(false);
	}

    @SuppressWarnings("SameParameterValue")
    public static double getDouble(boolean negative) {
        if(negative && getBoolean()) return -1D * RANDOM.nextDouble();
        return RANDOM.nextDouble();
    }

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
