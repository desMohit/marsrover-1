#! /bin/sh

autoheader -f
touch README
touch stamp-h
aclocal
autoconf -f
automake --add-missing -c
