package project1;

import java.util.ArrayList;

public class WordBreak {

	ArrayList<String> list = new ArrayList<>();
	String word = new String();
	String ans_word = new String("");

	public WordBreak(String x, ArrayList<String> a) {
		this.word = x;
		this.list = a;
	}

	public int[][] table() {
		int n = word.length();
		int[][] array = new int[n][n];

		for(int i = 1; i <= n; i++) {
			for(int j = 0; j < n - i + 1; j++) {
				int o = j + i - 1;
				String exp = word.substring(j, o + 1);

				if(checkDictionary(exp, list)) {
					array[j][o] = 1;
					continue;
				}

				for(int k = j; k < o; k++){
					if(array[j][k] == 1 && array[k + 1][o] == 1){
						array[j][o] = 1;
						continue;
					}
				}
			}
		}

		return array;
	}

	public void recWordBreak(ArrayList<String> dictionary, String word1, int[][] arr, String ans, int i, int j) {
		if(i > j) {
			ans_word = ans + "\n";
		}
		
		for(int k = i; k <= j; k++) {
			if(arr[i][k] == 1) {
				String substring = word.substring(i, k + 1);
				
				if(dictionary.contains(substring)) {
					recWordBreak(dictionary, word1, arr, ans + " " + substring, k + 1, j);
				}
			}
		}
	}

	private static boolean checkDictionary(String prefix,ArrayList<?> a) {
		if(a.contains(prefix))
			return true;

		return false;
	}

}