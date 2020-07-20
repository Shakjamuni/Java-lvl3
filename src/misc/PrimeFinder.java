package misc;

public class PrimeFinder implements Runnable {
    Thread go;
    StringBuffer primes = new StringBuffer();
    int time = 0;

    public PrimeFinder() {
        start();
        while (primes != null) {
            System.out.println(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exc) {

            }
            time++;
        }
    }

    public void start() {
        if (go == null) {
            go = new Thread(this);
            go.start();
        }
    }

    @Override
    public void run() {
        int quantity = 1_000_000;
        int numPrimes = 0;

        int candidate = 2;
        primes.append("\nFirst ").append(quantity).append(" " + "simple numbers: \n \n");
        while (numPrimes < quantity) {
            if (isPrime(candidate)) {
                primes.append(candidate).append(" '");
                numPrimes++;
            }
            candidate++;
        }
        System.out.println(primes);
        primes = null;
        System.out.println("\n Used "+time+" seconds");
    }
    public static boolean isPrime(int checkNumber) {
        double root = Math.sqrt(checkNumber);
        for(int i = 2; i <= root; i++) {
            if(checkNumber % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PrimeFinder();
    }
}
