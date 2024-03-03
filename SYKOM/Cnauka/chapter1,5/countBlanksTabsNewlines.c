#include <stdio.h>
/*exercise 1.5.1-8 - counting newlines, tabs and blanks in input*/
int main() {
    int c,nl,tabs,blanks;
    nl=0;
    blanks=0;
    tabs=0;
    while ((c=getchar())!=EOF){
        if(c=='\n'){
            ++nl;
        }
        else if(c=='\t'){
            ++tabs;
        }
        else if(c==' '){
            ++blanks;
        }
    }
    printf("New lines: %d\t tabs: %d\t blanks: %d\n",nl, tabs,blanks);
}