#ifndef VIEWGRAPHIQUE_H
#define VIEWGRAPHIQUE_H


#include "observer/observer.h"
#include "observer/subject.h"
#include "controler/controler.h"
#include "qview.h"


class ViewGraphique : public QMainWindow,public nvs::Observer
{
    Q_OBJECT


private:

    Controler m_controler;
    QView * m_view;

    void init();
private slots:

    inline void play(int index){if(!m_controler.play(index))m_view->setInfo("you can't do that.");}
    inline void rotate(){m_controler.rotate();}
    inline void addPlayers(std::vector<std::string> players){if(m_controler.addPlayers(players)){m_controler.start(m_view->getNbCard());m_view->showGame();}}
    inline void closeGame(){m_controler.end();this->close();}
    inline void reset(){m_controler.reset();m_view->closeGame();m_view->showStart();}
    inline void skip(){if(!m_controler.skip())m_view->setInfo("You have to insert a piece fisrt");}

public:

    ViewGraphique(Controler const & controler,QMainWindow * parent = nullptr);
    ~ViewGraphique() override = default;

    void start();
    void update(const nvs::Subject * ) override;


};

#endif // VIEWGRAPHIQUE_H
