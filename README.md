# Notificaciones de Fraude de Transacciones en Java

Este proyecto en Java está diseñado para identificar notificaciones de fraude basadas en los gastos diarios de un cliente. Utiliza `TreeMap` para gestionar de manera eficiente los gastos de los días anteriores y calcular la mediana, así como para contar las notificaciones cuando el gasto en un día es al menos el doble de la mediana de los días anteriores.

## Descripción de los Métodos

### Método `agregar`

Este método se encarga de añadir un valor al `TreeMap`, que mantiene un registro de la cantidad de ocurrencias de cada gasto. Cuando se añade un nuevo gasto, el método incrementa su conteo en el mapa. Si el gasto ya existe en el mapa, su conteo se incrementa; si no, se agrega al mapa con un conteo inicial de uno.

### Método `eliminar`

Este método elimina un valor del `TreeMap`. Primero, obtiene el conteo actual del gasto en el mapa. Si el gasto tiene solo una ocurrencia, se elimina completamente del mapa. Si hay más de una ocurrencia, simplemente se decrementa el conteo del gasto en el mapa.

### Método `obtenerMediana`

Este método calcula la mediana de los gastos almacenados en el `TreeMap`. Para encontrar la mediana, el método itera sobre las entradas del mapa acumulando el conteo hasta que alcanza el punto de la mediana. La mediana puede ser un valor único (si el número de días es impar) o el promedio de dos valores (si el número de días es par).

### Método `contarNotificaciones`

Este es el método principal que cuenta las notificaciones de fraude. Primero, inicializa un `TreeMap` vacío y agrega los primeros días de gastos al mapa. Luego, recorre los días restantes y realiza los siguientes pasos para cada día:

1. Calcula la mediana de los gastos en el `TreeMap` utilizando los días anteriores.
2. Compara el gasto del día actual con el doble de la mediana calculada. Si el gasto cumple con la condición de ser al menos el doble de la mediana, se considera que se ha generado una notificación.
3. Actualiza el `TreeMap` eliminando el gasto más antiguo y agregando el nuevo gasto del día actual.

Finalmente, cuenta y retorna la cantidad total de notificaciones enviadas.

## Repositorio

    ```bash
    git clone https://github.com/Bautistadev/Ejercicio_3.git
    ```
