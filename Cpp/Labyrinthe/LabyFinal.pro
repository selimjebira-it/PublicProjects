
CONFIG += console c++17
CONFIG -= app_bundle
CONFIG += gui core


greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

DEFINES += QT_DEPRECATED_WARNINGS

SOURCES +=  \
    controler/controler.cpp \
    main.cpp \
    model/board.cpp \
    model/direction.cpp \
    model/game.cpp \
    model/plate.cpp \
    model/player.cpp \
    model/position.cpp \
    model/util.cpp \
    observer/subject.cpp \
    tests/doctest.cpp \
    view/qbox.cpp \
    view/qgrid.cpp \
    view/qpawn.cpp \
    view/qview.cpp \
    view/viewConsole.cpp \
    view/viewgraphique.cpp



HEADERS +=  \
    controler/controler.h \
    main.h \
    model/board.h \
    model/card.h \
    model/color.h \
    model/direction.h \
    model/game.h \
    model/iplate.h \
    model/lplate.h \
    model/pawn.h \
    model/plate.h \
    model/player.h \
    model/position.h \
    model/random.hpp \
    model/tplate.h \
    model/util.h \
    observer/observer.h \
    observer/subject.h \
    tests/doctest.h \
    view/qbox.h \
    view/qgrid.h \
    view/qpawn.h \
    view/qview.h \
    view/viewConsole.h \
    view/viewgraphique.h

RESOURCES += \
    ressources.qrc


