# ASD_Project
## Progetto di laboratorio del primo semestre del corso "Algoritmi e strutture dati" A.A. 2019-2020
Questa repo di GitHub raccoglie il lavoro svolto da Francesco Bombassei De Bona e Andrea Cantarutti per la realizzazione del primo progetto di laboratorio assegnato alla fine del primo semestre del corso "Algotimi e strutture dati".

### Prerequisiti
Per una corretta visualizzazione del contenuto della repo sono necessari alcuni software:
* R
* RStudio
* IDE Java
* SDK Java
* Excel, o equivalente

### Struttura della repo
Le cartelle e i file di diretto interesse per visionare il progetto sono:
* src - in cui sono contenuti tutti i file .java riguardanti gli algortimi di selezione, la gestione della raccolta dati su file .xlsx, la generazione degli input e altre utility
* R - che raccoglie le elaborazioni in R dei grafici costruiti a partire dai dati raccolti e gli stessi grafici
* Time.xlsx - file utilizzato per raccogliere e generare statistiche preliminari sui dati temporali degli algoritmi di selezione
* Time_Template.xlsx - foglio di lavoro utilizzato come template per generare tutti i fogli contenuti nel workbook Time.xlsx
Le restanti cartelle e file sono superflui, perché generati dall'IDE e da Git

### Precauzioni sull'utilizzo della classe Time.java
Il codice da noi elaborato in Time.java, in particolare i meccanismi di Warmup della JVM, sono stati adattatiper un'esecuzione efficiente sull'hardware a nostra disposizione e quindi possono fornire prestazioni differenti se eseguiti su macchine a prestazioni differenti

### Classi non trattate nella relazione
Le classi Excel.java e Count.java non vengono analizzate in modo approfondito nella relazione complementare a questa repo, questo perché le loro funzionalità non sono necessarie alla risoluzione dei problemi proposti dall'insegnante, ma del tutto accessorie alla realizzazione pratica di un progetto maggiormente user friendly. In particolare:
* Excel.java - serve a strutturare in modo automatico il file Time.xlsx
* Count.java - semplice utility per contare il numero di interi in una sequenza di numeri fornita a riga di comando
