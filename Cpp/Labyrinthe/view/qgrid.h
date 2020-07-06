#ifndef QGRID_H
#define QGRID_H

#include <QWidget>
#include <QGridLayout>
#include <QPaintEvent>
#include <QPainter>
#include <QPixmap>
#include <QPen>
#include <QList>
#include <QLabel>
#include <QDebug>
#include "qpawn.h"
#include "qbox.h"
#include "model/board.h"
#include "model/player.h"


class QView;

enum class enum_Direction{Right,Down,Left,Up};

class QGrid : public QWidget
{
    Q_OBJECT


private:

    static const int M_NBBOX = 7;

    QRect m_rect;
    QGridLayout * m_grid;
    QList<QBox*> boxes;


    void paintEvent(QPaintEvent *event) override;
    void mousePressEvent(QMouseEvent *e) override;
//    void mouseReleaseEvent(QMouseEvent *e) override;

    void updateBoard(const Board &board, vector<Pawn> pawns);
    void updatePawn(vector<Pawn> pawns);

public:

    void update(const Board & board,vector<Player> players);

    explicit QGrid(QWidget *parent = nullptr,int size_Boxes = 100 );
    QGrid(Board & board,int box_size, QWidget *parent);


    inline void setColor(enum_Color color,int index){ boxes.at(index)->showOnePawn(color);}
    inline void setPlate(enum_Plates plate,int rotationLvl,int index){boxes.at(index)->setPlate(plate,rotationLvl);}
    inline void setTreasure(QString treasure,int index){boxes.at(index)->setTreasure(treasure);}


signals:

    void pressOnBox(int const &);
//    void releaseOnBox(int const &);



public slots:
};

#endif // QGRID_H
