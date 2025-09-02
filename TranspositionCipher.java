import java.util.Scanner;

class TranspositionCipher {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext from the user
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();
        scanner.close();

        // Remove spaces from the plaintext
        String cleanedText = plaintext.replaceAll("\\s", "");
        System.out.println("After removing spaces: " + cleanedText);

        // Set the number of columns for the matrix (can be adjusted)
        int numColumns = 4;
        int textLength = cleanedText.length();
        int numRows = (int) Math.ceil((double) textLength / numColumns); // Calculate number of rows

        // Create a matrix to hold the characters in row-major order
        char[][] matrix = new char[numRows][numColumns];
        int currentIndex = 0; // Pointer to current character in the cleaned text

        // Fill the matrix with characters from the cleaned text
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                if (currentIndex < textLength) {
                    matrix[row][col] = cleanedText.charAt(currentIndex++);
                } else {
                    matrix[row][col] = '#'; // Padding character to fill the matrix
                }
            }
        }

        // Create a new matrix to hold the transposed (encrypted) message
        char[][] transposedMatrix = new char[numColumns][numRows];

        // Transpose the original matrix to rearrange the characters column-wise
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                transposedMatrix[col][row] = matrix[row][col];
            }
        }

        // Display the transposed (encrypted) message
        System.out.println("Encrypted message:");
        for (int col = 0; col < numColumns; col++) {
            for (int row = 0; row < numRows; row++) {
                System.out.print(transposedMatrix[col][row]);
            }
        }
        System.out.println();
    }
}