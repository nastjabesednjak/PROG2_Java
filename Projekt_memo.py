from tkinter import *
import tkinter.messagebox
import os
import random

x1 = 25
x2 = 0
y1 = 50
y2 = 25

poskus = 0
tezavnost = 24
tezavnostRaw = 6
barva = "black"

barve = [0, 0, 0, 0]
vnos = [0, 0, 0, 0]
prikaz = [0, 0, 0, 0]
pravilno_msto_barva = 0
pravilna_barva = 0

# Računalnik naključno izbere 4 izmed 6 barv
def izbira():
    global barve
    dokoncano_izbiranje = 0

    barve[0] = random.randint(1, 6)

    while dokoncano_izbiranje == 0:

        barve[1] = random.randint(1, 6)

        if barve[1] == barve[0]:

            barve[1] = 0

        else:

            dokoncano_izbiranje = 1

    dokoncano_izbiranje = 0

    while dokoncano_izbiranje == 0:

        barve[2] = random.randint(1, 6)

        if barve[2] == barve[0] or barve[2] == barve[1]:

            barve[2] = 0

        else:

            dokoncano_izbiranje = 1

    dokoncano_izbiranje = 0

    while dokoncano_izbiranje == 0:

        barve[3] = random.randint(1, 6)

        if barve[3] == barve[0] or barve[3] == barve[1] or barve[3] == barve[2]:

            barve[3] = 0

        else:

            dokoncano_izbiranje = 1

izbira()

#prikaze pravilno resitev
def Pravilna_resitev(resitev):
    x5 = 175
    x6 = 150
    y3 = 50
    y4 = 25
    if resitev[0] == 1:
        barva1 = "blue"
    elif resitev[0] == 2:
        barva1 = "red"
    elif resitev[0] == 3:
        barva1 = "yellow"
    elif resitev[0] == 4:
        barva1 = "green"
    elif resitev[0] == 5:
        barva1 = "purple"
    else:
        barva1 = "orange"
    if resitev[1] == 1:
        barva2 = "blue"
    elif resitev[1] == 2:
        barva2 = "red"
    elif resitev[1] == 3:
        barva2 = "yellow"
    elif resitev[1] == 4:
        barva2 = "green"
    elif resitev[1] == 5:
        barva2 = "purple"
    else:
        barva2 = "orange"
    if resitev[2] == 1:
        barva3 = "blue"
    elif resitev[2] == 2:
        barva3 = "red"
    elif resitev[2] == 3:
        barva3 = "yellow"
    elif resitev[2] == 4:
        barva3 = "green"
    elif resitev[2] == 5:
        barva3 = "purple"
    else:
        barva3 = "orange"
    if resitev[3] == 1:
        barva4 = "blue"
    elif resitev[3] == 2:
        barva4 = "red"
    elif resitev[3] == 3:
        barva4 = "yellow"
    elif resitev[3] == 4:
        barva4 = "green"
    elif resitev[3] == 5:
        barva4 = "purple"
    else:
        barva4 = "orange"

    PoljeResitev.create_oval(x5, y3, x6, y4, fill=barva1)
    x5 += 30
    x6 += 30
    PoljeResitev.create_oval(x5, y3, x6, y4, fill=barva2)
    x5 += 30
    x6 += 30
    PoljeResitev.create_oval(x5, y3, x6, y4, fill=barva3)
    x5 += 30
    x6 += 30
    PoljeResitev.create_oval(x5, y3, x6, y4, fill=barva4)

#Preveri igralčev poskus, če se ugibanje ujema z izbranimi barvammi
def Preveri_poskus():
    global prikaz, pravilno_mesto_barva, pravilna_barva, y1, y2

    x3 = 25
    x4 = 0

    pravilno_mesto_barva = prikaz.count(2)
    pravilna_barva = prikaz.count(1)

    while pravilno_mesto_barva > 0:
        x3 += 30
        x4 += 30
        Polje_preverjanje.create_oval(x3, y1, x4, y2, fill="black")
        pravilno_mesto_barva -= 1

    while pravilna_barva > 0:
        x3 += 30
        x4 += 30
        Polje_preverjanje.create_oval(x3, y1, x4, y2)
        pravilna_barva -= 1

def Primerjava_resitev():
    global barve, vnos, prikaz

    if barve[0] in vnos:
        if vnos[0] == barve[0]:
            prikaz[0] = 2
        else:
            prikaz[0] = 1
    else:
        prikaz[0] = 0

    if barve[1] in vnos:
        if vnos[1] == barve[1]:
            prikaz[1] = 2

        else:
            prikaz[1] = 1
    else:
        prikaz[1] = 0

    if barve[2] in vnos:
        if vnos[2] == barve[2]:
            prikaz[2] = 2
        else:
            prikaz[2] = 1
    else:
        prikaz[2] = 0

    if barve[3] in vnos:
        if vnos[3] == barve[3]:
            prikaz[3] = 2
        else:
            prikaz[3] = 1
    else:
        prikaz[3] = 0

    Preveri_poskus()
    Konec_igre()

