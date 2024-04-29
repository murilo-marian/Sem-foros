import java.util.Random;

public class Filosofo implements Runnable {
    Random aleatorio = new Random();
    private final Semaforo leftFork;
    private final Semaforo rightFork;
    private final Semaforo mesa;

    public Filosofo(Semaforo leftFork, Semaforo rightFork, Semaforo mesa) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.mesa = mesa;
    }

    public void pensar() {
        try {
            System.out.println(Thread.currentThread().getName() + " está pensando.");
            Thread.sleep(aleatorio.nextInt(15000, 20000));
        } catch (InterruptedException e) {
            System.out.println("Pensou demais");
        }
    }

    public void comer() {
        try {
            System.out.println(Thread.currentThread().getName() + " está comendo.");
            Thread.sleep(aleatorio.nextInt(15000, 20000));
        } catch (InterruptedException e) {
            System.out.println("comeu demais");
        }
    }

    public void baterFome() throws InterruptedException {
        mesa.acquire();
        leftFork.acquire();
        System.out.println(Thread.currentThread().getName() + " pegou o garfo esquerdo.");
        rightFork.acquire();
        System.out.println(Thread.currentThread().getName() + " pegou o garfo direito e está comendo.");
        comer();
        mesa.release();
        leftFork.release();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo esquerdo.");
        rightFork.release();
        System.out.println(Thread.currentThread().getName() + " soltou o garfo direito e está pensando.");
    }

    public void run() {
        try {
            while (true) {
                int escolha = aleatorio.nextInt(2);
                if (escolha == 0) {
                    pensar();
                } else {
                    baterFome();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
