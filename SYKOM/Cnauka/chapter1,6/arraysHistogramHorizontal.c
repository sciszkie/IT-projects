#include <stdio.h>

/*1.5.4 Count Words with modification from 1.6.4.1-13 horizontal histogram of digits occurance */

#define IN 1 /*inside a word*/
#define OUT 0 /*outside a word*/

int main() {

    int c,state;

    state = OUT;

    while ((c=getchar())!=EOF){
        if(c=='\n' || c=='\t' || c==' '){
            if(state==IN){
                state=OUT;
                putchar('\n');
            }
        }
        else if(state==IN){
            putchar('*');
        }
        else if(state==OUT){
            state=IN;
            putchar('*');
        }
    }
}