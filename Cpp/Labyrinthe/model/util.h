#ifndef UTIL_H
#define UTIL_H

#include <vector>
#include <iostream>
//#include <fcntl.h> /* A SUPPRIMER */
#include <time.h>
#include <sstream>




using namespace std;

namespace util {

template <typename T>

/**
 * @brief contains vérifie si un élement d'un certain type est présent aumoins une fois dans un vecteur contenant le même type d'élément
 * @param vec vecteur dans lequel on cherche
 * @param elem élément à trouver
 * @return vrai is l'élément a été trouvé, faux sinon
 */
static inline bool contains (std::vector<T> & vec, T const & elem){
    bool found = false;
    unsigned cpt = 0;
    while(cpt< vec.size() && !found){
        found = vec.at(cpt)== elem;
        cpt++;
    }
    return found;
}

template <typename T>
/**
 * @brief printVector affiche le contenu d'un vector (les éléments du vecteurs doivent être affichable avec std::cout)
 * @param vec vecteur à afficher
 */
static inline void printVector(std::vector<T> & vec){
    for(auto i : vec){
        std::cout << i << std::endl;
    }
}

/**
 * @brief cutStringInWord découpe un une chaine de caractère, à chaque espace la chaine
 * est coupée et le premier morceau est placé dans un vecteur
 * @param str chaine de caractère à découpée
 * @return le vecteur de morceau de chaine
 */
static vector<string> cutStringInWord(string const & str){
    vector<string> cutString;
    stringstream ss;
    string temp;

    ss << str;
    string found;

    while (!ss.eof()) {
        ss >> temp;
        stringstream(temp) >> found;
        cutString.push_back(found);
        temp = "";
    }
    return cutString;
}



/*A Supprimer , Util pour DEBOGER fonctionnel sous WINDOS*/
//static inline void printUTf16(std::wstring const & ws){
//    setmode(STDOUT_FILENO,_O_U16TEXT);
//    std::wcout << ws << std::endl;
//    setmode(STDOUT_FILENO,_O_TEXT);
//}
}














#endif // UTIL_H
