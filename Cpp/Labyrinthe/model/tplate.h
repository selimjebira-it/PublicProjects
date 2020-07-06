#ifndef TPLATE_H
#define TPLATE_H
#include "plate.h"

/**
 * @brief The TPlate class
 */
class TPlate:public Plate
{
    bool fixed_;

public:

    /**
     * @brief IPlate constructeur de Tplate, hérite de plate.
     * @param pos position de la plate sur le plateau
     * @param card éventuel trésor présent sur le la plate
     * @param rotationLvl niveau de rotation de la plate
     * @param fixed indique si il est possible de bougé la plate
     */
    inline TPlate(Position pos, std::string const & card ="" ,unsigned rotationLvl = 0,bool fixed = false)
        :Plate(pos,card),fixed_{fixed}
    {
        openings_.push_back(directionEnum_.Right_);
        openings_.push_back(directionEnum_.Down_);
        openings_.push_back(directionEnum_.Left_);
        shapes_.push_back(L" ╦ ");
        shapes_.push_back(L" ╣ ");
        shapes_.push_back(L" ╩ ");
        shapes_.push_back(L" ╠ ");

        rotate(rotationLvl);
    }

    inline std::wstring shape() const override{return shapes_[rotationLvl_];}

    /**
     * @brief type retourne le type de la pièce, ici " L ";
     * @return type de la pièce
     */
    inline std::string type()const override{return " T ";}
    /**
     * @brief isMovable retourne si la pièce est déplaçable
     * @return vrai si la pièce est déplaçable, faux sinon
     */
    inline bool isMovable() const override{return !fixed_;}

    ~TPlate() override = default;
};

#endif // TPLATE_H
