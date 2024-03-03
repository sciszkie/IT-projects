#include <stdio.h>

int main() {

int c;
printf("Value of EOF is %d\n",EOF);
while ((c=getchar())!=EOF){
    putchar(c);
}
}