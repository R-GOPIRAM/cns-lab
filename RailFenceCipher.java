public class RailFenceCipher {
    
    // Method to encode a message using Rail Fence Cipher
    public static String encode(String msg, int depth) {
        char[][] rail = new char[depth][msg.length()];
        StringBuilder enc = new StringBuilder();
        
        // Fill the rail in zigzag pattern
        int row = 0;
        boolean goingDown = false;
        
        // Fill the rail with characters
        for (int i = 0; i < msg.length(); i++) {
            rail[row][i] = msg.charAt(i);
            if (row == 0 || row == depth - 1) {
                goingDown = !goingDown; // Change direction
            }
            row += goingDown ? 1 : -1; // Move up or down
        }
        
        // Read the rail matrix to create the encoded message
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < msg.length(); j++) {
                if (rail[i][j] != 0) {
                    enc.append(rail[i][j]);
                }
            }
        }
        
        return enc.toString();
    }

    // Method to decode a message using Rail Fence Cipher
    public static String decode(String encMsg, int depth) {
        char[][] rail = new char[depth][encMsg.length()];
        StringBuilder dec = new StringBuilder();
        
        // Initialize the rail with '*' to mark the positions
        int row = 0;
        boolean goingDown = false;
        
        // Mark the positions of characters in the zigzag pattern
        for (int i = 0; i < encMsg.length(); i++) {
            rail[row][i] = '*';
            if (row == 0 || row == depth - 1) {
                goingDown = !goingDown;
            }
            row += goingDown ? 1 : -1;
        }

        // Fill the rail with the encrypted message characters
        int k = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < encMsg.length(); j++) {
                if (rail[i][j] == '*' && k < encMsg.length()) {
                    rail[i][j] = encMsg.charAt(k++);
                }
            }
        }

        // Read the rail matrix to create the decoded message
        row = 0;
        goingDown = false;
        for (int i = 0; i < encMsg.length(); i++) {
            dec.append(rail[row][i]);
            if (row == 0 || row == depth - 1) {
                goingDown = !goingDown;
            }
            row += goingDown ? 1 : -1;
        }
        
        return dec.toString();
    }

    public static void main(String[] args) {
        String msg = "HELLO";
        int depth = 3;

        // Encrypt and decrypt the message
        String enc = encode(msg, depth);
        String dec = decode(enc, depth);

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}