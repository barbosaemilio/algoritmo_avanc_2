import java.util.Scanner;

public class TrocoMoedasFicticias {

    // Moedas fictícias, da maior para a menor
    private static final int[] MOEDAS = {5, 2, 1};

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Digite a quantia do troco: ");
        int quantia = teclado.nextInt();

        if (quantia < 0) {
            System.out.println("A quantia não pode ser negativa.");
            return;
        }

        int total = 0;
        int restante = quantia;

        System.out.println("Troco de " + quantia + ":");

        for (int moeda : MOEDAS) {
            int qtd = restante / moeda;   // quantas moedas desse valor cabem
            restante = restante % moeda;  // o que sobra para as próximas
            if (qtd > 0) {
                System.out.println(qtd + " moeda(s) de " + moeda);
                total += qtd;
            }
        }

        System.out.println("Total de moedas: " + total);
        teclado.close();
    }
}