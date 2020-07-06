#ifndef QPAWN_H
#define QPAWN_H

#include <QWidget>
#include <QLabel>



enum class enum_Color
{
    Red, Blue, Pink, Green
};

class QPawn : public QLabel{
    Q_OBJECT

private:

    enum_Color m_color;
    QPixmap m_originalPixmap;
    int m_x, m_y;

//    void resizeEvent(QResizeEvent *event);
    QPixmap getColorFromenum(enum_Color color);

public:

    QPawn(enum_Color color=enum_Color::Red, QWidget * parent = nullptr);
    inline int x(){return m_x;}
    inline int y(){return m_y;}
    inline enum_Color color(){return m_color;}
    inline void setColor(enum_Color color){m_color = color;setPixmap(m_originalPixmap =getColorFromenum(color));}
    inline bool operator==(QPawn const &  other){
        return m_color == other.m_color;
    }
};
#endif // QPAWN_H
