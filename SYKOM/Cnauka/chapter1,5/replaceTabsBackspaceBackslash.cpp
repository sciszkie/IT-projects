#include <stdio.h>
/*exercise 1.5.1-10 - replacing tabs, backslash and backspaces with \t, \\ and \b*/
int main() {
    int c, lc;
    while ((c=getchar())!=EOF){
        if(c=='\t'){
            putchar('\\');
            putchar('t');
        }
        if(c=='\b'){
            putchar('\\');
            putchar('b');
        }
        if(c=='\\'){
            putchar('\\');
            putchar('\\');
        }
        if(c!='\t' && c!='\b'&& c!='\\'){
            putchar(c);
        }
    }
}