@echo off
setlocal

rem Ir a la raiz del proyecto (el BAT esta en scripts)
pushd "%~dp0.."

echo ==========================================
echo   COMPILACION DEL PROYECTO JAVA
echo ==========================================
echo.

echo [1/4] Borrando directorio bin...
if exist bin rmdir /s /q bin

echo [2/4] Creando directorio bin...
mkdir bin

echo [3/4] Compilando...

setlocal enabledelayedexpansion
set "SOURCES=%temp%\sources.txt"
if exist "%SOURCES%" del "%SOURCES%"
for /R src %%f in (*.java) do (
    set "SOURCE=%%~f"
    set "SOURCE=!SOURCE:\=/!"
    echo "!SOURCE!">>"%SOURCES%"
)
javac -cp "lib\antlr-4.13.2-complete.jar" -sourcepath src -d bin @"%SOURCES%"
endlocal
if exist "%SOURCES%" del "%SOURCES%"

if errorlevel 1 (
    echo.
    echo ==========================================
    echo ERROR EN LA COMPILACION
    echo ==========================================
    popd
    pause
    exit /b 1
)

echo [4/4] Compilacion finalizada correctamente.

echo.
echo ==========================================
echo COMPILACION FINALIZADA CORRECTAMENTE
echo ==========================================
echo Clases generadas en bin
echo.

popd
pause
