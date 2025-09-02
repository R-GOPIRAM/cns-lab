class DiffieHellman {
    public static void main(String args[]) {
        int p = 23; // publicly known (prime number)
        int g = 5;  // publicly known (primitive root)

        int x = 4;  // Alice's secret
        int y = 3;  // Bob's secret

        // Alice sends
        double aliceSends = (Math.pow(g, x)) % p;

        // Bob sends
        double bobSends = (Math.pow(g, y)) % p;

        // Bob computes shared key
        double bobComputes = (Math.pow(aliceSends, y)) % p;

        // Alice computes shared key
        double aliceComputes = (Math.pow(bobSends, x)) % p;

        // Direct calculation (for verification)
        double sharedSecret = (Math.pow(g, (x * y))) % p;

        System.out.println("Simulation of Diffie-Hellman Key Exchange Algorithm\n--");
        System.out.println("Alice Sends     : " + aliceSends);
        System.out.println("Bob Computes    : " + bobComputes);
        System.out.println("Bob Sends       : " + bobSends);
        System.out.println("Alice Computes  : " + aliceComputes);
        System.out.println("Shared Secret   : " + sharedSecret);

        // Validation
        if ((aliceComputes == sharedSecret) && (aliceComputes == bobComputes))
            System.out.println("Success: Shared Secrets Match! " + sharedSecret);
        else
            System.out.println("Error: Shared Secrets do not Match");
    }
}
