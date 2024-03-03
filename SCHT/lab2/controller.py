import networkx as nx
from collections import defaultdict
import shutil
import subprocess
from networkx.exception import NetworkXNoPath
import matplotlib.pyplot as plt

class Controller:

    #konstruktor klasy Controller, przyjmuje plik z danymi o grafie  i zapisuje dane do zmiennych

    def __init__(self, filename):
        self.filename = filename
        self.graph, self.hostsports, self.edges = self.read_graph_from_file()

    #metoda czytająca dane z pliku, słownik edges - nieuzupełninone zostają ostatnie trzy komórki (zawierać będą informacje o przeciązęniu łączy)
    def read_graph_from_file(self):
        graph = nx.Graph()
        hostsports = []
        udp_bw = 0
        how_many_tcp_streams = 0
        tcp_streams = []
        edges = defaultdict(list)
        with open(self.filename, 'r') as file:
            for line in file:
                if "%" in line:
                    hostsports.append(line[3])
                else:
                    start, end, weight, bandwidth, portS, portD = map(float, line.split())
                    graph.add_edge(int(start), int(end), weight=weight)
                    edges[start].append((end, weight, bandwidth, portS, portD,udp_bw,how_many_tcp_streams,tcp_streams))
                    edges[end].append((start, weight, bandwidth, portD, portS,udp_bw,how_many_tcp_streams,tcp_streams))
        return graph, hostsports, edges

    #metoda kopiująca zawratosć pliku do innego pliku
    def copy_and_save(self, source_filename, destination_filename):
        try:
            shutil.copyfile(source_filename, destination_filename)
        except FileNotFoundError:
            print(f"Plik {source_filename} nie istnieje.")

    #metoda wywoływana po stwierdzeniu, że żądany strumień zmieści się w wyliczonej ścieżce (metoda aktualizuje bieżący stan sieci)
    def decrease_bw(self, path, stream_bw, type):
        for j in range(0, len(path) - 1):
            for index in range(0, len(self.edges[path[j]])):
                if self.edges[path[j]][index][0] == path[j + 1]:
                    edge = self.edges[path[j]][index]
                    if type == "udp":

                        #dodanie do komórki 5 wielkości przepustowości zajmowanej przez udp, aktualizacja słownika

                        updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5] + stream_bw, edge[6],
                                        edge[7])
                        self.edges[path[j]][index] = updated_edge
                    elif type == "tcp":

                        #zwiększenie liczby strumieni tcp na łączu, zapisanie w tablicy przepustowości żadanych przez klienta

                        if not edge[7]:
                            updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6] + 1,[stream_bw])
                        else:
                            updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6] + 1,edge[7]+[stream_bw])
                        self.edges[path[j]][index] = updated_edge

            #musimy zaktualizować słownik i łącza w dwie strony (łącze a-b i b-a)

            for index in range(0, len(self.edges[path[j + 1]])):
                if self.edges[path[j + 1]][index][0] == path[j]:
                    edge = self.edges[path[j + 1]][index]
                    if type == "udp":
                        updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5] + stream_bw, edge[6], edge[7])
                        self.edges[path[j + 1]][index] = updated_edge
                    elif type == "tcp":
                        if not edge[7]:
                            updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6] + 1,[stream_bw])
                        else:
                            updated_edge = (edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6] + 1,edge[7]+[stream_bw])
                        self.edges[path[j + 1]][index] = updated_edge
    #getter łączy
    def get_edge(self, start, end):
        for index in range(len(self.edges[start])):
            if self.edges[start][index][0] == end:
                return self.edges[start][index]

    #metoda rysująca graf
    def draw(self):
        pos = nx.spring_layout(self.graph)
        nx.draw(self.graph, pos, with_labels=True, node_size=500, node_color='green')
        labels = nx.get_edge_attributes(self.graph, 'weight')
        nx.draw_networkx_edge_labels(self.graph, pos, edge_labels=labels)
        plt.show()

    # metoda sprawdzająca, czy wyliczona ścieżka jest w stanie obsłużyć nasze żądanie
    def left_bandwidth_check(self, path, stream_bw, is_possible):
        is_possible=True
        for i in range(0,len(path)-1):
            is_edge_free=True
            edge = self.get_edge(path[i], path[i + 1])
            bw=edge[2]
            udp_bw=edge[5]
            how_many_tcp_streams=edge[6]
            tcp_bw_table=edge[7]
            possible_bw=(bw-udp_bw)/(len(tcp_bw_table)+1)
            if(possible_bw<stream_bw):
                is_possible=False
                is_edge_free=False
            for index in range(0,len(tcp_bw_table)):
                    if(tcp_bw_table[index]>possible_bw):
                        is_possible=False
                        is_edge_free=False
            if(is_edge_free==False):
                self.graph.remove_edge(path[i], path[i+1])
        return (self.graph, is_possible)

    # meotda przeprowadzająca algorytm wyszukiwania optymalnej ścieżki
    def finding_path(self, start, end, stream_bw,type):
        is_possible = False
        copy_graph = self.graph.copy()

        while (is_possible==False):
            try:
                is_possible = True
                path = nx.shortest_path(self.graph, start, end, weight='weight')
            except NetworkXNoPath:
                print("Brak mozliwosci realizacji twojego zadania, sprobuj pozniej.")
                path=None
                path_length=0
                self.graph = copy_graph
                return (path, path_length)
            self.graph, is_possible = self.left_bandwidth_check(path, stream_bw, is_possible)
        self.decrease_bw(path, stream_bw,type)
        path_length = nx.shortest_path_length(self.graph, start, end, weight='weight')
        self.graph = copy_graph
        return path, path_length

    # metoda, w której nastepuje interakcja klienta z aplikacją (tutaj przyjmowane są parametry, następnie są sprawdzane),a po wyliczeniu
    # klient otrzymuje wyniki
    def input_panel(self):
        odp = input("Czy chcesz dodać nowe połączenie? Podaj 't' lub 'n': ")
        how_many_streams = 0
        stream_bw=0
        while odp != "n":
            if odp == "t":

                while True:
                    try:
                        start_vertex = int(input("Podaj pierwszy wierzchołek: "))
                        break
                    except ValueError:
                        print("Podano nieprawidłowy znak. Wprowadź liczbę.")

                while True:
                    try:
                        end_vertex = int(input("Podaj ostatni wierzchołek: "))
                        break
                    except ValueError:
                        print("Podano nieprawidłowy znak. Wprowadź liczbę.")

                while (start_vertex == end_vertex):
                    end_vertex = int(
                        input("Wierzchołek docelowy musi być inny niż startowy. Podaj ostatni wierzchołek: "))
                type = str(input("Podaj rodzaj protokołu transportowego, którego chcesz użyć (tcp, udp, eth): "))
                while True:
                    if type in ["tcp", "udp"]:
                        while True:
                            try:
                                stream_bw = int(input("Podaj oczekiwaną szybkość przesyłu: "))
                                break
                            except ValueError:
                                print("Podano nieprawidłowy znak. Wprowadź liczbę.")
                    elif type == "eth":
                        stream_bw = 0
                    else:
                        print("Błąd")
                        type = str(input("Podaj rodzaj protokołu transportowego, którego chcesz użyć (tcp, udp, eth): "))
                        if type in ["tcp", "udp"]:
                            stream_bw = int(input("Podaj oczekiwana szybkosc przesyłu: "))
                    if type in ["tcp", "udp", "eth"]:
                        break
                path, path_length = self.finding_path(start_vertex, end_vertex, stream_bw,type)
                if(path!=None):
                    how_many_streams += 1
                    print(f"\nNajkrótsza ścieżka od wierzchołka {start_vertex} do {end_vertex}:")
                    print(f"Ścieżka: {path}")
                    print(f"Długość ścieżki: {path_length}")
                    self.file_builder("tosend.json",path, self.hostsports, how_many_streams,type)
                odp = input("Czy chcesz dodać nowe połączenie? Podaj 't' lub 'n': ")
            elif odp == "n":
                print("Program zakończył działanie.")
                break
            else:
                print("Błędna opcja - podaj 't' lub 'n'.")
                odp = input("Czy chcesz dodać nowe połączenie? Podaj 't' lub 'n': ")

    # metoda przygotowuwująca plik .json do wysłania na serwer onosowy
    def file_builder(self,filename, shortestpath, hostports, how_many_streams,type):
        template = "szablondowzorutcpudp.json"
        if(type=="tcp"):
            type="6"
        elif(type=="udp"):
            type="17"
        else:
            template = "szablondowzorueth.json"
        with open(filename, "r") as file_to_send:
            content = file_to_send.read()
            with open(template, "r") as file_template:
                new_content = file_template.read()
                if (how_many_streams > 1):
                    new_content += ","
                line = content.split('\n')
                line.insert(2, new_content)
                content = '\n'.join(line)

                content = content.replace("%1", str(hex(int(shortestpath[0]))[2:]))
                content = content.replace("%2", str(int(self.get_edge(shortestpath[0], shortestpath[1])[3])))
                content = content.replace("%3", hostports[shortestpath[0] - 1])
                content = content.replace("%4", str(shortestpath[len(shortestpath) - 1]))
                content = content.replace("%5", str(shortestpath[0]))
                content = content.replace("%6", type)

                if (how_many_streams == 1):
                    new_content += ","
                line = content.split('\n')
                line.insert(2, new_content)
                content = '\n'.join(line)
                content = content.replace("%1", str(hex(int(shortestpath[0]))[2:]))
                content = content.replace("%2", hostports[shortestpath[0] - 1])
                content = content.replace("%3", str(int(self.get_edge(shortestpath[0], shortestpath[1])[3])))
                content = content.replace("%4", str(shortestpath[0]))
                content = content.replace("%5", str(shortestpath[len(shortestpath) - 1]))
                content = content.replace("%6", type)

                line = content.split('\n')
                line.insert(2, new_content)
                content = '\n'.join(line)
                content = content.replace("%1", str(hex(int(shortestpath[len(shortestpath) - 1]))[2:]))
                content = content.replace("%2", hostports[shortestpath[len(shortestpath) - 1] - 1])
                content = content.replace("%3", str(int(self.get_edge(shortestpath[len(shortestpath) - 1], shortestpath[len(shortestpath) - 2])[3])))
                content = content.replace("%4", str(shortestpath[len(shortestpath) - 1]))
                content = content.replace("%5", str(shortestpath[0]))
                content = content.replace("%6", type)

                line = content.split('\n')
                line.insert(2, new_content)
                content = '\n'.join(line)
                content = content.replace("%1", str(hex(int(shortestpath[len(shortestpath) - 1]))[2:]))
                content = content.replace("%2", str(int(self.get_edge(shortestpath[len(shortestpath) - 1], shortestpath[len(shortestpath) - 2])[3])))
                content = content.replace("%3", hostports[shortestpath[len(shortestpath) - 1] - 1])
                content = content.replace("%4", str(shortestpath[0]))
                content = content.replace("%5", str(shortestpath[len(shortestpath) - 1]))
                content = content.replace("%6", type)

                for index in range(1, len(shortestpath) - 1):
                    with open(template, "r") as file_template:
                        new_content = file_template.read()
                        new_content += ","
                        line = content.split('\n')
                        line.insert(2, new_content)
                        content = '\n'.join(line)
                        content = content.replace("%1", str(hex(int(shortestpath[index]))[2:]))
                        content = content.replace("%2", str(int(self.get_edge(shortestpath[index], shortestpath[index + 1])[3])))
                        content = content.replace("%3", str(int(self.get_edge(shortestpath[index], shortestpath[index - 1])[3])))
                        content = content.replace("%4", str(int(shortestpath[len(shortestpath) - 1])))
                        content = content.replace("%5", str(int(shortestpath[0])))
                        content = content.replace("%6", type)

                        line = content.split('\n')
                        line.insert(2, new_content)
                        content = '\n'.join(line)
                        content = content.replace("%1", str(hex(int(shortestpath[index]))[2:]))
                        content = content.replace("%2", str(int(self.get_edge(shortestpath[index], shortestpath[index - 1])[3])))
                        content = content.replace("%3", str(int(self.get_edge(shortestpath[index], shortestpath[index + 1])[3])))
                        content = content.replace("%4", str(int(shortestpath[0])))
                        content = content.replace("%5", str(int(shortestpath[len(shortestpath) - 1])))
                        content = content.replace("%6", type)
            with open(filename, 'w') as file_to_send:
                file_to_send.write(content)