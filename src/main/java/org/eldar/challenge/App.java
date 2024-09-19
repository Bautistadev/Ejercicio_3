package org.eldar.challenge;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {




    // Método para agregar un valor al TreeMap que cuenta la cantidad de ocurrencias de cada gasto
    private static void agregar(TreeMap<Integer, Integer> map, int valor) {
        map.put(valor, map.getOrDefault(valor, 0) + 1);  // Incrementa el conteo del valor
    }




    // Método para eliminar un valor del TreeMap
    private static void eliminar(TreeMap<Integer, Integer> map, int valor) {
        int cuenta = map.get(valor); // Obtiene el conteo actual del valor
        if (cuenta == 1) {
            map.remove(valor);  // Si solo hay una ocurrencia, elimina el valor del TreeMap
        } else {
            map.put(valor, cuenta - 1);  // Decrementa el conteo si hay más de una ocurrencia
        }
    }




    // Método para encontrar la mediana en un TreeMap
    private static double obtenerMediana(TreeMap<Integer, Integer> map, int d) {
        int conteoAcumulado = 0;
        int mediana1 = 0, mediana2 = 0;

        // Iteramos sobre las entradas del TreeMap
        for (var entry : map.entrySet()) {
            conteoAcumulado += entry.getValue();

            if (mediana1 == 0 && conteoAcumulado >= d / 2) {
                mediana1 = entry.getKey();  // Primer valor medio
            }
            if (conteoAcumulado >= (d / 2) + 1) {
                mediana2 = entry.getKey();  // Segundo valor medio
                break;
            }
        }

        return (d % 2 == 0) ? (mediana1 + mediana2) / 2.0 : mediana2;
    }




    // Método principal que cuenta notificaciones usando Streams y TreeMap
    public static long contarNotificaciones(List<Integer> gastos, int d) {
        // Inicializamos un TreeMap vacío para almacenar los gastos
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Añadimos los primeros 'd' días de gastos al TreeMap usando Streams
        IntStream.range(0, d).forEach(i -> agregar(map, gastos.get(i)));

        // Usamos un IntStream que recorre desde el día 'd' hasta el final de los gastos
        return IntStream.range(d, gastos.size())
                // Filtramos los días en los que se envía una notificación de fraude
                .filter(i -> {
                    // Calcula la mediana usando los últimos 'd' días
                    double mediana = obtenerMediana(map, d);

                    // Verifica si el gasto del día actual es mayor o igual al doble de la mediana
                    boolean notificacion = gastos.get(i) >= 2 * mediana;

                    // Actualiza el TreeMap eliminando el gasto más antiguo y agregando el nuevo
                    eliminar(map, gastos.get(i - d));
                    agregar(map, gastos.get(i));

                    System.out.println(map.toString());

                    return notificacion;
                })
                // Contamos cuántos días cumplen con la condición del filtro
                .count();
    }





    public static void main( String[] args ) {
        Scanner scanner =  new Scanner(System.in);

        // Ejemplo de datos de entrada como una lista de Integer
        List<Integer> gastos = new ArrayList<>();

        System.out.println("Ingresa el plaso de dias anterires.");
        System.out.println("Por ej: 3 dias antes de la compra actual");
        Integer d = scanner.nextInt();

        while(true) {

            System.out.println("INGRESA GASTO DEL DÍA");
            Integer gasto =  scanner.nextInt();

            gastos.add(gasto);

            if(!gastos.isEmpty() && gastos.size() > d) {
                // Llama al método contarNotificaciones para obtener el número de alertas
                long resultado = contarNotificaciones(gastos, d);

                // Imprime el resultado
                System.out.println("=======================================");
                System.out.println("Número de notificaciones: " + resultado);
                System.out.println("=======================================");
            }

        }
    }
}
