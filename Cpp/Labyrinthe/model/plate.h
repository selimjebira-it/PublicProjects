#ifndef PLATE_H
#define PLATE_H
#include <vector>
#include "model/direction.h"
#include "model/card.h"

class Plate
{
    /**
     *Classe représentant les tuiles (coullissantes et non coullisantes) sur le plateau.
     * Cette classe est abstraite, il en découle des tuiles en "T", "L" et "I".
    */
protected:

    std::vector<std::string> openings_;/**< Liste des ouverture d'une pièce par rapport à l'extérieur, par exemple une truile "L" aura haut et bas*/

    std::vector<std::wstring> shapes_;/**< Sert à l'affichage de la plate*/

    Direction directionEnum_;/**< enum des différentes directions possible*/

    Position position_;/**< Position de la tuile sur le plateau*/

    unsigned rotationLvl_;/**< niveau de rotation de la pièce (les openings changeant également avec le niveau de rotation)*/

    std::string card_;/**< Trésore de labyrinte présent sur chaque tuile, les tuiles sans réel trésor on le trésor "aucun" (ou "")*/



    inline Plate(Position position =Position(0,0), std::string const & card =""):
        position_{position},rotationLvl_{0}, card_{card}{}

public:

 /**< Destructeur*/
    virtual ~Plate() = 0;

    /** Méthode qui retourne les ouverture de la tuile
     * \return* les ouverture de la tuile */
    inline std::vector<std::string> getOpenings() const{return openings_;}
    /** Méthode qui retourne le trésort de la carte.
     * \return le trésort de la carte
    */
    inline string getCard() const {return card_;}
    /** Méthode qui retourne le niveau de rotation de la tuile
     * \return le niveau de rotation de la tuile*/
    inline unsigned getRotationLvl() const {return  rotationLvl_;}

    /** Méthode qui retourne la position de la tuile
     * \return la position de la tuile*/
    inline Position getPosition() const {return position_;}
    inline void setPosition(Position & pos){position_ = pos;}

    /** méthode permetant de retourner la pièce d'un quart de tour vers la droite
     *nombre de quarts de tour
    */
    void rotate(unsigned);
    /**
     *méthode permettant de voir si deux plates tuiles sont connectées pas leur chemin
     * \return vrai si les pièces sont connectées, faux autrement
    */
    bool connected(Plate const & other) const;

    virtual std::string type() const = 0;

    virtual std::wstring shape() const = 0;

    virtual bool isMovable() const = 0;

};

inline Plate::~Plate() = default;
#endif // PLATE_H
