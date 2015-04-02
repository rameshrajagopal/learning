#!/usr/bin/env bash
WORKDIR=`pwd`
export CLASSPATH=$WORKDIR:$WORKDIR/src:$WORKDIR/tests
javac src/BigInteger.java
javac tests/BigIntegerTest.java
cd tests;
java  BigIntegerTest
cd ../
