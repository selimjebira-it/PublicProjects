#include "position.h"



bool Position::operator>(Position const & other) const {
    return ((y()*100 + x()) > (other.y() * 100 + other.x()));
}

bool Position::operator<(const Position &other) const {
    return !(*this > other);
}


Position operator+(Position const & pos1, Position const & pos2){
    return Position(pos1.x() + pos2.x(),pos1.y() + pos2.y());
}
Position operator-(Position const & pos1, Position const & pos2){
    return Position(pos1.x() - pos2.x(),pos1.y() - pos2.y());
}

bool operator==(Position const & pos1 , Position const & pos2){
    return (pos1.x() == pos2.x() && pos1.y() == pos2.y());
}
std::ostream& operator<<(std::ostream & sortie,const Position & pos){
    sortie << "(" <<pos.x() << "," << pos.y() << ")";
    return sortie;
}
