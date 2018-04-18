#!/bin/bash
echo $1 $2
cd ..
cd Scripts
javac iByte.java
java iByte encrypt $2 $1 