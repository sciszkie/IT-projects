#include <stdio.h>

/*1.5.4 Count Words with modification from 1.5.4.1-12 (every word in new line)*/

#define IN 1 /*inside a word*/
#define OUT 0 /*outside a word*/

int main() {

    int c, nl, nw, nc, state;

    state = OUT;

    nw=nc=0;
    nl=1;
    while ((c=getchar())!=EOF){
        putchar(c);
        ++nc;
        if(c=='\n'){
            ++nl;
        }
        if(c=='\n' || c=='\t' || c==' '){
            if(state==IN){
                state=OUT;
                putchar('\n');
            }
        }
        else if(state==OUT){
            ++nw;
            state=IN;
        }
    }
    printf("%d %d %d \n",nl, nw,nc);
}