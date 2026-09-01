# academia.mod - modelo AMPL equivalente al problema de asignacion de alumnos a grupos
# Cada alumno debe asignarse a exactamente un grupo y se maximiza la afinidad total.

param N integer >= 1;
param M integer >= 1;

set ALUMNOS := 1..N;
set GRUPOS := 1..M;

param afinidad{ALUMNOS, GRUPOS} >= 0;

var x{ALUMNOS, GRUPOS} binary;

maximize AfinidadTotal:
    sum{i in ALUMNOS, j in GRUPOS} afinidad[i,j] * x[i,j];

subject to AsignarAlumno{i in ALUMNOS}:
    sum{j in GRUPOS} x[i,j] = 1;

# Si se quiere imponer capacidad por grupo, se puede anadir:
# param cap{GRUPOS} >= 0;
# subject to CapacidadGrupo{j in GRUPOS}:
#     sum{i in ALUMNOS} x[i,j] <= cap[j];
