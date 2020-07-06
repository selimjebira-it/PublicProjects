#include "controler.h"
#include "QDebug"
#define NB_MIN 2
#define NB_MAX 4



bool Controler::move(int x, int y){

    return game_->movePawn(Position(x,y));
}

bool Controler::play(int index)
{
//    qDebug() << "appel à play";
    auto pos = Position( index%7 ,index /7);
    if(game_->getTurnstate() == Game::TurnStatus::NothingMoved){
        return game_->insertPlate(pos);
    }else{
        return game_->movePawn(pos);
    }
}

bool Controler::insert(int x, int y){

    return game_->insertPlate(Position(x,y));
}

void Controler::start(int deckSize){

    if(game_->getGameState() == Game::GameStatus::notOpen){
        if(NB_MIN <= names_.size() && names_.size() <= NB_MAX){
            for(auto & i : names_){
                game_->addPlayer(i);
            }game_->init(deckSize);
        }
    }
}

void Controler::end(){


        game_->end();



}

bool Controler::skip(){

    return game_->skip();
}


bool Controler::addPlayers(std::vector<std::string> names){

    auto res = true;
    for(auto & name : names){
        if(!verifyName(name)){
            res = false;
            break;
        }
    }
    if(res){
        for(auto & name : names){
            names_.push_back(name);
        }
    }
    return res;
}

bool Controler::verifyName(std::string const & name){
    return !(name.size() > 8 || name.size() < 3
            || game_->getGameState() == Game::GameStatus::running
            || names_.size() >= NB_MAX
            || util::contains(names_,name));

}
bool Controler::addPlayer(const string  &name){
    if(verifyName(name)){
        names_.push_back(name);
        return true;

    }
    return false;
}


bool Controler::removePlayer(string const & name){

    if(!(game_->getGameState() == Game::GameStatus::running) && (util::contains<string>(names_,name))){
        auto rm = std::remove(names_.begin(),names_.end(),name);
        names_.erase(rm,names_.end());
        return true;
    }
    return false;

}


void Controler::rotate(){

    if(game_->getGameState() == Game::GameStatus::running){
        game_->rotate();
    }

}


