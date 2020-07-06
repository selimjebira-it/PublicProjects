#ifndef QVIEW_H
#define QVIEW_H

#include "qgrid.h"
#include "model/player.h"

#include <QMainWindow>
#include <QPushButton>
#include <QPainter>
#include <QPaintEvent>
#include <QVBoxLayout>
#include <QLabel>
#include <QLineEdit>
#include <QMessageBox>
#include <QDebug>
#include <QDesktopWidget>
#include <QApplication>
#include <QToolBar>
#include <QPalette>
#include <QImage>
#include <QSpacerItem>
#include <QDialogButtonBox>
#include <QDebug>
#include <algorithm>
#include <QSpinBox>




class QView:public QMainWindow
{
    Q_OBJECT

    QSize m_size ;
    QBox * m_currentPlate;
    QPushButton * m_currentPlayer;
    QPushButton * m_currentCard;
    QPushButton * m_turnState;
    QPushButton * m_skipButton;
    QLabel * m_Info;
    QPawn * m_currentPawn;
    QToolBar * m_toolbar;
    QWidget * m_infoPlayer;
    QGrid * m_grid;
    QList<QLineEdit*> m_listLine;
    QMainWindow * m_helpWindow;
    QMainWindow * m_welcomeWindow;
    QMainWindow * m_registerWindow;
    QMainWindow * m_gameWindow;
    QSpinBox * m_nbPlayers;
    QSpinBox * m_nbCards;


private slots:


    inline void registerPlayer(){initRegister(m_nbPlayers->value());m_welcomeWindow->hide();m_registerWindow->show();}
    inline void help(){m_helpWindow->show();}
    inline void handleExit(){/*qDebug() << "exit" ;*/emit exitGame();}
    inline void handleClickGrid(int const & index){/*qDebug() << "Box selected at " << index;*/emit boxSelected(index);}
    inline void handleClickBox(){/*qDebug()<< "box alone";*/emit rotate();}
    void verify();

signals:

    void boxSelected(int);
    void verifyEntries(std::vector<std::string>);
    void rotate();
    void exitGame();
    void reset();
    void skip();


private:

    void init();
    void initToolBar();
    void initInfoPlayer();
    void initHelp();
    void initWelcome();
    void initRegister(int nbJoueur);
    void initGameView();



    QMainWindow * createMainWindow(QWidget *content);




public:

    inline void updateBoard(const Board & board,vector<Player> players){m_grid->update(board,players);}
    inline void setCurrentPlayer(QString currentplayer){m_currentPlayer->setText(currentplayer);}
    inline void setCurrentPlate(std::string treasure,enum_Plates plateType,int rotation){m_currentPlate->setTreasure(QString::fromStdString(treasure));m_currentPlate->setPlate(plateType,rotation);}
    inline void setCurrentCard(QString treasure){m_currentCard->setText(treasure);}
    inline void setTurnState(QString turnState){m_turnState->setText(turnState);}
    inline void setCurrentPawn(enum_Color color){m_currentPawn->setColor(color);}
    inline void setInfo(QString info){m_Info->setText(info);}
    inline int getNbCard(){return m_nbCards == nullptr? 12: m_nbCards->value();}
    void showGame();
    void closeGame();
    QView(QWidget * parent = nullptr, const QSize & = QSize(1000,800));
    void showStart();
};

#endif // QVIEW_H
