# Frogger Game

## Popis
Tento projekt je jednoduchá implementácia klasickej hry **Frogger**, kde hráč ovláda žabu, ktorá sa musí dostať cez cestu a rieku, pričom sa vyhýba autám a snaží sa preskočiť na logy. Cieľom je prežiť a dostať žabu na druhú stranu.

## Funkcie
- Hráč môže pohybovať žabou pomocou klávesov `WASD` alebo šípok.
- Rôzne objekty ako autá a logy pohybujúce sa po obrazovke.
- Žaba môže zostať na logoch, ale ak sa dostane do vody alebo narazí do auta, hra končí.
- Hra obsahuje základnú grafiku s GUI, a to pomocou Java Swing.

## Inštalácia
1. Skontrolujte, či máte nainštalovanú **Javu** (minimálne verziu 8).
2. Klonujte tento repozitár:
   __git clone https://github.com/uzivatelsky_meno/frogger_game.git__
3. Prejdite do adresára projektu:
    __cd frogger_game__
4. Skontrolujte, či máte všetky závislosti (pre túto hru používame základné Java knižnice bez potreby externých závislostí).
5. Skontrolujte, či sú v projekte nastavené všetky súbory a obrázky (napr. gif pre pozadie).
6. Spustite hru pomocou:
   __javac *.java java main.Main__

## Ovládanie
- **Šípky alebo `WASD`:** Pohyb žaby na obrazovke.
- **Úlohy:**
- Vyhnúť sa autám.
- Preskakovať na logy v rieke.
- Ak sa žaba dostane do vody bez logu, hra končí.

## Architektúra
- **GamePanel.java:** Hlavná herná logika, zobrazenie hry, pohyb objektov a detekcia kolízií.
- **Hlavné funkcie:** Kreslenie herného plánu, spracovanie pohybu žaby a iných objektov, detekcia kolízií a kontrola životov.
- **Pohyb objektov:** Pohyb aut, logov a žaby, ktoré reagujú na stlačené klávesy (`WASD`, šípky).
- **Main.java:** Hlavný vstupný bod aplikácie, ktorý vytvára a spúšťa herný panel.
- **Hlavné funkcie:** Inicializuje herné prostredie, spúšťa herný cyklus a aktualizuje herné objekty.

## Závislosti
- **Java:** Verzia 8 alebo novšia.
- **Swing:** Pre GUI a kreslenie herného plánu.

## Poznámky
Tento projekt je stále vo vývoji. Plánujeme pridať ďalšie funkcie, ako sú zvukové efekty, lepšia grafika a vylepšená detekcia kolízií.

## Vývojári
- **Maksym Chernikov** - Hlavný vývojár.
- **Erik Janošik** - Hlavný vývojár.
- **Matej Uhrina** - Hlavný vývojár.
- **Matúš Remeň** - Hlavný vývojár.

