# maxflow.mod - Modelo AMPL equivalente a ejemplos/maxflow.lsi
# Notas:
# - El fichero .lsi define funciones externas (getN, containsEdge, edgeMax, edgeMin, isSource, isSink, isIntermediate).
#   En AMPL se modelan esas entidades como conjuntos y parámetros que deben ser provistos en un fichero de datos (.dat).
# - Declarar en el fichero de datos: n, NODE (o n para construir 0..n), EDGES, capMax (opcional), capMin (opcional), SOURCES, SINKS.

# Tamaño / nodos
param n integer >= 0;                # número máximo de nodo; debe fijarse en el .dat
set NODE := 0..n;                     # nodos etiquetados de 0 a n

# -- EDGES como lista de pares proporcionada en el .dat --
set EDGES within (NODE cross NODE);    # definir en el .dat con sintaxis de pares: set EDGES := (0,1) (0,2) ... ;

# Capacidades por arista (indexadas por EDGES). Valores por defecto permiten [0, +inf)
param edgeMax{(i,j) in EDGES} default 1e30;
param edgeMin{(i,j) in EDGES} default 0;

# Conjuntos de fuentes y sumideros (definir en .dat como sets de nodos)
set SOURCES subset NODE;
set SINKS subset NODE;
set INTERMEDIATE := NODE diff (SOURCES union SINKS);

# Variables
var y{(i,j) in EDGES} >= edgeMin[i,j] <= edgeMax[i,j];
var x{i in NODE} >= 0;

# Objetivo: maximizar la suma de x[i] para los nodos fuente
maximize TotalFlow: sum{i in SOURCES} x[i];

# Restricciones
subject to FlowConservationIntermediate{i in INTERMEDIATE}:
    sum{(j,i) in EDGES} y[j,i] - sum{(i,j) in EDGES} y[i,j] = 0;

subject to DefineX_In{i in INTERMEDIATE union SINKS}:
    sum{(j,i) in EDGES} y[j,i] - x[i] = 0;

subject to DefineX_Out{i in SOURCES}:
    sum{(i,j) in EDGES} y[i,j] - x[i] = 0;

# Notas para el .dat (lista de pares):
# Example .dat should include:
# param n := 4;
# set EDGES := (0,1) (0,2) (1,3) (2,3) (3,4);
# param edgeMax :=
# (0,1) 10
# (0,2) 5
# (1,3) 15
# (2,3) 10
# (3,4) 20 ;
# # edgeMin can be omitted or provided similarly
# set SOURCES := 0;
# set SINKS := 4;
# The model uses EDGES directly and assumes unspecified edgeMax/edgeMin use defaults.

# Comentario: en el .lsi original algunas comprobaciones eran condicionales (por ejemplo solo imponer límite superior si
# existe edgeMax). Aquí se usan parámetros capMax/capMin con valores por defecto; si no se quieren límites superiores, dejar capMax grande.
# Proveer un fichero .dat con los conjuntos/param necesarios, por ejemplo:
# param n := 4;
# set NODE := 0 1 2 3 4;
# set EDGES := (0,1) (0,2) (1,3) (2,3) (3,4);
# param capMax := [
#  (0,1) 10
#  (0,2) 5
#  (1,3) 15
#  (2,3) 10
#  (3,4) 20
# ];
# set SOURCES := 0;
# set SINKS := 4;

