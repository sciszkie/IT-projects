from mininet.topo import Topo


class MyTopo(Topo):
    def __init__(self):
        Topo.__init__(self)

        h1 = self.addHost("h1") #Berlin
        h2 = self.addHost("h2") #Dortmund
        h3 = self.addHost("h3") #Monachium

        s1 = self.addSwitch("s1") #Berlin
        s2 = self.addSwitch("s2") #Dortmund
        s3 = self.addSwitch("s3") #Monachium

        link_s1s2 = self.addLink(s1, s2, delay='2.97ms', bw=10)
        link_s1s3 = self.addLink(s1, s3, delay='3.57ms', bw=10)

        self.addLink(h1, s1, delay='0.1ms', bw=10)
        self.addLink(h2, s2, delay='0.1ms', bw=10)
        self.addLink(h3, s3, delay='0.1ms', bw=800)


topos = {'mytopo': (lambda: MyTopo())}
