public class VigenereCipher {
    // Encode the text using the Vigenère Cipher
    static String encode(String text, String key) {
        return process(text, key, true);
    }

    // Decode the text using the Vigenère Cipher
    static String decode(String text, String key) {
        return process(text, key, false);
    }

    // Common method for encoding and decoding
    static String process(String text, String key, boolean isEncoding) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyLength = key.length();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') { // Preserve non-alphabet characters
                result.append(c);
                continue;
            }
            int shift = (isEncoding ? 1 : -1) * (key.charAt(j) - 'A');
            result.append((char) ((c - 'A' + shift + 26) % 26 + 'A'));
            j = (j + 1) % keyLength; // Cycle through the key
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String key = "VIGENERECIPHER";
        String msg = "Security Laboratory";

        System.out.println("Simulating Vigenère Cipher");
        System.out.println("--------------------------");
        System.out.println("Input Message : " + msg);

        // Remove spaces and encode the message
        String msgNoSpaces = msg.replaceAll("\\s", "").toUpperCase();
        String encrypted = encode(msgNoSpaces, key);
        System.out.println("Encrypted Message : " + encrypted);

        // Decode the encrypted message
        String decrypted = decode(encrypted, key);
        System.out.println("Decrypted Message : " + decrypted);
    }
}