#include "generatetable.h"
#include <pthread.h>
#include "generatepwd.h"
#include "sha256.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>



static pthread_mutex_t D_Mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_mutex_t P_Mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_mutex_t F_Mutex = PTHREAD_MUTEX_INITIALIZER;
static std::string characterSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
static size_t charSetSize = characterSet.size();


void generateTable::createTable(){

    std::cout << "creation of the table" << std::endl;
    time_t start = time(nullptr);
    std::random_shuffle(characterSet.begin(), characterSet.end());
    pthread_t threads[nb_Threads];
    for(auto i = 0; i < nb_Threads; i++)
    {
        pthread_create( &(threads[i]), nullptr,createLines,static_cast<void*>(this));
    }
    for(auto i= 0; i < nb_Threads; i++)
    {
        pthread_join(threads[i],nullptr);
    }

    time_t end = time(nullptr);
    std::cout << " time : " << end-start << " s " << std::endl;
}

void* generateTable::createLines(void* elm){

    generateTable* genTable = static_cast<generateTable*>(elm);

    std::ofstream file(genTable->filename , std::ios::out | std::ios::app);

    unsigned counter ;
    auto limit = genTable->nb_Combinaisons/genTable->nb_Threads;

    std::string password,tail;

    for (auto i = 0; i < limit; i ++) {

        //lock the set of passwords
        pthread_mutex_lock(&P_Mutex);

        do{
            password =  generate_password(genTable->password_Size);
            counter = genTable->passWds.count(password);
        }while(counter > 0);


        genTable->passWds.insert(password);

        //unlock while we are generating the tail
        pthread_mutex_unlock(&P_Mutex);

        tail = generateTail(password,genTable->nb_Iterations,genTable->passWds);

        //lock the file to write in
        pthread_mutex_lock(&F_Mutex);

        if(file){
            file << password << " " << tail << std::endl;
        }
//        genTable->dico[password] = tail;


        //unlock when finished
        pthread_mutex_unlock(&F_Mutex);


    }
    file.close();
    std::cout << limit <<" elements added " <<std::endl;





}


std::string generateTable::reduce(std::string const & hashed, long const & reduceLvl){

    std::string result("");
    unsigned salty = reduceLvl % 2 == 0 ? 3:7;
    for(auto i = 0 ; i < 8 ; i ++){
        result.push_back(static_cast<char>(characterSet[((static_cast<unsigned>(hashed[i * salty])) + reduceLvl )% charSetSize]));
    }
    return result;
}

std::string generateTable::generateTail(std::string  tete, long const & iterations,std::unordered_set<std::string> & pass){

    for(auto i = 0; i < iterations ; i ++){
        tete = reduce(sha256(tete),i);
//        pthread_mutex_lock(&P_Mutex);
//        pass.insert(tete);
//        pthread_mutex_unlock(&P_Mutex);
    }
    return tete;


}

void generateTable::saveToFile(){

    std::ofstream file(filename , std::ios::out | std::ios::app);

    if(file) {

        for(auto i = dico.begin(); i != dico.end(); i ++){
            file << i->first << " " << i->second << std::endl;
        }
        file.close();
    } else {
        std::cerr << "Erreur à l'ouverture !" << std::endl;
    }
    dico.clear();

}

void generateTable::loadFromFile(){

        std::ifstream file(filename);
        std::string buff;
        if(file){
            while(file.eof() != 0){
                //todo
            }
        }
}


