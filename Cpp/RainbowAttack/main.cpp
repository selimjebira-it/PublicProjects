#include <iostream>
#include "generatetable.h"
#include "crackpwd.h"

using namespace std;

int main()
{

    generateTable genT (8,1,pow(2,14),8,"rainbowTable.txt");

    genT.createTable();
    genT.saveToFile();
    crackPwd("rainbowTable.txt",8);

    return 0;
}
