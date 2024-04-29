public class Semaforo {
    private int permits;

    public Semaforo(int permits) {
        this.permits = permits;
    }

    //diminui o número de ingressos disponíveis, diminuindo o número de threads que podem realizar o acesso.
    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) { //espera caso não tenham recursos disponíveis
            wait();
        }
        permits--;
    }

    //aumenta o número de ingressos depois que os recursos não estiverem mais em uso
    public synchronized void release() {
        permits++;
        notify();
    }

    public int getPermits() {
        return permits;
    }
}
