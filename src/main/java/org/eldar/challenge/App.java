package org.eldar.challenge;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {




    // Método para agregar un valor al TreeMap que cuenta la cantidad de ocurrencias de cada gasto
    private static void agregar(TreeMap<Double, Double> map, Double valor) {
        map.put(valor, map.getOrDefault(valor, 0.0) + 1);  // Incrementa el conteo del valor
    }




    // Método para eliminar un valor del TreeMap
    private static void eliminar(TreeMap<Double, Double> map, Double valor) {
        Double cuenta = map.get(valor); // Obtiene el conteo actual del valor
        if (cuenta == 1) {
            map.remove(valor);  // Si solo hay una ocurrencia, elimina el valor del TreeMap
        } else {
            map.put(valor, cuenta - 1);  // Decrementa el conteo si hay más de una ocurrencia
        }
    }




    // Método para encontrar la mediana en un TreeMap
    private static double obtenerMediana(TreeMap<Double, Double> map, int d) {
        int conteoAcumulado = 0;
        Double mediana1 = 0.0, mediana2 = 0.0;

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
    public static long contarNotificaciones(List<Double> gastos, int d) {
        // Inicializamos un TreeMap vacío para almacenar los gastos
        TreeMap<Double, Double> map = new TreeMap<>();

        // Añadimos los primeros 'd' días de gastos al TreeMap usando Streams
        IntStream.range(0, d).forEach(i -> agregar(map, gastos.get(i)));

        // Usamos un IntStream que recorre desde el día 'd' hasta el final de los gastos
        return IntStream.range(d, gastos.size())
                // Filtramos los días en los que se envía una notificación de fraude
                .filter(i -> {
                    // Calcula la mediana usando los últimos 'd' días
                    double mediana = obtenerMediana(map, d);

                    System.out.println(gastos.get(i)+" - "+ 2*mediana);
                    // Verifica si el gasto del día actual es mayor o igual al doble de la mediana
                    boolean notificacion = gastos.get(i) >= 2 * mediana;

                    // Actualiza el TreeMap eliminando el gasto más antiguo y agregando el nuevo
                    eliminar(map, gastos.get(i - d));
                    agregar(map, gastos.get(i));

                    return notificacion;
                })
                // Contamos cuántos días cumplen con la condición del filtro
                .count();
    }

    void Interactive(Scanner scanner,Integer d,List<Double> gastos){
        System.out.println("INGRESA GASTO DEL DÍA");
        Double gasto = null;

        try {
            gasto = scanner.nextDouble();

            gastos.add(gasto);

            if (!gastos.isEmpty() && gastos.size() > d) {
                // Llama al método contarNotificaciones para obtener el número de alertas
                long resultado = contarNotificaciones(gastos, d);
                // Imprime el resultado
                System.out.println("=======================================");
                System.out.println("Notificaciones enviadas: " + resultado);
                System.out.println("=======================================");
            }
        }catch (Exception e){
            System.out.println("Error: Dígito no aceptado");
            Interactive(scanner,d,gastos);
        }
    }

    public static boolean validateText(String validate){

        if(validate == null || validate == "" || !validate.matches("[a-zA-Z]+"))
            return false;

        return true;
    }

    public App(){
        Scanner scanner =  new Scanner(System.in);

        // Ejemplo de datos de entrada como una lista de Integer
        List<Double> gastos = new ArrayList<>();

        System.out.println("Ingresa los datos de un cliente");

        System.out.println("NOMBRE:");
        String nombre = scanner.next();
        if(!validateText(nombre)){
            System.out.println("Error: Mal ingreso del nombre");
            new App();
        }

        System.out.println("APELLIDO:");
        String apellido = scanner.next();
        if(!validateText(apellido)){
            System.out.println("Error: Mal ingreso del apellido");
            new App();
        }
        Integer dni = 0;
        try {
            System.out.println("DNI:");
             dni = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Error: dígito no aceptado ");
            new App();
        }

        Cliente cliente = new Cliente(nombre,apellido,dni);

        System.out.println("HOLA: " + cliente.getNombre()+" "+cliente.getAppelido());

        System.out.println("Ingresa el plaso de dias anterires.");
        System.out.println("Por ej: 3 dias antes de la compra actual");
        Integer d = null;
        try {
             d=scanner.nextInt();

        }catch (Exception e){
            System.out.println("Error: dígito no aceptado ");
            new App();
        }

        while(true) {
            Interactive(scanner,d,gastos);
        }
    }



    public static void main( String[] args ) {
        new App();
    }
}
