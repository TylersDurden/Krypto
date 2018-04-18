#!/bin/bash
touch output.txt
echo 'SHA 256 Sum: ';InputFiles/$1 | shasum;
InputFiles/$1 | shasum > output.txt
cd ..
cd Scripts 
cp $1 pwd
sleep 5
./encrypt.sh $1 SHA 
