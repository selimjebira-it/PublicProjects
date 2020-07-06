#ifndef POSITION_H
#define POSITION_H
#include <iostream>

/**
 * @brief The Position class, permet de définir une position x, y
 */
class Position
{
private:

    /**
     * @brief x_ coordonée x
     */
    int x_;
    /**
     * @brief y_ coordonée y
     */
    int y_;

public:

    /**
     * @brief Position
     * @param x coordonée x
     * @param y coordonée y
     */
    inline Position(int x= 0,int y= 0):x_{x},y_{y}{}
    /**
     * @brief x retourne la coordonée x
     * @return la coordonée x
     */
    inline int x() const {return x_;}
    /**
     * @brief y retourne la coordonée y
     * @return la coordonée y
     */
    inline int y() const {return y_;}

    /**
     * @brief operator >  indique si 100*y + x de la position est plus grand que 100*y + x de l'autre position
     * @param other l'autre position
     * @return retourne vrai si 100*y + x de la position est plus grand que 100*y + x de l'autre position, faux sinon
     */
    bool operator>(Position const & other) const ;

    /**
     * @brief operator < indique si la position est plus petite qu'une autre c'est à dire si elle n'est pas plus grande ou égale (<, ==)
     * @param other l'autre position
     * @return vrai si la position est plus petite qu'une autre c'est à dire si elle n'est pas plus grande ou égale (<, ==), faux sinon
     */
    bool operator<(Position const & other) const ;
};

/**
 * @brief operator + retourne une position dont le x est la sommes des x des deux position entrées en paramètres, idem pour y.
 * @return une position dont le x est la sommes des x des deux position entrées en paramètres, idem pour y.
 */
Position operator+(Position const &, Position const &);

/**
 * @brief operator - retourne une position dont le x est la différence des x des deux position entrées en paramètres, idem pour y.
 * @return une position dont le x est la différence des x des deux position entrées en paramètres, idem pour y.
 */
Position operator-(Position const &, Position const &);

/**
 * @brief operator == indique l'égalité de deux positions par rapport à l'égalité de leurs coordonnées( x == x et y == y)
 * @return
 */
bool  operator==(Position const &, Position const &);

std::ostream& operator<<(std::ostream & sortie,const Position & pos);


#endif // POSITION_H
