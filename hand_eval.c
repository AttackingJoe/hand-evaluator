
#include "hand_eval.h"

bool has_first = false;
char *hand1;
char *hand2;

/*
 * Checks to make sure that each hand is in its proper format
 */
bool is_proper_format(char input[]) {
    int count = 0;

    for(int index = 0; index < strlen(input); ++index) {
        if(!isspace(input[index])) { // don't care about whitespace
            // Format should be (2-9) or (T, J, Q, K, A) non-case sens followed by (d, s, c, h)
            if(isdigit(input[index])) {
                int card = atoi(&input[index]);
                if(card < 2)
                    return false;
                char suit = (char)input[index + 1];
                if(suit == 'd' || suit == 'D' || suit == 's' || suit == 'S' || suit == 'c' || suit == 'C' || suit == 'h' || suit == 'H') {
                    // it is an appropriate pair
                    count++;
                }

            } else { // it isn't an int, so it should be one of five chars
                char c = (char)input[index];
                if(c == 'T' || c == 't' || c == 'J' || c == 'j' || c == 'Q' || c == 'q' || c == 'K' || c == 'k' || c == 'A' || c == 'a') {

                    if(!isdigit(input[index + 1])) {
                        char suit = (char)input[index + 1];

                        if(suit == 'd' || suit == 'D' || suit == 's' || suit == 'S' || suit == 'c' || suit == 'C' || suit == 'h' || suit == 'H') {
                            // it is an appropriate pair
                            count++;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false; // Not an int 2-9 or an appropriate Letter
                }
            }

            ++index; // working in pairs
        }
    }
    if(count != 5) {
        return false;
    }
    return true;
}
/*
 * Simply store each hand to compare
 */
void store_string(char *input) {
    if(has_first) {
        hand2 = input;
        has_first = !has_first;
    }
    else {
        hand1 = input;
        has_first = !has_first;
    }

}