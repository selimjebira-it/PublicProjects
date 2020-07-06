#define DOCTEST_CONFIG_IMPLEMENT
#include "doctest.h"
#include "model/position.h"
#include "main.h"


int main(int argc, char** argv) {

    doctest::Context context;

    // !!! THIS IS JUST AN EXAMPLE SHOWING HOW DEFAULTS/OVERRIDES ARE SET !!!


    context.applyCommandLine(argc, argv);

    // overrides
    context.setOption("no-breaks", true);             // don't break in the debugger when assertions fail

    int res = context.run();

    if(context.shouldExit()) // important - query flags (and --exit) rely on the user doing this
        return res;          // propagate the result of the tests

    myMain(argc,argv);

    int client_stuff_return_code = 0;


    return res + client_stuff_return_code; // the result from doctest is propagated here as well
}

TEST_CASE("opperation pos"){

    Position pos1(1,1);
    Position pos2(2,2);
    Position pos3(3,3);
    Position pos4(3,3);
    CHECK(pos3 == pos4);
    CHECK(pos1 + pos2 == pos3);
}





