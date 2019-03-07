package assignment1;

public class Message {
	
	public String message;
	public int lengthOfMessage;

	public Message (String m){
		message = m;
		lengthOfMessage = m.length();
		this.makeValid();
	}
	
	public Message (String m, boolean b){
		message = m;
		lengthOfMessage = m.length();
	}
	
	/**
	 * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
	 */
	public void makeValid(){
		//INSERT YOUR CODE HERE
		String lowerMessage = message.toLowerCase();
		String validMessage = "";
		
		for(int i=0 ; i<lowerMessage.length() ; i++ ) {
			
			char mvLetter = lowerMessage.charAt(i);
			
			if( Character.isLetter(mvLetter) ) 
			{
				validMessage += lowerMessage.charAt(i); //adds lower case letters to new valid string
			}
		}
		
		message = validMessage;
		lengthOfMessage = validMessage.length();
	}
	
	/**
	 * prints the string message
	 */
	public void print(){
		System.out.println(message);
	}
	
	/**
	 * tests if two Messages are equal
	 */
	public boolean equals(Message m){
		if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
			return true;
		}
		return false;
	}
	
	/**
	 * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
	 * @param key
	 */
	public void caesarCipher(int key){
		// INSERT YOUR CODE HERE
		
		String caesarCiphered = "";
		int shift = key%26;
		for (int i=0 ; i < message.length(); i++ ){
			char cLetter = message.charAt(i);
			char shifted = (char)(cLetter + shift);
			if (shifted > 'z'){
				caesarCiphered = caesarCiphered + (char)(shifted - 26);
			}
			else if (shifted < 'a'){
				caesarCiphered = caesarCiphered + (char)(shifted + 26);					
			}
			else caesarCiphered = caesarCiphered + shifted;
		}
		
		message = caesarCiphered;
	}
	
	public void caesarDecipher(int key){
		this.caesarCipher(- key);
	}
	
	
	/**
	 * caesarAnalysis breaks the Caesar cipher
	 * you will implement the following algorithm :
	 * - compute how often each letter appear in the message
	 * - compute a shift (key) such that the letter that happens the most was originally an 'e'
	 * - decipher the message using the key you have just computed
	 */
	public void caesarAnalysis(){
		// INSERT YOUR CODE HERE
		int freqLetter[] = new int[26];
		
		for(int i=0; i < message.length(); i++){
			char caLetter = message.charAt(i);
			int index = caLetter - 97;
			freqLetter[index] = freqLetter[index] + 1; 		
		}
		
		int indexEmc = 0;
		for(int x=0; x < 26; x++){
			if(freqLetter[x] > freqLetter[indexEmc]){
				indexEmc = x;
			}
		}
		
		int key = indexEmc - 4;
		caesarDecipher(key);
		
	}
	
	/**
	 * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
	 * @param key
	 */
	public void vigenereCipher (int[] key){
		// INSERT YOUR CODE HERE
		String vigenereCiphered = "";
		int numOfKeys = key.length;
	
		for (int i=0 ; i < message.length(); i++ ){
			char vLetter = message.charAt(i);
			int tempKey = key[i%numOfKeys];
			int shift = tempKey%26;
			char shifted = (char)(vLetter + shift);
			if (shifted > 'z'){
				vigenereCiphered = vigenereCiphered + (char)(shifted - 26);
			}
			else if (shifted < 'a'){
				vigenereCiphered = vigenereCiphered + (char)(shifted + 26);					
			}
			else vigenereCiphered = vigenereCiphered + shifted;
		}
		
		message = vigenereCiphered;
	}

	/**
	 * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
	 * @param key
	 */
	public void vigenereDecipher (int[] key){
		// INSERT YOUR CODE HERE
		int numOfKeys = key.length;
		int j=0;
		while(j<numOfKeys){
			key[j] = -1 * key[j];
			j++;
		}
		this.vigenereCipher(key);
	}
	
	/**
	 * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
	 * @param key
	 */
	public void transpositionCipher (int key){
		// INSERT YOUR CODE HERE
		String transpositionCiphered = "";
		int numOfColumns = key;
		int numOfRows;
		if(message.length()%numOfColumns != 0){
			numOfRows = 1 + message.length()/numOfColumns;
		}
		else numOfRows = message.length()/numOfColumns;
		
		int indexInString = 0;
		char[][] twoDimCharArray = new char[numOfColumns][numOfRows];
		int Asterisks = 0;
		
		
		for(int x=0; x < numOfRows; x++){
			for(int y=0; y < numOfColumns; y++){
				if (indexInString < message.length()){
					twoDimCharArray[y][x] = message.charAt(indexInString);
					indexInString++;
				}
				else{
					twoDimCharArray[y][x] = '*';
					Asterisks++;
				}
			}//
		}
		
		for(int i=0; i < numOfColumns; i++){
			for(int j=0; j < numOfRows; j++){
				transpositionCiphered += twoDimCharArray[i][j];
			}
		}
		
		lengthOfMessage = message.length() + Asterisks;
		message = transpositionCiphered;
	}
	
	/**
	 * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
	 * @param key
	 */
	public void transpositionDecipher (int key){
		// INSERT YOUR CODE HERE
		String transpositionDeciphered = "";
		String transpoDeciphWAsterisks = "";
		int numOfColumns = key;
		int numOfRows;
		if(message.length()%numOfColumns != 0){
			numOfRows = 1 + message.length()/numOfColumns;
		}
		else numOfRows = message.length()/numOfColumns;
		
		int indexInString = 0;
		char[][] twoDimCharArray = new char[numOfColumns][numOfRows];
		int Asterisks = 0;
		
		for(int y=0; y < numOfColumns; y++){
			for(int x=0; x < numOfRows; x++){
				if (indexInString < message.length()){
					twoDimCharArray[y][x] = message.charAt(indexInString);
					indexInString++;
				}
				else{
					twoDimCharArray[y][x] = '*';
					Asterisks++;
				}
			}
		}
		
		
		for(int j=0; j < numOfRows; j++){
			for(int i=0; i < numOfColumns; i++){
				transpoDeciphWAsterisks += twoDimCharArray[i][j];
			}
		}
		
		for(int a=0; a < message.length() + Asterisks; a++){
			char tLetter = transpoDeciphWAsterisks.charAt(a);
			if(Character.isLetter(tLetter)){
				transpositionDeciphered += tLetter;
			}
		}
		
		lengthOfMessage = transpositionDeciphered.length();
		message = transpositionDeciphered;	
	}
	
}
