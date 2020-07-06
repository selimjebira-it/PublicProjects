#ifndef PAWN_H
#define PAWN_H
#include <iostream>
#include "color.h"
#include "position.h"
#include <string>

using namespace std;

/**
 * @brief The Pawn class, pion du jeu
 */
class Pawn
{
    /**
     * @brief color_ couleur du pion
     */
    string  color_;
    /**
     * @brief posi_ position du pion
     */
    Position posi_;
    /**
     * @brief starter position de départ du pion
     */
    Position starter;


public:

    /**
     * @brief Pawn constructeur du pion
     * @param colorName nom de la couleur du pion
     * @param posi position du pion (sera aussi sa position de départ)
     */
    inline Pawn(string const & colorName = "", Position posi = Position(0,0)):color_{colorName},posi_{posi}, starter{posi}{}

    /**
     * @brief getColor retourne la couleur du pion
     * @return la couleur du pion
     */
    inline string getColor()const{
        return color_;
    }

    /**
     * @brief position retourne la position du pion
     * @return la position du pion
     */
    inline Position position() const{
        return posi_;
    }

    /**
     * @brief position set la position du pion
     * @param newPosi nouvelle position du pion
     */
    inline void  position(Position const & newPosi){
        posi_ = newPosi;
    }

    /**
     * @brief operator == vérifie l'égalité des pions par rapport au nom de leur couleur.
     * @param other l'autre pion à comparer
     * @return vrai si égaux, faux sinon
     */
    inline bool operator==(Pawn const & other)const{
        return other.color_.compare(color_) == 0;
    }

    /**
     * @brief isOnStart indique si le pion est sur sa position de départ
     * @return vrai si le pion est sur sa position de départ, faux sinon
     */
    inline bool isOnStart() const{
        return  starter == posi_;
    }

};


inline std::ostream& operator<<(std::ostream & out, Pawn const & pawn){
    out <<"Pawn " << pawn.getColor() << " : "<< pawn.position();
    return out;

}

#endif // PAWN_H
