#include <stdio.h>

int main() {
    int c,nl;
    nl=0;
    while ((c=getchar())!=EOF){
        if(c=='\n'){
            ++nl;
        }
    }
    printf("%1d\n",nl);
}