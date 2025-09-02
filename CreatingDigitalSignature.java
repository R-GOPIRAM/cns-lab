import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class CreatingDigitalSignature {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some text: ");
        String msg = sc.nextLine();

        // Step 1 & 2: Create and initialize KeyPairGenerator
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);

        // Step 3: Generate KeyPair
        KeyPair pair = keyPairGen.generateKeyPair();

        // Step 4: Get PrivateKey
        PrivateKey privKey = pair.getPrivate();

        // Step 5: Create Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        // Step 6: Initialize Signature with private key
        sign.initSign(privKey);

        // Step 7: Add data to be signed
        byte[] bytes = msg.getBytes();
        sign.update(bytes);

        // Step 8: Generate signature
        byte[] signature = sign.sign();

        System.out.println("Digital signature for given text: ");
        System.out.println(new String(signature, "UTF8"));
    }
}
