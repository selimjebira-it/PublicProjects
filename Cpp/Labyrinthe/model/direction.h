#ifndef DIRECTION_H
#define DIRECTION_H
#include <vector>
#include "position.h"
#include <string>
#include <stdexcept>

using namespace std;

class Direction
{
public:

    const string Up_ = "Up";
    const string Right_ = "Right";
    const string Down_ = "Down";
    const string Left_ = "Left";

private:

    std::vector<string> values_;



    Position const upPosition;
    Position const rightPosition;
    Position const downDPosition;
    Position const leftPosition;


public:

    /**
     * @brief Direction constructeur de direction
     */
    inline Direction():upPosition{Position(0,-1)}, rightPosition{Position(1,0)}, downDPosition{Position(0,1)},leftPosition{Position(-1,0)}

    {
        values_.push_back(Up_);
        values_.push_back(Right_);
        values_.push_back(Down_);
        values_.push_back(Left_);
        static int cpt = 0;
        cpt++;
    }

    /**
     * @brief clock permet de tourner une position d'un quart de tour, dans le sens horloger.
     * la direction d'origine n'est pas modifiée
     * @return une direction correspondant à celle d'origine après rotation
     */
    string clock(string const &) const;

    /**
     * @brief retourn l'opposer de la position en paramètre
     * @return l'opposé de la position
     */
    string opposite(string const &) const;

    inline Position getPosition(string const & direction) const{

        if(direction.compare(Up_)== 0){
            return upPosition;
        }else if(direction.compare(Down_)== 0){
            return downDPosition;
        }else if(direction.compare(Right_)== 0){
            return rightPosition;
        }else if(direction.compare(Left_)== 0){
            return leftPosition;
        }else{
            throw std::invalid_argument("Bad direction request: getPosition");
        }
    }


    Direction(Direction const&) = delete ;

    /**
     * @brief values retourne la liste de toutes les directions
     * @return la liste de toutes les directions
     */
    inline  std::vector<string> values()const{
        return values_;
    }

    /**
     * @brief vecteur renvoie une Position correspondant à ce qui
     * faudrait ajouter à une position originale, pour que cette dernière indique un emplacement
     * décaler d'une cetaine distance et dans une certaine direction (en somme un vecteur)
     * par rapport à l'emplacement original.
     * @param direction direction de décalage de la position
     * @param ext distance de décalage
     * @return la position à additionner pour décalé.
     */
    inline Position vecteur(string direction, int ext = 1) const {
        if(Up_.compare(direction)!=0 && Down_.compare(direction)!=0 && Right_.compare(direction)!=0 && Left_.compare(direction)!=0){
            throw std::invalid_argument("Bad direction request");
        }
        return Position(getPosition(direction).x()*ext,getPosition(direction).y()*ext);
    }


};


#endif // DIRECTION_H
