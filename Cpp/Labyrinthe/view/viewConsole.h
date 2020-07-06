#ifndef VUECONSOLE_H
#define VUECONSOLE_H
#include "model/util.h"
#include "observer/observer.h"
#include "model/game.h"
#include "controler/controler.h"
#include "observer/subject.h"
#include <map>
#include <typeinfo>


class ViewConsole:public nvs::Observer//merci
{


    Controler & controler_;
    Game const * subject_;

    void welcome();
    void showCurrentCard();
    void showPlayers();
    void showPawns();
    void showBoard();
    void showCurrentPlayer();
    void showCards();
    void showCurrentPlate();
    void showTurn();
    void showEndOFGame();
    void showWinner();
    void help();
    void askPlayer();
    void addPlayer();
    void update(nvs::Subject const * subject) override;

public:


    ViewConsole(Controler & controler);

    inline ~ViewConsole()override = default;

    void start();



};


#endif // VUECONSOLE_H
