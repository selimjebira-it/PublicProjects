#include "qview.h"
#include <QSpinBox>

#define MIN_LETTER 3
#define MAX_LETTER 8
#define SPACE 30



QView::QView(QWidget * parent,QSize const & size):QMainWindow(parent),
    m_size{size}
{

    init();


}
/****************** INITIALISATION ********************************************/
/*APPEL EN CASCADE */
void QView::init(){
    setStyleSheet("QPushButton{font-size: 32px;font-family: Verdana;color: white; background-color: black;}"
                  "QLabel{font-size: 32px; font-family: Verdana ; color: green;}");

    initToolBar();//initialisation toolbar
}


void QView::initToolBar(){

    m_toolbar = new QToolBar(this);
    m_toolbar->setMovable(false);

    /*EXIT BTN*/
    QPushButton * btnExit = new QPushButton("EXIT");
    btnExit->setFlat(true);
    connect(btnExit,&QPushButton::clicked,this,&QView::handleExit);

    m_toolbar->addWidget(btnExit);

    /*NEW GAME BTN*/
    QPushButton * btnNewGame = new QPushButton("NEW GAME");
    connect(btnNewGame,&QPushButton::clicked,this,&QView::reset);
    btnNewGame->setFlat(true);

    m_toolbar->addWidget(btnNewGame);

    /*HELP BTN  + SEPARATOR*/
    QPushButton * btnHelp = new QPushButton("HELP");
    connect(btnHelp,&QPushButton::clicked,this,&QView::help);
    btnHelp->setFlat(true);

    m_toolbar->addWidget(btnHelp);
    m_toolbar->addSeparator();

    initInfoPlayer();//INIT INFO PLAYER BULLE

}

void QView::initInfoPlayer()
{
    /* INFO COURANTES CARD ,PAWN,SKIP BTN PLATE,PLAYER*/
    m_currentPlayer = new QPushButton("Player1");
    m_currentPlayer->setFlat(true);
    m_currentCard = new QPushButton("Herisson");
    m_currentCard->setFlat(true);
    m_skipButton = new QPushButton("SKIP");
    m_skipButton->setFlat(true);
    connect(m_skipButton,&QPushButton::clicked,this,&QView::skip);
    m_currentPlate = new QBox();
    m_currentPawn = new QPawn();
    connect(m_currentPlate,&QBox::clicked,this,&QView::handleClickBox);
    m_infoPlayer = new QWidget;
    QVBoxLayout * vb = new QVBoxLayout(m_infoPlayer);

    /*MISE EN FORME DANS UNE VBOX*/


    vb->addWidget(m_currentPlate,1);
    vb->addWidget(m_skipButton,1);
    vb->addWidget(new QLabel("Current Player"));
    vb->addWidget(m_currentPawn,1,Qt::AlignCenter);

    vb->addWidget(m_currentPlayer,1,Qt::AlignCenter);

    vb->addWidget(new QLabel("Current Card"),Qt::AlignCenter);

    vb->addWidget(m_currentCard,1,Qt::AlignCenter);

    /*AJOUT A LA TOOLBAR */
    m_toolbar->addWidget(m_infoPlayer);
    m_toolbar->hide();

    initHelp(); //HELPER

}

void QView::initHelp(){

    auto container = new QWidget(this);
    container->setLayout(new QVBoxLayout);

    auto label = new QLabel(this);
    label->setText("Labyrinthe helper :\n"
                   "\tClick -Board to insert-move\n"
                   "\tClick -Plate to rotate\n"
                   "\tClick -SKIP to skip\n"
                   "\tClick -HELP to show this\n"
                   "\tClick -EXIT to quit"
                   "\n\n To win , get all the treasures related to your cards and go back to your initial position");
    label->setWordWrap(true);
    auto helpbtn = new QPushButton("OK");
    helpbtn->setFlat(true);

    container->layout()->addWidget(label);
    container->layout()->addItem(new QSpacerItem(m_size.width(),m_size.height()));
    container->layout()->addWidget(helpbtn);


    m_helpWindow = createMainWindow(container);
    connect(helpbtn,SIGNAL(clicked()),m_helpWindow,SLOT(hide()));

    initWelcome();//FIRSTPAGE
}



