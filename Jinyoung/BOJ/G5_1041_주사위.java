import java.io.*;
import java.util.*;

/**
* @since 2022. 1. 25.
* @author Jin
* @see https://www.acmicpc.net/problem/1041
* @mem 11596kb
* @time 80ms
* @caution G5
*/

public class G5_1041_주사위 {
	
	static int[] dice, diceTwo, diceThree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		long N = Long.parseLong(br.readLine());

		dice = new int[6];
		diceTwo = new int[12];
		diceThree = new int[8];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < dice.length; i++) 
			dice[i] = Integer.parseInt(st.nextToken());
		
		long answer = 0;
		if(N==1) {
			Arrays.sort(dice);
			for(int i = 0; i<5; i++)
				answer += dice[i];
			System.out.println(answer);
			return;
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < dice.length; i++) 
			min = Math.min(min, dice[i]);
		answer += ((N-2)*(5*N-6)*min);
		
		two();
		three();

		answer += diceTwo[0]*4*(2*N-3);
		answer += diceThree[0]*4;
		System.out.println(answer);
	}
	
	static void two() {
		int idx = 0;
		for (int i = 1; i < dice.length - 1; i++) 
			diceTwo[idx++] = dice[0] + dice[i];
		
		for (int i = 2; i < dice.length; i++) {
			if(i==4) continue;
			diceTwo[idx++] = dice[1] + dice[i];
		}
		
		for (int i = 4; i < dice.length; i++) {
			diceTwo[idx++] = dice[2] + dice[i];
			diceTwo[idx++] = dice[3] + dice[i];
		}
		
		diceTwo[idx++] = dice[4] + dice[5];
		Arrays.sort(diceTwo);
	}
	
	static void three() {
		// a 0 / b 1 / c 2 / d 3 / e 4 / f 5
		int idx = 0;
		diceThree[idx++] = dice[0] + dice[3] + dice[4]; // ade
		diceThree[idx++] = dice[0] + dice[1] + dice[3]; // adb
		diceThree[idx++] = dice[0] + dice[2] + dice[4]; // aec
		diceThree[idx++] = dice[0] + dice[1] + dice[2]; // abc
		diceThree[idx++] = dice[5] + dice[3] + dice[4]; // fde
		diceThree[idx++] = dice[5] + dice[1] + dice[3]; // fdb
		diceThree[idx++] = dice[5] + dice[2] + dice[4]; // fec
		diceThree[idx++] = dice[5] + dice[1] + dice[2]; // fbc
		Arrays.sort(diceThree);
	}
}
