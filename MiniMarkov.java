import java.util.*;

public class MiniMarkov {
    public static void main(String[] args) {
        List<String> mensajes = List.of(
            "we should probably ship it",
            "we should probably wait",
            "i think this works"
        );

        // Entrenar: contar qué palabra sigue a cuál
        Map<String, List<String>> tabla = new HashMap<>();
        for (String msg : mensajes) {
            String[] w = msg.split(" ");
            for (int i = 0; i < w.length - 1; i++) {
                tabla.computeIfAbsent(w[i], k -> new ArrayList<>())
                     .add(w[i + 1]);
            }
        }

        // Generar: elegir la siguiente palabra al azar según los conteos
        Random rand = new Random();
        String actual = "we";
        while (actual != null) {
            System.out.print(actual + " ");
            List<String> op = tabla.get(actual);
            actual = op == null ? null : op.get(rand.nextInt(op.size()));
        }
        System.out.println();
    }
}
