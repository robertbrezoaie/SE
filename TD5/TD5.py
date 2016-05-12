import threading
import time


# pentru treaduri=semafor
class StareBlock:

    def __init__(self, init):
        self.lock = threading.Condition(threading.Lock())  #default unlocked
        self.value = init

    def available(self):
        with self.lock:
            self.value += 1
            self.lock.notify()

    def ocupat(self):
        with self.lock:
            while self.value == 0:
                self.lock.wait()
            self.value -= 1


class Furculite(object):

    def __init__(self, nr):
        self.lock = threading.Condition(threading.Lock())
        self.nr = nr
        self.filo = -1
        self.esteFolosita = False

    def lasa(self, filo):
        with self.lock:
            while self.esteFolosita == False:
                self.lock.wait()
            self.filo = -1
            self.esteFolosita = False
            print("filozoful cu nr : " + filo + " lasa furcuita " + self.nr)
            self.lock.notifyAll()

    def ridica(self, filo):
        with self.lock:
            while self.esteFolosita == True:
                self.lock.wait()
            self.filo = filo
            self.esteFolosita = True
            print("filozoful cu nr : " + filo + " ia furculita " + self.nr)
            self.lock.notifyAll()



class Filozof(threading.Thread):

    def __init__(self, nrFilo, furcstanga, furcdreapta, stareThread):
        threading.Thread.__init__(self)
        self.nrFilo = nrFilo
        self.furcstanga = furcstanga
        self.furcdreapta = furcdreapta
        self.stareThread = stareThread

    def run(self):
        for i in range(20):
            self.stareThread.ocupat()
            time.sleep(1)
            self.furcstanga.ridica(self.nrFilo)
            self.furcdreapta.ridica(self.nrFilo)
            time.sleep(1)
            self.furcdreapta.lasa(self.nrFilo)
            self.furcstanga.lasa(self.nrFilo)
            self.stareThread.available()
        print(self.nrFilo + " a terminat de mancat si nici nu se mai gandeste"  )


def main():
    nrPhfc = 6
    servire = StareBlock(nrPhfc - 1)        # pt a evita deadlock u

    furculite = [Furculite(i) for i in range(nrPhfc)]
    filozofi = [Filozof(i, furculite[i], furculite[(i + 1) % nrPhfc], servire) for i in range(nrPhfc)]

    for i in range(nrPhfc):
        filozofi[i].start()


if __name__ == "__main__":
    main()
