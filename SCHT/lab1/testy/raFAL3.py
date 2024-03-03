from mininet.topo import Topo


class MyTopo(Topo):
    def __init__(self):
        Topo.__init__(self)

        h1 = self.addHost("h1") #Berlin
        h2 = self.addHost("h2") #Dortmund
        h3 = self.addHost("h3") #Monachium
        h4 = self.addHost("h4") #Gdansk
        h5 = self.addHost("h5") #Bruksela
        h6 = self.addHost("h6") #Amsterdam
        h7 = self.addHost("h7") #Praga
        h8 = self.addHost("h8") #Wieden
        h9 = self.addHost("h9") #Lublana
        h10 = self.addHost("h10") #Budapeszt

        s1 = self.addSwitch("s1") #Berlin
        s2 = self.addSwitch("s2") #Dortmund
        s3 = self.addSwitch("s3") #Monachium
        s4 = self.addSwitch("s4") #Gdansk
        s5 = self.addSwitch("s5") #Bruksela
        s6 = self.addSwitch("s6") #Amsterdam
        s7 = self.addSwitch("s7") #Praga
        s8 = self.addSwitch("s8") #Wieden
        s9 = self.addSwitch("s9") #Lublana
        s10 = self.addSwitch("s10") #Budapeszt

        link_s1s2 = self.addLink(s1, s2, delay='2.97ms', bw=10)
        link_s1s3 = self.addLink(s1, s3, delay='3.57ms', bw=10)
        link_s1s4 = self.addLink(s1, s4, delay='2.85ms', bw=10)
        link_s2s5 = self.addLink(s2, s5, delay='1.61ms', bw=10)
        link_s2s6 = self.addLink(s2, s6, delay='1.43ms', bw=10)
        link_s3s7 = self.addLink(s3, s7, delay='2.09ms', bw=10)
        link_s3s8 = self.addLink(s3, s8, delay='2.47ms', bw=10)
        link_s8s9 = self.addLink(s8, s9, delay='1.95ms', bw=10)
        link_s8s10 = self.addLink(s8, s10, delay='1.56ms',bw=10)

        self.addLink(h1, s1, delay='0.1ms', bw=10)
        self.addLink(h2, s2, delay='0.1ms', bw=10)
        self.addLink(h3, s3, delay='0.1ms', bw=10)
        self.addLink(h4, s4, delay='0.1ms', bw=10)
        self.addLink(h5, s5, delay='0.1ms', bw=10)
        self.addLink(h6, s6, delay='0.1ms', bw=10)
        self.addLink(h7, s7, delay='0.1ms', bw=10)
        self.addLink(h8, s8, delay='0.1ms', bw=10)
        self.addLink(h9, s9, delay='0.1ms', bw=10)
        self.addLink(h10, s10, delay='0.1ms', bw=5)


topos = {'mytopo': (lambda: MyTopo())}
