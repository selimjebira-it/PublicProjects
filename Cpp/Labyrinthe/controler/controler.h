#ifndef CONTROLER_H
#define CONTROLER_H

#include <vector>
#include <string>
#include <cctype>
#include <algorithm>
#include "model/game.h"


using namespace std;
class Controler
{

    vector <std::string> names_;
    Game * game_;

    bool control();


public:

    Controler(Game * game):game_{game}{}
    bool addPlayer(std::string const &);
    bool addPlayers(std::vector<string>);
    bool removePlayer(std::string const &);

    bool insert(int,int);
    bool move(int,int);
    bool play(int);
    void rotate();
    bool skip();
    void start(int maxDeckSize = 12);
    void end();
    inline int nbNames()const {return names_.size();}
    inline void reset(){names_.clear();game_->reset();}

private:

    bool verifyName(std::string const & name);
};

#endif // CONTROLER_H
