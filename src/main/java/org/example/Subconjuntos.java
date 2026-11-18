import java.util.ArrayList;
import java.util.List;

public class Subconjuntos {

    public static <T> List<List<T>> subconjuntos(List<T> s, int n) {
        List<List<T>> resultado = new ArrayList<>();
        if (n < 0 || n > s.size()) {
            return resultado;
        }
        gerar(s, n, 0, new ArrayList<>(), resultado);
        return resultado;
    }

    private static <T> void gerar(List<T> s, int n, int inicio,
                                  List<T> atual, List<List<T>> resultado) {
        // Caso base: subconjunto completo
        if (atual.size() == n) {
            resultado.add(new ArrayList<>(atual)); // copia, pois "atual" será modificado
            return;
        }

        // Poda: não há elementos restantes suficientes para completar n
        int faltam = n - atual.size();
        for (int i = inicio; i <= s.size() - faltam; i++) {
            atual.add(s.get(i));                  // escolhe
            gerar(s, n, i + 1, atual, resultado); // explora
            atual.remove(atual.size() - 1);       // desfaz a escolha
        }
    }

    public static void main(String[] args) {
        System.out.println(subconjuntos(List.of(1, 2, 3), 2));
        // [[1, 2], [1, 3], [2, 3]]

        System.out.println(subconjuntos(List.of(1, 2, 3, 4), 1));
        // [[1], [2], [3], [4]]
    }
}
