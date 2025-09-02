import java.awt.Point;

public class PlayfairCipher {
    private static char[][] cipherTable;
    private static Point[] characterPositions;

    // Prepares the text by removing unwanted characters and merging 'J' into 'I'
    private static String cleanText(String input) {
        input = input.toUpperCase().replaceAll("[^A-Z]", ""); // Remove non-alphabetic characters
        return input.replace("J", "I"); // Merge 'J' into 'I'
    }

    // Creates the Playfair cipher table
    private static void generateCipherTable(String key) {
        cipherTable = new char[5][5];
        characterPositions = new Point[26];
        String uniqueCharacters = cleanText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        int uniqueIndex = 0;
        for (int i = 0; i < uniqueCharacters.length(); i++) {
            char currentChar = uniqueCharacters.charAt(i);
            if (characterPositions[currentChar - 'A'] == null) { // Add unique characters only
                cipherTable[uniqueIndex / 5][uniqueIndex % 5] = currentChar;
                characterPositions[currentChar - 'A'] = new Point(uniqueIndex % 5, uniqueIndex / 5);
                uniqueIndex++;
            }
        }
    }

    // Encodes or decodes the text based on the direction (1 for encode, -1 for decode)
    private static String processText(StringBuilder text, int direction) {
        for (int i = 0; i < text.length(); i += 2) {
            char firstChar = text.charAt(i);
            char secondChar = text.charAt(i + 1);

            Point firstPos = characterPositions[firstChar - 'A'];
            Point secondPos = characterPositions[secondChar - 'A'];

            if (firstPos.y == secondPos.y) { // Same row
                firstChar = cipherTable[firstPos.y][(firstPos.x + direction + 5) % 5];
                secondChar = cipherTable[secondPos.y][(secondPos.x + direction + 5) % 5];
            } else if (firstPos.x == secondPos.x) { // Same column
                firstChar = cipherTable[(firstPos.y + direction + 5) % 5][firstPos.x];
                secondChar = cipherTable[(secondPos.y + direction + 5) % 5][secondPos.x];
            } else { // Rectangle swap
                firstChar = cipherTable[firstPos.y][secondPos.x];
                secondChar = cipherTable[secondPos.y][firstPos.x];
            }

            text.setCharAt(i, firstChar);
            text.setCharAt(i + 1, secondChar);
        }
        return text.toString();
    }

    // Encodes the input text
    private static String encode(String input) {
        StringBuilder text = new StringBuilder(input);
        for (int i = 0; i < text.length(); i += 2) {
            if (i == text.length() - 1) { // If odd length, add padding
                text.append('X');
            } else if (text.charAt(i) == text.charAt(i + 1)) { // If same characters, add padding
                text.insert(i + 1, 'X');
            }
        }
        return processText(text, 1);
    }

    // Decodes the input text
    private static String decode(String input) {
        return processText(new StringBuilder(input), -1);
    }

    public static void main(String[] args) {
        String key = "CSE";
        String plaintext = "SecurityLab";

        // Generate cipher table
        generateCipherTable(key);

        // Prepare plaintext
        String preparedText = cleanText(plaintext);

        // Encrypt and decrypt
        String encryptedText = encode(preparedText);
        String decryptedText = decode(encryptedText);

        // Display results
        System.out.println("Playfair Cipher Simulation");
        System.out.println("--------------------------");
        System.out.println("Input Message: " + plaintext);
        System.out.println("Encrypted Message: " + encryptedText);
        System.out.println("Decrypted Message: " + decryptedText);
    }
}