#include "qbox.h"



/**
 * @brief Box::Box Constructor
 * @param size size of the box
 * @param plateType type of the plate
 * @param parent the parent of the widget
 */
QBox::QBox(QWidget *parent,enum_Plates plateType,QString treasure ,int size) : QWidget(parent),m_grid{new QGridLayout(this)}
  ,m_plate{plateType},m_size{size},m_pixMap{getImageFromPlate(m_plate)},m_treasure{treasure}

{

    m_rect.setWidth(size);
    m_rect.setHeight(size);
    setGeometry(m_rect);
    initGridLayout();


}



/***********************************      Private       **************************/
/**
 * @brief Box::initGridLayout method to init the the grid layout
 */
void QBox::initGridLayout(){

    for(auto i = 0 ; i < 3 ; i ++){
        m_grid->setRowStretch(i,1);
        m_grid->setColumnStretch(i,1);
        m_grid->setRowMinimumHeight(i,this->height()/3);
        m_grid->setColumnMinimumWidth(i,this->height()/3);
    }
    setQPawn(new QPawn(enum_Color::Red,this));
    setQPawn(new QPawn(enum_Color::Blue,this));
    setQPawn(new QPawn(enum_Color::Green,this));
    setQPawn(new QPawn(enum_Color::Pink,this));
    hideAllPawns();
}

void QBox::mousePressEvent(QMouseEvent *e)
{
    emit clicked();
    e->ignore();

}

void QBox::paintEvent(QPaintEvent *event)
{
    QRect rect = event->rect();
    QPainter painter(this);
    painter.drawPixmap(rect,m_pixMap);
    painter.drawRect(rect);
    QPen pen;
    pen.setCosmetic(true);
    painter.setPen(pen);
    painter.drawText(rect, Qt::AlignCenter | Qt::TextWordWrap ,m_treasure);

}

QPixmap QBox::getImageFromPlate(enum_Plates const & plate)
{
    switch (plate) {

    case enum_Plates::IPlate:
        return  QPixmap(":/plate/images/iPlate.png");

    case enum_Plates::TPlate:
        return  QPixmap(":/plate/images/tPlate.png");

    case enum_Plates::LPlate:
        return QPixmap(":/plate/images/lplate.png");
    }
}

void QBox::setQPawn(QPawn * pawn){

    pawn->setPixmap(pawn->pixmap()->scaled(this->size()/4));
    m_grid->addWidget(pawn,pawn->y(),pawn->x());
    m_listPawns.append(pawn);

}

/********************* Public ************************************/

void QBox::rotate(int nb_rotations)
{
    for(auto i =0 ; i < nb_rotations; i++){
        m_pixMap = m_pixMap.transformed(QTransform ().rotate(90));
    }
    this->repaint(m_rect = this->rect());
}


void QBox::hideAllPawns()
{
    for(auto &pawn : m_listPawns){
        pawn->hide();
    }
}

void QBox::showOnePawn(const enum_Color &color){

    for(auto &pawn : m_listPawns){
        if(pawn->color() == color)
            pawn->show();
    }
}

void QBox::hideOnePawn(const enum_Color &color){
    for(auto &pawn : m_listPawns){
        if(pawn->color() == color)
            pawn->hide();
    }
}

