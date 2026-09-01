@echo off

echo.
echo ===================================
echo Limpiando proyecto
echo ===================================
echo.

if exist src\lsi\parser (
    echo Borrando src\lsi\parser...
    rmdir /s /q src\lsi\parser
)

if exist bin (
    echo Borrando bin...
    rmdir /s /q bin
)

echo.
echo Limpieza completada.
echo.

exit /b 0