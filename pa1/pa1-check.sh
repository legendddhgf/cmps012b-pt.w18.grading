#!/usr/bin/bash

SRCDIR=https://raw.githubusercontent.com/legendddhgf/cmps012b-pt.w18.grading/master/pa1
NUMTESTS=1
PNTSPERTEST=6
let MAXPTS=$NUMTESTS*$PNTSPERTEST

if [ ! -e backup ]; then
  echo "WARNING: a backup has been created for you in the \"backup\" folder"
  mkdir backup
fi

cp *.java Makefile backup   # copy all files of importance into backup

for NUM in $(seq 1 $NUMTESTS); do
#  curl $SRCDIR/infile$NUM.txt > infile$NUM.txt
  curl $SRCDIR/model-outfile$NUM.txt > model-outfile$NUM.txt
done

curl $SRCDIR/ModelRecursionTest.java > ModelRecursionTest.java

make

if [ ! -e Recursion ] || [ ! -x Recursion ]; then # exist and executable
  echo ""
  echo "Makefile doesn't correctly create Executable!!!"
  echo ""
  rm -f *.class
  javac -Xlint Recursion.java
  echo "Main-class: Recursion" > Manifest
  jar cvfm Recursion Manifest *.class
  rm Manifest
  chmod +x Recursion
fi

echo ""
echo ""

recursiontestspassed=$(expr 0)
echo "Please be warned that the following tests discard all output to stdout/stderr"
echo "Recursion tests: If nothing between '=' signs, then test is passed"
echo "Press enter to continue (Type \"v + enter\" for more details)"
read verbose
for NUM in $(seq 1 $NUMTESTS); do
  rm -f outfile$NUM.txt
  timeout 5 Recursion &> outfile$NUM.txt >> outfile$NUM.txt
  diff -bBwu outfile$NUM.txt model-outfile$NUM.txt &> diff$NUM.txt >> diff$NUM.txt
  if [ "$verbose" == "v" ]; then
    echo "Test $NUM:"
    echo "=========="
    cat diff$NUM.txt
    echo "=========="
  fi
  if [ -e diff$NUM.txt ] && [[ ! -s diff$NUM.txt ]]; then
    let recursiontestspassed+=1
  fi
  rm -f *outfile$NUM.txt
done

echo ""
echo ""

let recursiontestpoints=$PNTSPERTEST*$recursiontestspassed

echo "Passed $recursiontestspassed / $NUMTESTS Recursion tests"
echo "This gives a total of $recursiontestpoints / $MAXPTS points"

echo ""
echo ""

make clean

if [ -e Recursion ] || [ -e *.class ]; then
  echo "WARNING: Makefile didn't successfully clean all files"
fi

echo ""

echo "Press Enter To Continue with RecursionTest Results (Type \"v + enter\" for more details)"
read verbose

javac ModelRecursionTest.java Recursion.java
if [ "$verbose" == "v" ]; then
  timeout 5 java ModelRecursionTest -v > RecursionTest-out.txt &>> RecursionTest-out.txt
else
  timeout 5 java ModelRecursionTest > RecursionTest-out.txt &>> RecursionTest-out.txt
fi

cat RecursionTest-out.txt
rm -f RecursionTest-out.txt

rm -f *.class ModelRecursionTest.java garbage
