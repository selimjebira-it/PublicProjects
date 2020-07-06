#ifndef COLOR_H
#define COLOR_H
#include <string>
#include <vector>
#include <stdexcept>
#include "position.h"


using namespace std;
/** Classe reprenant les 4 couleurs du plateau.
 *  Les couleurs représentes chacune une position particulière sur le plateaux
*/
class Color
{
public:
     const string Red = "Red";
     const string Green = "Green";
     const string Blue = "Blue";
     const string Pink = "Pink";

private:

     std::vector < string> values_;/**< Liste des 4 couleurs*/

    Position positionRed_;/**< position relative à la couleur rouge*/
    Position positionGreen_;/**< position relative à la couleur verte*/
    Position positionPink_;/**< position relative à la couleur rose*/
    Position positionBleu_;/**< position relative à la couleur bleue*/




public:

    inline Color(): positionRed_{Position(0,0)},positionGreen_{Position(6,6)},
        positionPink_{Position(6,0)}, positionBleu_{Position(0,6)}{
        values_.push_back(Red);
        values_.push_back(Green);
        values_.push_back(Blue);
        values_.push_back(Pink);
    }

    /** Cette methode retourne la liste des 4 couleurs
     *\return la liste des 4 couleurs
    */
    inline  std::vector< string>   values() const{
        return values_;
    }

    /** Cette methode renvoie la position relative à la couleur sur le plateau
     * \param couleur pour laquelle on souhaite conaitre la position
     * \return la position relative à la couleur
    */
    inline Position const &  gamePosition(string color) const {
        if(color.compare("Red")== 0){
            return positionRed_;
        }else if(color.compare("Green")== 0){
            return positionGreen_;
        }else if(color.compare("Blue")== 0){
            return positionBleu_;
        }else if(color.compare("Pink")== 0){
            return  positionPink_;
        }else{
            throw std::invalid_argument("Bad color request");
        }
    }

};

#endif // COLOR_H
