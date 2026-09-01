# correcto.mod - Modelo AMPL equivalente a ejemplos/correcto.pli
# Parámetros (valores tomados del .pli original)
param N integer >= 1 := 10;    # Número de índices
param C := 4.5;                # Constante (no usada en el modelo original, se mantiene por equivalencia)

# Índice
set I := 1..N;

# Variables
# x[i] es entero con 0 <= x[i] <= 10
var x{i in I} integer >= 0 <= 10;
# b[i] es binaria
var b{i in I} binary;

# Objetivo: minimizar la suma de las x[i]
minimize TotalX: sum{i in I} x[i];

# No hay restricciones adicionales en el .pli original más allá de los límites de variable
# (si quieres añadir restricciones que dependan de C o de b, indícalo y las incorporo)
