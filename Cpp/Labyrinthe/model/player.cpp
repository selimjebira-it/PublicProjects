#include "player.h"

//#include <stdnoreturn.h> //WTF????! c'est qui lui


Player::Player(string const & name, vector<string> const &deck, const Pawn &pawn)
    :name_{name},deck_{deck},pawn_{pawn}{
}


void Player::setCurrentCard(){

    if(!deck_.empty()){
        done_.push_back(deck_.back());
        deck_.pop_back();
    }
}


bool Player::hasFinished() const{
    return deck_.empty() && pawn_.isOnStart();
}


std::ostream& operator<<(std::ostream & sortie,const Player & player){
    sortie << player.name() << " : " << player.pawn().position() << " Current Card : "<<  player.getCurrentCard();
    return sortie;
}
