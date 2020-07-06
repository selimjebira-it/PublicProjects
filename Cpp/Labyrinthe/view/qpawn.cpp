#include "qpawn.h"


QPawn::QPawn(enum_Color color, QWidget *parent):
    QLabel{parent}, m_color{color}, m_originalPixmap{getColorFromenum(color)}
{
    setPixmap(m_originalPixmap);
    setAttribute(Qt::WA_TransparentForMouseEvents);
}

//void QPawn::resizeEvent(QResizeEvent * event)
//{
//    setPixmap(m_originalPixmap.scaled(this->size().width()-1,this->size().height()-1 ,Qt::AspectRatioMode::IgnoreAspectRatio));
//    repaint();
//}

QPixmap QPawn::getColorFromenum(enum_Color color)
{

    switch (color) {

    case enum_Color::Red:
        m_x = 0;
        m_y = 0;
       return QPixmap (":/pawn/images/piontRouge.png");

    case enum_Color::Blue:
        m_x = 2;
        m_y = 0;
        return QPixmap(":/pawn/images/piontBleu.png");
    case enum_Color::Pink:
        m_x = 2;
        m_y = 2;
        return QPixmap(":/pawn/images/piontRose.png");
    case enum_Color::Green:
        m_x = 0;
        m_y = 2;
        return QPixmap(":/pawn/images/piontVert.png");
    default:throw;
    }
}
