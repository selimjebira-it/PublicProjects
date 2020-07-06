#include "qgrid.h"
#include "model/plate.h"


QGrid::QGrid(QWidget *parent,int box_size) :
    QWidget(parent),m_grid{new QGridLayout(this)}
{

    m_rect = QRect(QPoint(0,0),QSize((M_NBBOX*box_size) + 10,(M_NBBOX*box_size)+10));
    setGeometry(m_rect);
    for(auto i = 0; i < M_NBBOX ; i ++){
        for(auto j = 0; j < M_NBBOX; j ++){
            QBox * box = new QBox(this);
            boxes.push_back(box);
            box->setTreasure("Hello");
            m_grid->addWidget(box,i,j);
        }
    }

}

QGrid::QGrid(Board & board,int box_size, QWidget *parent):
    QWidget(parent),m_grid{new QGridLayout(this)}
{

    m_rect = QRect(QPoint(0,0),QSize((M_LABY_SIZE*box_size) + 10,(M_LABY_SIZE*box_size)+10));
    setGeometry(m_rect);
    for(auto y = 0; y < M_LABY_SIZE ; y ++){
        for(auto x = 0; x < M_LABY_SIZE; x ++){
            QBox * box = new QBox(this,board.getBoard()[x][y]->type().compare(" T ") == 0? enum_Plates::TPlate : board.getBoard()[x][y]->type().compare(" L ") == 0? enum_Plates::LPlate : enum_Plates::IPlate);
            box->rotate(board.getBoard()[x][y]->getRotationLvl());
            boxes.push_back(box);
            box->setTreasure(QString::fromStdString(board.getBoard()[x][y]->getCard()));
            m_grid->addWidget(box,y,x);
        }
    }
}

/***********************PRIVATE**************************/

void QGrid::paintEvent(QPaintEvent *event)
{
    QRect rect = event->rect();
    QPainter painter(this);
    painter.setRenderHint(QPainter::Antialiasing);
    painter.setPen(Qt::black);
    painter.drawRect(rect);
}

void QGrid::mousePressEvent(QMouseEvent * e)
{


    if (QBox * const box = qobject_cast<QBox*>(childAt(e->pos()))) {
        const int index = m_grid->indexOf(box);
        if (index >= 0){
            emit pressOnBox(index);
            e->accept();
        }else{e->ignore();}
    }else{e->ignore();}
}

//void QGrid::mouseReleaseEvent(QMouseEvent *e){


//    if (QBox * const box = qobject_cast<QBox*>(childAt(e->pos()))) {
//        const int index = m_grid->indexOf(box);
//        if (index >= 0){
//            qDebug() << "release on Board" << index;
//            emit releaseOnBox(index);
//        }else{e->ignore();}
//    }else{e->ignore();}

//}


void QGrid::updateBoard(Board  const &  board, vector<Pawn> pawns)
{

    int indexBoxes = 0;
     for(auto y = 0; y < M_NBBOX ; y ++){
         for(auto x = 0; x < M_NBBOX; x ++){
             boxes[indexBoxes]->hideAllPawns();
             boxes[indexBoxes]->setPlate(board.getPlate(Position(x,y)).type().compare(" T ") == 0?
                                             enum_Plates::TPlate : board.getPlate(Position(x,y)).type().compare(" L ") == 0?
                                                 enum_Plates::LPlate : enum_Plates::IPlate,
                                         board.getPlate(Position(x,y)).getRotationLvl());
             boxes[indexBoxes]->setTreasure(QString::fromStdString(board.getPlate(Position(x,y)).getCard()));
             indexBoxes++;
         }
     }

    updatePawn(pawns);
}

void QGrid::updatePawn(vector<Pawn> pawns){

    for(auto &i : pawns){
        auto pos = i.position();
        auto index = pos.y() * M_NBBOX + pos.x();
        boxes[index]->showOnePawn(i.getColor().compare("Red") == 0 ? enum_Color::Red
                                                                   :i.getColor().compare("Green") == 0 ? enum_Color::Green
                                                                                                       :i.getColor().compare("Pink") == 0 ? enum_Color::Pink : enum_Color::Blue);
    }
}

void QGrid::update(const Board & board, vector<Player> players){

    vector<Pawn> pawns = vector<Pawn>();
    for(auto &pl :players){
        pawns.push_back(pl.pawn());

    }
    updateBoard(board,pawns);

}

/**********************PUBLIC**************************/



