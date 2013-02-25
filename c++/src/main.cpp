#include <config.h>
#include <stdio.h>

#include <iostream>

int main (void)
{
    puts ("Hello World!");
    puts ("This is " PACKAGE_STRING ".");

    std::cout << "This is C++" << std::endl;
    return 0;
}