def Konec_igre():
    global vnos, barve, poskus, tezavnost
    if vnos == barve:
        tezavnost = 0
        Pravilna_resitev(barve)
        tkinter.messagebox.showinfo("Memo", "Čestitam, našli ste pravilno rešitev!")
    elif tezavnost != 0:
        NONE
    else:
        Pravilna_resitev(barve)
        tkinter.messagebox.showinfo("Memo", "Poskusite ponovno.")

def SradnjaTezavnost():

    global tezavnostRaw
    tezavnostRaw = 6
    NovaIgra()

def TezjaTezavnost():

    global tezavnostRaw
    tezavnostRaw = 5
    NovaIgra()

def LahkaTezavnost():

    global tezavnostRaw
    tezavnostRaw = 7
    NovaIgra()

def Ugibanje(barva):

    global vnos

    if vnos[0] == 0:
        vnos[0] = barva
    elif vnos[1] == 0:
        vnos[1] = barva

    elif vnos[2] == 0:
        vnos[2] = barva

    elif vnos[3] == 0:
        vnos[3] = barva

        Primerjava_resitev()
    else:
        vnos = [0, 0, 0, 0,]
        Ugibanje(barva)

def Modra():

    global barva
    barva = "blue"
    SpawnKrog()
    Ugibanje(1)

def Rdeca():

    global barva
    barva = "red"
    SpawnKrog()
    Ugibanje(2)

def Rumena():

    global barva
    barva = "yellow"
    SpawnKrog()
    Ugibanje(3)

def Zelena():

    global barva
    barva = "green"
    SpawnKrog()
    Ugibanje(4)

def Vijola():

    global barva
    barva = "purple"
    SpawnKrog()
    Ugibanje(5)

def Oranzna():

    global barva
    barva = "orange"
    SpawnKrog()
    Ugibanje(6)

def SpawnKrog():

    global x1, x2, y1, y2, barva, poskus, tezavnost

    if tezavnost != 0:
        if poskus <= 3:

            x1 += 30
            x2 += 30
            PoljeKrogi.create_oval(x1, y1, x2, y2, fill=barva)
            poskus += 1

        else:

            x1 = 55
            x2 = 30
            y1 += 30
            y2 += 30

            PoljeKrogi.create_oval(x1, y1, x2, y2, fill=barva)
            poskus = 1

        tezavnost -= 1

def NovaIgra():

    global tezavnost, tezavnostRaw, x1, x2, y1, y2, poskus
    PoljeKrogi.delete(ALL)
    Polje_preverjanje.delete(ALL)
    PoljeResitev.delete(ALL)
    tezavnost = tezavnostRaw * 4

    x1 = 25
    x2 = 0
    y1 = 50
    y2 = 25
    poskus = 0

def pravila():

    os.startfile("Pravila")

okno = Tk()

OkvirBarve = Frame(okno)
OkvirBarve.pack()

ModerGumb = Button(OkvirBarve, text='Modra', bg="blue", fg="white", command = Modra)
RumenGumb = Button(OkvirBarve, text='Rumena', bg="yellow", command = Rumena)
RdecGumb = Button(OkvirBarve, text='Rdeča', bg="red", command = Rdeca)
ZelenGumb = Button(OkvirBarve, text='Zelena', bg="green", command = Zelena)
OranzenGumb = Button(OkvirBarve, text='Oranžna', bg="orange", command = Oranzna)
VijolaGumb = Button(OkvirBarve, text='Vijola', bg="purple", fg="white", command = Vijola)
ModerGumb.pack(side=LEFT)
RdecGumb.pack(side=LEFT)
RumenGumb.pack(side=LEFT)
ZelenGumb.pack(side=LEFT)
VijolaGumb.pack(side=LEFT)
OranzenGumb.pack(side=LEFT)

menu = Menu(okno)
okno.config(menu=menu)

podMenu = Menu(menu)
menu.add_cascade(label='Meni', menu=podMenu)
podMenu.add_command(label='Nova igra', command=NovaIgra)
podMenu.add_cascade(label='Pravila', command=pravila)
podMenu.add_separator()
podMenu.add_command(label='Izhod', command=okno.destroy)

podMenu2 = Menu(menu)
menu.add_cascade(label='Težavnost', menu=podMenu2)
podMenu2.add_command(label='Lahko', command=LahkaTezavnost)
podMenu2.add_command(label='Srednje', command=SradnjaTezavnost)
podMenu2.add_command(label='Težko', command=TezjaTezavnost)

ResitevOkvir = Frame(okno)
IgraOkvir = Frame(okno)
PoljeResitev = Canvas(ResitevOkvir, width=400, height=50)
PoljeKrogi = Canvas(IgraOkvir, width=200, height=300)
ResitevOkvir.pack(side=BOTTOM)
IgraOkvir.pack(side=BOTTOM)
Polje_preverjanje = Canvas(IgraOkvir, width = 200, height = 300)
PoljeKrogi.pack(side = LEFT)
Polje_preverjanje.pack(side = RIGHT)
PoljeResitev.pack(side = BOTTOM)

okno.mainloop()
