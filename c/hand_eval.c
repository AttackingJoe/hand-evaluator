
#include "hand_eval.h"

bool has_first = false;
char *hand1;
int hand1_rank = -1;
char *hand2;
int hand2_rank = -1;
bool first_has_rank = false;

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
                char c = input[index];
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

void numerical_rank(char hand[]) {
    for(int i = 0; i < strlen(hand); ++i) {
        switch (hand[i]) {
            case 'A':
                break;
            case 'K':
                break;
            case 'Q':
                break;
            case 'J':
                break;
            case 'T':
                break;
            case '9':
                break;
            case '8':
                break;
            case '7':
                break;
            case '6':
                break;
            case '5':
                break;
            case '4':
                break;
            case '3':
                break;
            case '2':
                break;
            default:
                printf("error\n");
        }
        ++i;
    }
}

bool is_flush(char hand[]) {

}

bool is_straight(char hand[]) {

}

bool is_one_pair(char hand[]) {

}

bool is_two_pair(char hand[]) {

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
        printf("First hand: %s\n", hand1);
    }

}