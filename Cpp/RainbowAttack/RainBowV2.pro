TEMPLATE = app
CONFIG += console c++1x
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += \
        main.cpp \
    generatetable.cpp \
    generatepwd.cpp \
    sha256.cpp \
    crackpwd.cpp

HEADERS += \
    generatetable.h \
    random.hpp \
    generatepwd.h \
    sha256.h \
    crackpwd.h
