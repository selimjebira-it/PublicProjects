#include "plate.h"
#include "util.h"
using namespace std;


void Plate::rotate(unsigned a = 1){

    for(auto & opening : openings_){
        for(unsigned nb = 0; nb < a; nb ++){
            opening = directionEnum_.clock(opening);
        }
    }
    rotationLvl_ = ((rotationLvl_ + a) %4);
}


bool Plate::connected(const Plate & other) const{

    Position delta = other.position_ - position_;

    for(auto & i: directionEnum_.values()){ //pour chacune des directions

        if(directionEnum_.vecteur(i) == delta){ // on regarde si delta est une direction


            for(auto & j : openings_){ //on parcours les openings de la première plate

                if(directionEnum_.vecteur(i) == directionEnum_.vecteur(j)){ //si on a correspondance

                    for(auto & k : other.openings_){ // on vérifie dans l'autre plate qu'elle possède son opposé

                        if(directionEnum_.vecteur(directionEnum_.opposite(i)) == directionEnum_.vecteur(k)) // si c'est le cas elle sont connectées
                            return true;
                    }
                }

            }
        }

    }
    return false;
}

std::ostream& operator<<(std::ostream & sortie,Plate const & plate){
    sortie <<"Plate :" << plate.type() << " Position : " << plate.getPosition() << " Objectif : " << plate.getCard();
    return sortie;
}
