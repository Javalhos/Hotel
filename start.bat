REM @echo off
set CLASSPATH=./lib/*;./

echo Compiling files...
javac ./src/Controllers/*.java ./src/Data/*.java ./src/Database/*.java ./src/Models/*.java Server.java
echo Starting server...
java Server

pause