#include "viewgraphique.h"



ViewGraphique::ViewGraphique(const Controler &controler, QMainWindow *parent):QMainWindow (parent),m_controler{controler}
{

    init();

}
void ViewGraphique::init(){

    m_view = new QView(this);
    setCentralWidget(m_view);
    connect(m_view,&QView::boxSelected,this,&ViewGraphique::play);
    connect(m_view,&QView::rotate, this,&ViewGraphique::rotate);
    connect(m_view ,&QView::verifyEntries,this, &ViewGraphique::addPlayers);
    connect(m_view ,&QView::exitGame,this,&ViewGraphique::closeGame);
    connect(m_view, &QView::reset,this,&ViewGraphique::reset);
    connect(m_view,&QView::skip,this,&ViewGraphique::skip);
}



void ViewGraphique::start(){


    m_view->showStart();
}

void ViewGraphique::update(const nvs::Subject * subject){

    auto game = dynamic_cast<const Game*>(subject);
    if(game){

        auto color = game->getCurrentPlayer().pawn().getColor();
        m_view->setCurrentPawn(color.compare("Red") == 0 ? enum_Color::Red : color.compare("Blue") == 0?  enum_Color::Blue : color.compare("Green") == 0 ? enum_Color::Green : enum_Color::Pink);
        m_view->setCurrentPlayer(QString::fromStdString(game->getCurrentPlayer().name()));
        m_view->setCurrentPlate(game->getCurrentPlate().getCard(),game->getCurrentPlate().type() .compare(" T ") == 0 ? enum_Plates::TPlate
                              : game->getCurrentPlate().type().compare(" L ") == 0 ? enum_Plates::LPlate
                                                                                : enum_Plates::IPlate,game->getCurrentPlate().getRotationLvl());
        m_view->setCurrentCard(QString::fromStdString(game->getCurrentPlayer().getCurrentCard()));
        m_view->updateBoard(game->getBoard(),game->getPlayers());
        m_view->setTurnState(game->getTurnstate() == Game::TurnStatus::NothingMoved ? "INSERTION" : game->getTurnstate() == Game::TurnStatus::PieceMoved ? "MOVE" : "CARD DISCOVERED");
        if(game->getTurnstate() == Game::TurnStatus::CardDiscovered){
            QMessageBox::information(this,"CARD DISCOVERED",QString::fromStdString("new Card : " +game->getCurrentPlayer().getCurrentCard()));
        }
        if(game->getGameState() == Game::GameStatus::finished){
            if(game->getWinner()){
                QMessageBox::information(this,"VICTORY",QString::fromStdString(game->getCurrentPlayer().name()));
            }

        }
        m_view->setInfo("");
    }
}
