#ifndef IPLATE_H
#define IPLATE_H
#include "model/plate.h"

/**
 * @brief The IPlate class, plate I, hérite de plate.
 */
class IPlate:public Plate
{



public:

    /**
     * @brief IPlate constructeur de Iplate, hérite de plate.
     * @param pos position de la plate sur le plateau
     * @param card éventuel trésor présent sur le la plate
     * @param rotationLvl niveau de rotation de la plate
     */
    inline IPlate(Position pos, std::string const & card ="" ,unsigned rotationLvl = 0)
        :Plate(pos,card)
    {
        openings_.push_back(directionEnum_.Up_);
        openings_.push_back(directionEnum_.Down_);

        shapes_.push_back(L" ║ ");
        shapes_.push_back(L" ═ ");


        rotate(rotationLvl);
    }

    ~IPlate() override =default;

    /**
     * @brief type retourne le type de la pièce, ici " I ";
     * @return type de la pièce
     */
    std::string type() const override{return "I";}

    std::wstring shape() const override{return shapes_.at(rotationLvl_%2);}

    /**
     * @brief isMovable retourne si la pièce est déplaçable
     * @return vrai si la pièce est déplaçable, faux sinon
     */
    bool isMovable() const override{ return true;}
};

#endif // IPLATE_H
