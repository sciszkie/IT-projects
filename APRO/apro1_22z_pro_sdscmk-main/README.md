# Projekt labiryntu
Pliki w pakiecie java zawierają klasy związane z GUI (Controller, Application) oraz klasy związane z algorytmami (wszystkie inne).\
Pliki w pakiecie resources zawierają pliki .fxml zawierające sceny związane z interfejsem oraz plik .png zawierający logo.\
W folderze templates znajdują się zapisane labirynty i rozwiązania.\
\
W trybie gry porusza się klawiszami WSAD.\
\
Po użyciu opcji zapisu na labiryncie o określonej wielkości, labirynt zapisuje się do pliku .txt o nazwie odpowiadającej danej wielkości w lokalizacji /templates/maze\
Zapisywany jest tylko ostatni labirynt każdej wielkości.\
W celu wczytania danego labiryntu jakiejś wielkości należy w fazie wyboru wybrać wielkość labiryntu, który chcemy wczytać.\
W lokalizacji /templates/solution zapisywane są rozwiązania labiryntów. Aby zapisać rozwiązanie, należy podczas gry wyświetlić rozwiązanie przyciskiem "Solve", a następnie użyć opcji "Save".\
\
W przypadku nieładowania się projektu po sklonowaniu z repozytorium, należy kliknąć prawym przyciskiem myszy na plik pom.xml, najechać na opcję "Maven" i zbudować ponownie projekt.\
\
Z racji, iż mieliśmy problemy z zapisem do repozytorium projektu z interfejsem graficznym, zamieszczamy nasze repozytorium testowe, zawierające część commitów:\
https://gitlab-stud.elka.pw.edu.pl/sdolinsk/projekttest.git