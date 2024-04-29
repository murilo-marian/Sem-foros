public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numFilosofos = 5;
        Semaforo[] forks = new Semaforo[numFilosofos];
        Filosofo[] Filosofos = new Filosofo[numFilosofos];

        Semaforo mesa = new Semaforo(numFilosofos - 1); //semaforo que impede todos os filósofos de quererem comer ao mesmo tempo

        for (int i = 0; i < numFilosofos; i++) {
            forks[i] = new Semaforo(1);
        }

        for (int i = 0; i < numFilosofos; i++) {
            System.out.println("Garfo " + (i+1) + " está " + forks[i].getPermits());
        }
        for (int i = 0; i < numFilosofos; i++) {
            Semaforo leftFork = forks[i];
            Semaforo rightFork = forks[(i + 1) % numFilosofos]; //pega o próximo garfo, quando chega no último filósofo o garfo da direita é o 0
            Filosofos[i] = new Filosofo(leftFork, rightFork, mesa);
            Thread FilosofoThread = new Thread(Filosofos[i], "Filosofo " + i);
            FilosofoThread.start();
        }
        while (true) {
            for (int i = 0; i < numFilosofos; i++) {
                System.out.println("Garfo " + (i+1) + " está " + forks[i].getPermits());
            }
            Thread.sleep(10001);
        }
    }
}
