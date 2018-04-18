#!/bin/bash
cd src
javac Krypto.java
# The output of the program will always 
# be dumped into the output files directory 
chmod +x sha.sh 
chmod +x des.sh
cd ..;cd Scripts; 
javac iByte.java 
cd ..; cd src; 
java Krypto -alpha -encrypt -example.txt 
