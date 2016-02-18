#include "hand_eval.h"


int main(int argc, char *argv[]) {


    char input[256];
    scanf("%s", input);

    if(is_proper_format(input)) {
        printf("CORRECT!\n");
        store_string(input);
    } else {
        printf("INCORRECT\n");
    }

    printf("You typed %s.\n", input);

    return 0;
}