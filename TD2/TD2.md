import threading import queue

class Producator(threading.Thread):

#Producer ia datele ,le modifica si le pune in q pt Consumator


#constructor
def __init__(self, dataInQ, lock):
    threading.Thread.__init__(self)
    self.dataInQ = dataInQ
    self.lock = lock


def run(self):
    self.lock.acquire()
    file = "Persoane.txt"

    with open(file, 'r') as f:  #citeste din fisier (exceptie)

        for rand in f:
            self.dataInQ.put(rand.strip(' \r\n'))


    #anunta ca th. e terminat
    self.lock.release()
class Consumator(threading.Thread):

def __init__(self, dataQ, lock):
    threading.Thread.__init__(self)
    self.dataQ = dataQ
    self.M = []                         #q pentru monsieur
    self.Mme = []                       #q pentru madame
    self.lock = lock


#meth. de run pt Thread.
def run(self):
    self.lock.acquire()  #sa nu poata fi acc.de alt thread.

    while True:                                 #bucla inf.
        componenta = self.dataQ.get()           #ia primul element din q

        if componenta[0:2] == "M.":             #daca incepe cu M:
            self.M.append(componenta)           #il baga in q monsieur

        else:
            self.Mme.append(componenta)         #il baga in q madame


    self.lock.release()                         #termin. thread


def scriereFisier(self):

    f = open("Salutari.txt", 'w')

    for mme in self.Mme:
        f.write("Bonjour %s!\n" % {mme})          #scriere fisier

    for m in self.M:
        f.write("Bonjour %s!\n" % {m})          #scriere fisier

    f.close()                                   #inchidere fisier.
if name == '__main_': queue1 = queue.Queue()

lock = threading.Lock()
p = Producator(queue1, lock)
p.start()                       #porneste threadul.

c = Consumator(queue1, lock)
c.start()                       #porneste threadul.

queue1.join()    
