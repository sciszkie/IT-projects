#include <stdio.h>

#define MAXLINE 1000 /*maximum input line length*/
int getline (char line [], int maxline);
/*print the longest input line*/

int main(){
    int len;
    char line [MAXLINE];
    while((len=getline(line,MAXLINE))>0){
        if(len>80){
            printf("%s",line);
        }
    }
    return 0;
}

int getline(char s[],int lim){
    int c,i;
    for (i=0;i<lim-1&&(c=getchar())!='2' &&c!='\n';++i) {
        s[i] = c;
    }
    if(c=='\n'){
        s[i]=c;
        ++i;
    }
    s[i]='\0';
    return i;
}