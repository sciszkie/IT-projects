#include <stdio.h>
/*exercise 1.5.1-9 - ccopying input to output, but replacing string of blanks by one blank*/
int main() {
    int c, lc;
    while ((c=getchar())!=EOF){
        if(c==' '){
            if(lc!=' '){
                putchar(c);
                lc=c;
            }
        }
        else{
            putchar(c);
            lc=c;
        }
    }
}