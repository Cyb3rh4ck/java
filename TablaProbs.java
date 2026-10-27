import java.util.*;

/** Imprime la tabla aprendida con porcentajes (para la escena 3 del video). */
public class TablaProbs {
    public static void main(String[] args) {
        List<String> mensajes = List.of(
            "we should probably ship it",
            "we should probably wait",
            "i think this works"
        );
        Map<String, List<String>> tabla = new LinkedHashMap<>();
        for (String msg : mensajes) {
            String[] w = msg.split(" ");
            for (int i = 0; i < w.length - 1; i++) {
                tabla.computeIfAbsent(w[i], k -> new ArrayList<>())
                     .add(w[i + 1]);
            }
        }
        tabla.forEach((palabra, sig) -> {
            Map<String, Long> cuenta = new LinkedHashMap<>();
            sig.forEach(s -> cuenta.merge(s, 1L, Long::sum));
            StringBuilder sb = new StringBuilder(palabra + " ->");
            cuenta.forEach((s, n) -> sb.append(String.format(" %s %d%%", s, n * 100 / sig.size())));
            System.out.println(sb);
        });
    }
}
