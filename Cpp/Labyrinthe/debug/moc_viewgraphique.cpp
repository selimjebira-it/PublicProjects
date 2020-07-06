/****************************************************************************
** Meta object code from reading C++ file 'viewgraphique.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.12.2)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../view/viewgraphique.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'viewgraphique.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.12.2. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_ViewGraphique_t {
    QByteArrayData data[10];
    char stringdata0[93];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_ViewGraphique_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_ViewGraphique_t qt_meta_stringdata_ViewGraphique = {
    {
QT_MOC_LITERAL(0, 0, 13), // "ViewGraphique"
QT_MOC_LITERAL(1, 14, 4), // "play"
QT_MOC_LITERAL(2, 19, 0), // ""
QT_MOC_LITERAL(3, 20, 5), // "index"
QT_MOC_LITERAL(4, 26, 6), // "rotate"
QT_MOC_LITERAL(5, 33, 10), // "addPlayers"
QT_MOC_LITERAL(6, 44, 24), // "std::vector<std::string>"
QT_MOC_LITERAL(7, 69, 7), // "players"
QT_MOC_LITERAL(8, 77, 9), // "closeGame"
QT_MOC_LITERAL(9, 87, 5) // "reset"

    },
    "ViewGraphique\0play\0\0index\0rotate\0"
    "addPlayers\0std::vector<std::string>\0"
    "players\0closeGame\0reset"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_ViewGraphique[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    1,   39,    2, 0x08 /* Private */,
       4,    0,   42,    2, 0x08 /* Private */,
       5,    1,   43,    2, 0x08 /* Private */,
       8,    0,   46,    2, 0x08 /* Private */,
       9,    0,   47,    2, 0x08 /* Private */,

 // slots: parameters
    QMetaType::Void, QMetaType::Int,    3,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 6,    7,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void ViewGraphique::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<ViewGraphique *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->play((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 1: _t->rotate(); break;
        case 2: _t->addPlayers((*reinterpret_cast< std::vector<std::string>(*)>(_a[1]))); break;
        case 3: _t->closeGame(); break;
        case 4: _t->reset(); break;
        default: ;
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject ViewGraphique::staticMetaObject = { {
    &QMainWindow::staticMetaObject,
    qt_meta_stringdata_ViewGraphique.data,
    qt_meta_data_ViewGraphique,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *ViewGraphique::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *ViewGraphique::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_ViewGraphique.stringdata0))
        return static_cast<void*>(this);
    if (!strcmp(_clname, "nvs::Observer"))
        return static_cast< nvs::Observer*>(this);
    return QMainWindow::qt_metacast(_clname);
}

int ViewGraphique::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 5;
    }
    return _id;
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
