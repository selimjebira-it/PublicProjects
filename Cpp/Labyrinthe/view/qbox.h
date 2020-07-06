#ifndef QBOX_H
#define QBOX_H

#include <QWidget>
#include <QString>
#include <QGridLayout>
#include <QDebug>
#include <QPainter>
#include <QPaintEvent>
#include "qpawn.h"


enum class enum_Plates{
    IPlate,TPlate,LPlate
};



class QBox : public QWidget
{
    Q_OBJECT

private:

    /**
     * @brief m_rect shape of the Widget
     */
    QRect m_rect;

    /**
     * @brief m_grid Container of Pawns
     */
    QGridLayout * m_grid;

    /**
     * @brief m_listPawns list of Pawns
     */
    QList<QPawn*> m_listPawns;

    /**
     * @brief m_plate name of the plate contained
     */
    enum_Plates m_plate;

    int m_size;
    /**
     * @brief m_pixMap BackGround of the Widget
     */
    QPixmap m_pixMap;

    /**
     * @brief m_size size of the box
     */


    /**
     * @brief m_treasure treasure contained
     */
    QString m_treasure;

    /**
     * @brief getImageFromPlate method to get the right image for the plate
     * @param plate the plate
     * @return pixmap corresponding to the plate
     */
    QPixmap getImageFromPlate(enum_Plates  const & plate);

    /**
     * @brief setQPawn add QPawn to the list and insert it in the grid
     * @param pawn the pawn to insert
     */
    void setQPawn(QPawn * pawn);

    /**
     * @brief initGridLayout init the grid
     */
    void initGridLayout();

    void mousePressEvent(QMouseEvent *e) override;
    void paintEvent(QPaintEvent *event) override;


public:

    explicit QBox( QWidget *parent = nullptr, enum_Plates = enum_Plates::TPlate, QString treasure ="",int box_size = 100);

    /*Setter of some Attributes*/
    inline void setSize(int const & size){m_size = size;}
    inline void setTreasure(QString const & treasure){m_treasure = treasure; repaint(rect());}
    inline void setPlate(enum_Plates const & plate,int nb_rotations = 0){m_pixMap = getImageFromPlate(m_plate = plate);rotate(nb_rotations);repaint(rect());}


    /*Getter of some Attributes*/
    inline enum_Plates const & plate(){return m_plate;}
    inline QString const & treasure(){return m_treasure;}


    /* Method to act on pawns*/
    void hideAllPawns();
    void showOnePawn(enum_Color const & color);
    void hideOnePawn(enum_Color const & color);



signals:
    void clicked();

public slots:
    void rotate(int = 1);


};

#endif // QBOX_H
