#ifndef CRACKPWD_H
#define CRACKPWD_H
#include <string>
#include <unordered_map>
#include <fstream>
#include <sstream>
#include <iostream>

class crackPwd
{
    std::string filenameOfTable;
    std::size_t sizeMdp;
    std::unordered_map<std::string,std::string> dico;


    void extract();

public:
    crackPwd(std::string filename, std::size_t sizeMdp):filenameOfTable{filename},sizeMdp{sizeMdp}
    {
        extract();

    }

    std::string findRow(std::string, int);
    std::string findPswd(std::string, std::string);

};

#endif // CRACKPWD_H
