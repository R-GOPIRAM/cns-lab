
class CaesarCipher {
    public static String encode(String enc, int offset) {
        offset = offset % 26 + 26; // Normalize offset
        StringBuilder encoded = new StringBuilder();
        for (char i : enc.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isUpperCase(i)) {
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26));                    
                } else {
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26));
                }
            } else {
                encoded.append(i); // Non-alphabetic characters are not modified
            }
        }
        return encoded.toString();
    }

    public static String decode(String enc, int offset) {
        return encode(enc, 26 - offset); // Decoding is the inverse of encoding
    }

    public static void main(String[] args) throws java.lang.Exception {
        String msg = "AnnaUniversity";
        System.out.println("Simulating Caesar Cipher");
        System.out.println("------------------------");
        System.out.println("Input: " + msg);
        System.out.printf("Encrypted Message: ");
        System.out.println(CaesarCipher.encode(msg, 3));
        System.out.printf("Decrypted Message: ");
        System.out.println(CaesarCipher.decode(CaesarCipher.encode(msg, 3), 3));
    }
}