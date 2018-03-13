#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa5

curl $SRCDIR/ModelDictionaryTest.java > ModelDictionaryTest.java

echo "Press Enter To Continue with DictionaryTest Results"
read verbose

gcc -std=c99 -Wall ModelDictionaryTest.c Dictionary.c -o ModelDictionaryTest > garbage &>> garbage
cat garbage

timeout 5 valgrind ./ModelDictionaryTest -v > DictionaryTest-out.txt &>> DictionaryTest-out.txt
cat DictionaryTest-out.txt

rm -f *out.txt

rm -f *.o ModelDictionaryTest* garbage*
