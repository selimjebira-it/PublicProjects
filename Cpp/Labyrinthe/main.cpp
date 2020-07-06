#include <iostream>
#include "model/game.h"
#include "controler/controler.h"
#include "view/viewConsole.h"
#include <QApplication>
#include "view/qview.h"
#include "view/viewgraphique.h"
#include "model/random.hpp"


using namespace std;

int myMain(int argc, char **argv)
{


    QApplication app(argc,argv);
    srand(unsigned(nvs::random_value()));
    Game game;
    Controler controler(&game);
    ViewGraphique vg(controler);
    game.registerObserver(&vg);
    vg.show();

    return app.exec();

}
