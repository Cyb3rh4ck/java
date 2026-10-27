# MiniMarkov — una mini-IA en Java

Código **ilustrativo** del video *"Crea una mini-IA en Java (y entiende cómo piensa un LLM)"*, parte 1 de la serie **IA desde cero**, publicado en el Instagram de [codigojava.oficial](https://www.instagram.com/codigojava.oficial/).

No es una librería ni código de producción: es un ejemplo mínimo y didáctico para entender cómo se predice la siguiente palabra.

## Qué hace

Un **modelo de Markov de orden 1** que aprende de tres mensajes de ejemplo y genera texto:

1. **Entrenar = contar.** Por cada palabra guarda qué palabras vinieron después de ella.
2. **Generar = muestrear.** Parte de una palabra (`we`) y elige la siguiente al azar según lo que aprendió, hasta que no hay siguiente.

Con los mensajes de ejemplo, `probably` va seguida de `ship` y de `wait` a partes iguales (50 % / 50 %), así que el programa imprime una de estas dos frases:

```
we should probably ship it
we should probably wait
```

## Archivos

| Archivo | Para qué sirve |
|---|---|
| `MiniMarkov.java` | El programa completo del video (entrena y genera). |
| `TablaProbs.java` | Imprime la tabla aprendida con porcentajes (la que se ve en la escena de entrenamiento). |

## Cómo ejecutarlo

Requiere Java 11 o superior (probado con Java 26).

```bash
javac -d out MiniMarkov.java TablaProbs.java
java -cp out MiniMarkov
java -cp out TablaProbs
```

Salida de `TablaProbs`:

```
we -> should 100%
should -> probably 100%
probably -> ship 50% wait 50%
ship -> it 100%
i -> think 100%
think -> this 100%
this -> works 100%
```

## Cómo funciona (resumen)

```java
// Entrenar: contar qué palabra sigue a cuál
Map<String, List<String>> tabla = new HashMap<>();
// ... por cada mensaje y cada par de palabras consecutivas:
tabla.computeIfAbsent(w[i], k -> new ArrayList<>()).add(w[i + 1]);

// Generar: elegir la siguiente palabra al azar según los conteos
actual = op == null ? null : op.get(rand.nextInt(op.size()));
```

Como cada siguiente palabra se guarda en una lista (con repeticiones), elegir un elemento al azar equivale a elegir según su frecuencia.

## Qué NO es

- **No es un LLM.** Esta tabla solo mira la **última palabra**. Un LLM lee toda la conversación, la procesa con una red neuronal de miles de millones de parámetros y trabaja con *tokens* (fragmentos de palabra).
- Lo que tienen en común es la idea de fondo: calcular probabilidades de lo que viene y **muestrear** una opción.
- Los mensajes de ejemplo son propios y diminutos; con tan pocos datos el modelo solo puede repetir lo que vio. Los porcentajes y ejemplos de tokens que aparecen en el video son ilustrativos.

## Siguiente

Parte 2 de la serie: **temperatura y top-k** aplicados a este mini-modelo.

## Crédito

Inspirado en el curso de predicción del siguiente token de Anthropic. El código y los ejemplos de este repositorio son propios; no se copió texto del curso.
