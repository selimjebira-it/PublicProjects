#ifndef GENERATETABLE_H
#define GENERATETABLE_H
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <random>
#include <time.h>

class generateTable
{
    unsigned int password_Size;
    long int nb_Iterations;
    long int nb_Combinaisons;
    size_t nb_Threads;


    std::string filename;
    std::unordered_map <std::string , std::string> dico;
    std::unordered_set<std::string> passWds;

public:

    generateTable(unsigned int password_Size,long int nb_Iterations,long int nb_Combinaisons,
                  size_t nb_Threads,std::string filename)
        :password_Size{password_Size},nb_Iterations{nb_Iterations}, nb_Combinaisons{nb_Combinaisons},
          nb_Threads{nb_Threads},filename{filename}{}


    void createTable();

    void saveToFile();

    void loadFromFile();

    static void* createLines(void *);

    static std::string reduce(std::string const &,long const &);

    static std::string generateTail(std::string ,long const &,std::unordered_set<std::string> &);

};

#endif // GENERATETABLE_H
