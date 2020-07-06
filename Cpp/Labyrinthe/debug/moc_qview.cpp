/****************************************************************************
** Meta object code from reading C++ file 'qview.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.12.2)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../view/qview.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'qview.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.12.2. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_QView_t {
    QByteArrayData data[15];
    char stringdata0[155];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_QView_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_QView_t qt_meta_stringdata_QView = {
    {
QT_MOC_LITERAL(0, 0, 5), // "QView"
QT_MOC_LITERAL(1, 6, 11), // "boxSelected"
QT_MOC_LITERAL(2, 18, 0), // ""
QT_MOC_LITERAL(3, 19, 13), // "verifyEntries"
QT_MOC_LITERAL(4, 33, 24), // "std::vector<std::string>"
QT_MOC_LITERAL(5, 58, 6), // "rotate"
QT_MOC_LITERAL(6, 65, 8), // "exitGame"
QT_MOC_LITERAL(7, 74, 5), // "reset"
QT_MOC_LITERAL(8, 80, 14), // "registerPlayer"
QT_MOC_LITERAL(9, 95, 4), // "help"
QT_MOC_LITERAL(10, 100, 10), // "handleExit"
QT_MOC_LITERAL(11, 111, 15), // "handleClickGrid"
QT_MOC_LITERAL(12, 127, 5), // "index"
QT_MOC_LITERAL(13, 133, 14), // "handleClickBox"
QT_MOC_LITERAL(14, 148, 6) // "verify"

    },
    "QView\0boxSelected\0\0verifyEntries\0"
    "std::vector<std::string>\0rotate\0"
    "exitGame\0reset\0registerPlayer\0help\0"
    "handleExit\0handleClickGrid\0index\0"
    "handleClickBox\0verify"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_QView[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
      11,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       5,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    1,   69,    2, 0x06 /* Public */,
       3,    1,   72,    2, 0x06 /* Public */,
       5,    0,   75,    2, 0x06 /* Public */,
       6,    0,   76,    2, 0x06 /* Public */,
       7,    0,   77,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       8,    0,   78,    2, 0x08 /* Private */,
       9,    0,   79,    2, 0x08 /* Private */,
      10,    0,   80,    2, 0x08 /* Private */,
      11,    1,   81,    2, 0x08 /* Private */,
      13,    0,   84,    2, 0x08 /* Private */,
      14,    0,   85,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void, QMetaType::Int,    2,
    QMetaType::Void, 0x80000000 | 4,    2,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::Int,   12,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void QView::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<QView *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->boxSelected((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 1: _t->verifyEntries((*reinterpret_cast< std::vector<std::string>(*)>(_a[1]))); break;
        case 2: _t->rotate(); break;
        case 3: _t->exitGame(); break;
        case 4: _t->reset(); break;
        case 5: _t->registerPlayer(); break;
        case 6: _t->help(); break;
        case 7: _t->handleExit(); break;
        case 8: _t->handleClickGrid((*reinterpret_cast< const int(*)>(_a[1]))); break;
        case 9: _t->handleClickBox(); break;
        case 10: _t->verify(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (QView::*)(int );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&QView::boxSelected)) {
                *result = 0;
                return;
            }
        }
        {
            using _t = void (QView::*)(std::vector<std::string> );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&QView::verifyEntries)) {
                *result = 1;
                return;
            }
        }
        {
            using _t = void (QView::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&QView::rotate)) {
                *result = 2;
                return;
            }
        }
        {
            using _t = void (QView::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&QView::exitGame)) {
                *result = 3;
                return;
            }
        }
        {
            using _t = void (QView::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&QView::reset)) {
                *result = 4;
                return;
            }
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject QView::staticMetaObject = { {
    &QMainWindow::staticMetaObject,
    qt_meta_stringdata_QView.data,
    qt_meta_data_QView,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *QView::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *QView::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_QView.stringdata0))
        return static_cast<void*>(this);
    return QMainWindow::qt_metacast(_clname);
}

int QView::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 11)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 11;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 11)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 11;
    }
    return _id;
}

// SIGNAL 0
void QView::boxSelected(int _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}

// SIGNAL 1
void QView::verifyEntries(std::vector<std::string> _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 1, _a);
}

// SIGNAL 2
void QView::rotate()
{
    QMetaObject::activate(this, &staticMetaObject, 2, nullptr);
}

// SIGNAL 3
void QView::exitGame()
{
    QMetaObject::activate(this, &staticMetaObject, 3, nullptr);
}

// SIGNAL 4
void QView::reset()
{
    QMetaObject::activate(this, &staticMetaObject, 4, nullptr);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
