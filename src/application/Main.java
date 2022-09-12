package application;

import java.util.Scanner;

import game.Match;
import game.Words;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Match match = new Match("words.txt");
		
		System.out.println(match);
		
		sc.close();
	}

}