void QView::initWelcome()
{


    QWidget * welcomeWidget = new QWidget;
    welcomeWidget->setLayout(new QVBoxLayout);

    QPushButton * startBtn = new QPushButton(QString("START"));
    startBtn->setFlat(true);
    m_nbPlayers= new QSpinBox(welcomeWidget);
    m_nbPlayers->setRange(2,4);
    m_nbCards = new QSpinBox(welcomeWidget);
    m_nbCards->setRange(1,12);

    welcomeWidget->layout()->addItem(new QSpacerItem(m_size.width()/2,m_size.height()/2));
    welcomeWidget->layout()->addWidget(new QLabel("Number of players :",this));
    welcomeWidget->layout()->addWidget(m_nbPlayers);
    welcomeWidget->layout()->addWidget(new QLabel("Number of Cards in deck :",this));
    welcomeWidget->layout()->addWidget(m_nbCards);
    welcomeWidget->layout()->addWidget(startBtn);
    m_welcomeWindow = createMainWindow(welcomeWidget);
    connect(startBtn,SIGNAL(clicked()),this,SLOT(registerPlayer()));
    m_welcomeWindow->show();
    //SECOND PAGE

}


void QView::initRegister(int nbJoueur)
{

    QWidget * registerWindow =new QWidget;
    registerWindow->setLayout(new QVBoxLayout);
    m_listLine.clear();
    for (auto i = 0 ; i < nbJoueur; i ++) {

        QWidget * container = new QWidget(registerWindow);
        container->setLayout(new QHBoxLayout);
        QLabel * lab = new QLabel(QString("Player %1").arg(i),registerWindow);
        QLineEdit * lineEdit = new QLineEdit(QString("Player%1").arg(i),registerWindow);
        m_listLine.push_back(lineEdit);
        container->layout()->addWidget(lineEdit);
        container->layout()->addWidget(lab);
        registerWindow->layout()->addWidget(container);
    }

    QPushButton * btnConfirm = new QPushButton("Confirm");
    btnConfirm->setFlat(true);
    connect(btnConfirm,SIGNAL(clicked()),this,SLOT(verify()));

    registerWindow->layout()->addWidget(btnConfirm);

    m_registerWindow = createMainWindow(registerWindow);
    initGameView();//THIRD PAGE


}


void QView::initGameView()
{
    /*CREATION CONTAINER VBOX*/
    QWidget * game = new QWidget;
    QVBoxLayout * vb = new QVBoxLayout(game);

    /*AJOUT DE GRID PRINCIPAL DANS CONTAINER*/
    Board  board;
    m_grid = new QGrid(board,7, this);
    connect(m_grid,SIGNAL(pressOnBox(int const &)),this,SLOT(handleClickGrid(int const &)));
    vb->addWidget(m_grid,1,Qt::AlignCenter);
    vb->addSpacing(SPACE);


    /*INSERTION LABEL ET PUSHBUTTON POUR LE TURNSTATE*/
    vb->addWidget(new QLabel("Turn State:"),1,Qt::AlignRight);
    vb->addWidget(m_Info = new QLabel("info"),1,Qt::AlignLeft);
    vb->addSpacing(SPACE);
    m_turnState = new QPushButton("INSERTION");
    m_turnState->setFlat(true);
    vb->addWidget(m_turnState,1,Qt::AlignRight);

    m_gameWindow = createMainWindow(game);
    m_toolbar->show();
    m_welcomeWindow->show(); //ON AFFICHE LA PREMIERE PAGE

}



/********END OF INITIALISATION ***************/




/**
 * @brief QView::createMainWindow method to insert content in a main window with predefined aspect
 * @param content the content to insert
 * @return a mainWindow with the content in it
 */
QMainWindow *QView::createMainWindow(QWidget * content)
{

    //create a QMainWindow and center the content in it
    QMainWindow * result = new QMainWindow(this);
    QWidget * container = new QWidget(result);
    QVBoxLayout * vb = new QVBoxLayout(container);
    vb->addWidget(content,1,Qt::AlignCenter);

    //adding the tool bar and settings comon
    result->addToolBar(Qt::RightToolBarArea,m_toolbar);
    result->setCentralWidget(container);
    result->setFixedSize(m_size);

    //adding backGround
    QPixmap backGround = QPixmap(":/divers/images/montagne.jpg")
            .scaled(result->size(),Qt::IgnoreAspectRatio,Qt::SmoothTransformation);
    QPalette palette;
    palette.setBrush(QPalette::Background,backGround);
    result->setPalette(palette);

    //adding icon
    result->setWindowIcon(QIcon(QPixmap(":/divers/images/gameIcon.PNG")));
    result->setWindowFlag(Qt::FramelessWindowHint);




    return result;
}
void QView::showStart(){hide();m_welcomeWindow->show();}
void QView::showGame(){m_registerWindow->hide();m_gameWindow->show();}

void QView::closeGame()
{
    hide();
    m_gameWindow->close();
}

void QView::verify()
{
    std::vector<std::string> res;
    for(auto & lines : m_listLine){
        std::string names = lines->text().toStdString();
        res.push_back(names);
    }

    emit verifyEntries(res);

        m_registerWindow->close();
        m_gameWindow->show();

}




















