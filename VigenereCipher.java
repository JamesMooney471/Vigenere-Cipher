
package vigenerecipher;

import java.util.Scanner;

public class VigenereCipher {
    
    /*
     *caesarCipherOne
     *Shift one character by a certain amount
     *
     *@param ch Character to shift by the cipher index
     *@param cipherIndex The shift of the Caesar cipher
     *
     *@return ch character shifted by cipherIndex
     */
    
    public static char caesarCipherOne(char ch, int cipherIndex) {
        int alphabetMagnitude, chCipherAscii;
        char cipherCh;
        cipherIndex = realMod(cipherIndex, 26);
        int chAscii = (int) ch;
        if (chAscii >= 97) {
            alphabetMagnitude = 122;
        } else {
            alphabetMagnitude = 90;
        }
        if (chAscii + cipherIndex > alphabetMagnitude) {
            chCipherAscii = (chAscii - 26) + cipherIndex;
        } else {
            chCipherAscii = chAscii + cipherIndex;
        }
        cipherCh = (char) chCipherAscii;

        return cipherCh;
    }
    
    /*
     *realMod
     *Correct Java's implementation of modulo
     *
     *@param dividend dividend of the modulus equation
     *@param divisor divisor of the modulus equation
     *
     *@return modulo of the division equation, with negative dividends corrected
     */
    
    public static int realMod(int dividend, int divisor) {
        if (dividend % divisor < 0) {
            return divisor + (dividend % divisor);
        } else {
            return dividend % divisor;
        }
    }
    
    /*
     *vigenereCipherOne
     *Using a String key, encrypt a character with the key given the index of the character
     *
     *@param plainChar character to encrypt
     *@param cipherKey cipher key to reference
     *@param cipherIndex index of the character in the String
     *
     *@return encrypted character
     */
    
    public static char vigenereCipherOne(char plainChar, String cipherKey, int cipherIndex) {
        int trueCipherIndex, cipherKeyAscii, cipherKeyMagnitude;
        cipherKeyAscii = (int) cipherKey.charAt(cipherIndex % cipherKey.length());
        if (cipherKeyAscii >= 97) {
            cipherKeyMagnitude = 97;
        } else {
            cipherKeyMagnitude = 65;
        }
        trueCipherIndex = ((int) cipherKey.charAt(cipherIndex % cipherKey.length())) - cipherKeyMagnitude;
        char plainCharCipher = caesarCipherOne(plainChar, trueCipherIndex);
        return plainCharCipher;
    }
    
    /*
     *vigenereCipherString
     *Using a String key, encrypt a whole message with the key
     *
     *@param plainText plain text to be encrypted
     *@param cipherKey cipher key to reference for encryption
     *
     *@return full String encrypted using the vigenere cipher
     */
    
    public static String vigenereCipherString(String plainText, String cipherKey) {
        char[] cipherString = new char[plainText.length()];
        for (int i = 0; i < plainText.length (); i++) {
            cipherString[i] = vigenereCipherOne(plainText.charAt(i), cipherKey, i);
        }
        String str = new String(cipherString);
        return  str;
    }
    
    /*
     *vigenereCipherOneDecrypt
     *Using a String key, decrypt a character with the key given the index of the character
     *
     *@param plainChar character to decrypt
     *@param cipherKey cipher key to reference
     *@param cipherIndex index of the character in the String
     *
     *@return encrypted character
     */
    
    public static char vigenereCipherOneDecrypt(char plainChar, String cipherKey, int cipherIndex) {
        int trueCipherIndex, cipherKeyAscii, cipherKeyMagnitude;
        cipherKeyAscii = (int) cipherKey.charAt(cipherIndex % cipherKey.length());
        if (cipherKeyAscii >= 97) {
            cipherKeyMagnitude = 97;
        } else {
            cipherKeyMagnitude = 65;
        }
        trueCipherIndex = ((int) cipherKey.charAt(cipherIndex % cipherKey.length())) - cipherKeyMagnitude;
        char plainCharCipher = caesarCipherOne(plainChar, trueCipherIndex * -1);
        return plainCharCipher;
    }
    
    /*
     *vigenereCipherStringDecrypt
     *Using a String key, decrypt a whole message with the key
     *
     *@param plainText plain text to be decrypted
     *@param cipherKey cipher key to reference for decryption
     *
     *@return full String decrypted using the vigenere cipher
     */
    
    public static String vigenereCipherStringDecrypt(String plainText, String cipherKey) {
        char[] cipherString = new char[plainText.length()];
        for (int i = 0; i < plainText.length(); i++) {
            cipherString[i] = vigenereCipherOneDecrypt(plainText.charAt(i), cipherKey, i);
        }
        String str = new String(cipherString);
        return str;
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String cipherText;
        
        System.out.print("Would you like to decrypt or encrypt a message? (d/e): ");
        String userChoice = sc.nextLine();
        System.out.print("Enter plaintext: ");
        String plainText = sc.nextLine();
        System.out.print("Enter key: ");
        String cipherKey = sc.nextLine();
        
        if (userChoice.equals("d")) {
            cipherText = vigenereCipherStringDecrypt(plainText, cipherKey);
        } else {
            cipherText = vigenereCipherString(plainText, cipherKey);
        }
        
        System.out.println(cipherText);

        sc.close();
        
    }
    
}
